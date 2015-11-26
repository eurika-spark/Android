package com.hevo.appBackEnd.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


public class CheckVersionServlet extends HttpServlet {

	private static final long serialVersionUID = 2913866257550040941L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
		String result = getVersionInfoFromLocal();
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
		super.doGet(req, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		
		String result = getVersionInfoFromLocal();
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
		super.doPost(req, response);
	}
	
	
	private String getVersionInfoFromLocal() {
		String versionInfo = null;
		try {
			versionInfo = loadVersionInfoFromFile();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// if have <? version="" .. ?> delete it
		if(StringUtils.contains(versionInfo, "<?")) {
			versionInfo = StringUtils.substring(versionInfo,
					StringUtils.indexOf(versionInfo, "?>") + 2, versionInfo.length());
			versionInfo = StringUtils.replace(versionInfo, "\r\n", "");
			versionInfo = StringUtils.replace(versionInfo, "\t", "");
		}
		return versionInfo;
	}
	
	private String loadVersionInfoFromFile() throws Exception {
		InputStream in = this.getServletContext().getResourceAsStream("/AppVersion.xml");
		byte[] buffer = new byte[1024];
		
		OutputStream outStream = new ByteArrayOutputStream();
		int len = 0;
		while((len = in.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		
		return outStream.toString();
	}
	
}
