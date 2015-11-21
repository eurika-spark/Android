package com.hevo.study.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hevo.study.R;
import com.hevo.study.print.layout.PrintMainActivity;

public class MainActivity extends Activity {

	private TextView toPrintView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		toPrintView = (TextView) findViewById(R.id.page_2_print);
		toPrintView.setOnClickListener(new PageToClickListener());
	}
	
	private final class PageToClickListener implements View.OnClickListener {
		@Override
		public void onClick(View arg0) {
			pageTo(PrintMainActivity.class);
		}
	}
	
	private void pageTo(Class<?> clazz) {
		Intent intent = new Intent();
		intent.setClass(this, clazz);
		startActivity(intent);
	}
}
