package com.hevo.study.update.layout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;

import android.app.ProgressDialog;
import android.os.Environment;
import android.util.Xml;

public class VersionService {

	public static VersionBean getVerionInfo(String urlPath) throws Exception {

		if (StringUtils.isBlank(urlPath)) {
			return null;
		}

		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		InputStream inStream = null;
		if (conn.getResponseCode() == 200) {
			try {
				inStream = conn.getInputStream();

				// ByteArrayOutputStream outStream = new
				// ByteArrayOutputStream();
				// byte[] buffer = new byte[1024];
				// int len = -1;
				// while((len = inStream.read(buffer)) != -1)
				// outStream.write(buffer, 0, len);
				// System.out.println(outStream.toString());

				return parseXML(inStream);
			} catch (Exception e) {
				e.printStackTrace();
				if (inStream != null)
					inStream.close();
				throw e;
			}
		}
		return null;
	}

	private static VersionBean parseXML(InputStream inStream) throws Exception {
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream, "UTF-8");

		int event = parser.getEventType();
		VersionBean versionBean = null;
		List<String> updateInfoList = new ArrayList<String>();

		while (event != XmlPullParser.END_DOCUMENT) {

			switch (event) {
			case XmlPullParser.START_TAG:

				if ("appVersion".equalsIgnoreCase(parser.getName())) {
					versionBean = new VersionBean();
				}
				if ("versionCode".equalsIgnoreCase(parser.getName())) {
					versionBean.setVersionCode(NumberUtils.toInt(parser
							.nextText()));
				}
				if ("versionName".equalsIgnoreCase(parser.getName())) {
					versionBean.setVersionName(parser.nextText());
				}
				if ("updateInfo".equalsIgnoreCase(parser.getName())) {
					updateInfoList.add(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if ("appVersion".equalsIgnoreCase(parser.getName())) {
					versionBean.setUpdateInfoList(updateInfoList);
				}
			}
			event = parser.next();
		}

		return versionBean;
	}

	public static void downloadAPK(String url, String fileName, ProgressDialog progressBar) {
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();

			// get file size
			int length = (int) entity.getContentLength();
			progressBar.setMax(length);

			InputStream inStream = entity.getContent();
			FileOutputStream fileOutputStream = null;
			if (inStream != null) {
				File file = new File(Environment.getExternalStorageDirectory(), // get SDCard Directory
						fileName);
				fileOutputStream = new FileOutputStream(file);
				// byte[] buf = new byte[1024];
				byte[] buffer = new byte[256]; // for testing the performance
				int len = -1;
				int process = 0;
				while ((len = inStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, len);
					process += len;
					progressBar.setProgress(process); // 这里就是关键的实时更新进度了！
				}
			}
			fileOutputStream.flush();
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
