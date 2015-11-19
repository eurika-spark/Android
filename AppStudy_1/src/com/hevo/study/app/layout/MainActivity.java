package com.hevo.study.app.layout;

import com.hevo.study.app.R;
import com.hevo.study.app.service.LoadPrintPatternService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button filePlayBtn = null;
	private Button exceptionTestBtn = null;
	private Button txtPatternBtn = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		filePlayBtn = (Button) findViewById(R.id.filePlayBtn);
		filePlayBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent service = new Intent(getApplicationContext(), LoadPrintPatternService.class);
				startService(service);
			}
		});
		
		exceptionTestBtn = (Button) findViewById(R.id.exceptionTestBtn);
		exceptionTestBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				throw new NullPointerException("testing");
			}
		});
		
		txtPatternBtn = (Button) findViewById(R.id.txtPatternBtn);
		txtPatternBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				/** 
				 * 跨应用的Intent，调用其他的app    xx.xxx.xxx(包名)-锁定要调用的app
				intent.setClassName("com.pos.trade.ui", "com.pos.trade.ui.testActivity");
				 */
				
//				intent.setClass(MainActivity.this, TxtPatternActivity.class);
				intent.setClassName(MainActivity.this, "com.hevo.study.app.layout.TxtPatternActivity");
				startActivity(intent);
			}
		});
		
		// loading pattern template as initial
		Intent service = new Intent(getApplicationContext(), LoadPrintPatternService.class);
		startService(service);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
