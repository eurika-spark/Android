package com.hevo.study.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hevo.study.R;
import com.hevo.study.listViewPart.layout.ListViewMainActivity;
import com.hevo.study.print.layout.PrintMainActivity;
import com.hevo.study.progress.layout.ProgressMainActivity;
import com.hevo.study.requestWeb.layout.RequestWebMainActivity;
import com.hevo.study.sqlite.layout.SQLiteMainActivity;
import com.hevo.study.update.layout.UpdateAppActivity;

public class MainActivity extends Activity {

	private TextView toPrintView = null;
	
	private TextView toListView = null;
	
	private TextView toSqliteView = null;
	
	private TextView toUpdateView = null;
	
	private TextView toProvider = null;
	
	private TextView verTextView = null;
	
	private TextView progressView = null;
	
	private TextView reqWebView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		toPrintView = (TextView) findViewById(R.id.page_2_print);
		toPrintView.setOnClickListener(new PageToClickListener(PrintMainActivity.class));
		
		toListView = (TextView) findViewById(R.id.page_2_listView);
		toListView.setOnClickListener(new PageToClickListener(ListViewMainActivity.class));
		
		toSqliteView = (TextView) findViewById(R.id.page_2_sqlite);
		toSqliteView.setOnClickListener(new PageToClickListener(SQLiteMainActivity.class));
		
		toUpdateView = (TextView) findViewById(R.id.page_2_update);
		toUpdateView.setOnClickListener(new PageToClickListener(UpdateAppActivity.class));
		
		progressView = (TextView) findViewById(R.id.page_2_progressBar);
		progressView.setOnClickListener(new PageToClickListener(ProgressMainActivity.class));
		
		reqWebView = (TextView) findViewById(R.id.page_2_requestWeb);
		reqWebView.setOnClickListener(new PageToClickListener(RequestWebMainActivity.class));
		
		verTextView = (TextView) findViewById(R.id.version_info);
		verTextView.setText(getFullVersionName());
		
		toProvider = (TextView) findViewById(R.id.page_2_provider);
		toProvider.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "This is a kind of Service without View!", Toast.LENGTH_SHORT)
					.show();
			}
		});
	}
	
	private final class PageToClickListener implements View.OnClickListener {
		private Class<?> clazz = null;
		public PageToClickListener(Class<?> clazz) {
			this.clazz = clazz;
		}
		@Override
		public void onClick(View arg0) {
			pageTo(clazz);
		}
	}
	
	private void pageTo(Class<?> clazz) {
		Intent intent = new Intent();
		intent.setClass(this, clazz);
		startActivity(intent);
	}
	
	private String getFullVersionName() {
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			return packageInfo.versionName + "." + packageInfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
