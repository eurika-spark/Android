package com.hevo.study.requestWeb.layout;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.hevo.study.R;

public class RequestWebMainActivity extends Activity {

	private EditText urlText = null;
	private EditText postName = null;
	private EditText postPhone = null;
	private Button getBtn = null;
	private Button postBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_web_main);
		
		postName = (EditText) findViewById(R.id.req_post_name);
		postPhone = (EditText) findViewById(R.id.req_post_phone);
		urlText = (EditText) findViewById(R.id.req_web_url);
		
		getBtn = (Button) findViewById(R.id.req_get_btn);
		getBtn.setOnClickListener(new GetRequestClickListener());
		
		postBtn = (Button) findViewById(R.id.req_post_btn);
		postBtn.setOnClickListener(new PostRequestClickListener());
	}

	private final class GetRequestClickListener implements OnClickListener {
		@Override
		public void onClick(View view) {
			String url = urlText.getText().toString();
			new DoGetTask().execute(new String[]{url}); 
		}
	}
	
	private final class PostRequestClickListener implements OnClickListener {
		@Override
		public void onClick(View view) {
			String url = urlText.getText().toString();
			new DoPostTask().execute(new String[]{url}); 
		}
	}
	
	private class DoGetTask extends AsyncTask<String, Integer, Integer> {
		@Override
		protected Integer doInBackground(String... params) {
			String url = params[0];
			url += "?secretCode=testGet";
			HttpURLConnection conn;
			try {
				conn = (HttpURLConnection) new URL(url).openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				return conn.getResponseCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			AlertDialog.Builder builder = new Builder(RequestWebMainActivity.this);
			builder.setTitle("Do Get Response");
			if(result != -1) {
				builder.setMessage("Request for GET way successfully! ");
			} else {
				builder.setMessage("Request for GET way fail! ");
			}
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			Dialog dialog = builder.create();
			dialog.show();
		}
	}
	
	private class DoPostTask extends AsyncTask<String, Integer, Integer> {

		@Override
		protected Integer doInBackground(String... params) {
			try {
				String url = params[0];
				StringBuilder data = new StringBuilder("?");
				if(StringUtils.isNotBlank(postName.getText().toString())) {
					data.append("personName=");
						data.append(URLEncoder.encode(postName.getText().toString(), "utf-8"));
					data.append("&");
				}
				if(StringUtils.isNotBlank(postPhone.getText().toString())) {
					data.append("personPhone=");
					data.append(URLEncoder.encode(postPhone.getText().toString(), "utf-8"));
				}
				byte[] entity = data.toString().getBytes();
				
				HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);		// 允许对外输出数据
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
				OutputStream outStream = conn.getOutputStream();
				outStream.write(entity);
				
				return conn.getResponseCode() ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			AlertDialog.Builder builder = new Builder(RequestWebMainActivity.this);
			builder.setTitle("Do POST Response");
			if(result != -1) {
				builder.setMessage("Request for POST way successfully! ");
			} else {
				builder.setMessage("Request for POST way fail! ");
			}
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			Dialog dialog = builder.create();
			dialog.show();
		}
	}
	
}
