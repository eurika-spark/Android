package com.hevo.study.print.layout;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hevo.study.R;
import com.hevo.study.print.service.IPrintService;
import com.hevo.study.print.service.LoadPrintPatternService;
import com.hevo.study.print.service.impl.PrintServiceImpl;

public class TxtPatternActivity extends Activity {

	private EditText patternEditor = null;
	private Button saveBtn = null;
	
	// 打印的服务
	private IPrintService printService = null;
	
	// 服务连接类
	PrintPatternServiceConn conn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.txtpattern_activity);
		
		patternEditor = (EditText) findViewById(R.id.patternEditor);
		if(StringUtils.isNotBlank(PrintServiceImpl.patternContent)) {
			patternEditor.setText(PrintServiceImpl.patternContent);
		}
		
		saveBtn = (Button) findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(new onClickForSave());
		
		// 注册启动服务
		conn = new PrintPatternServiceConn();
		Intent service = new Intent(this, LoadPrintPatternService.class);
		bindService(service, conn, BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onDestroy() {
		unbindService(conn);
		super.onDestroy();
	}
	
	private class PrintPatternServiceConn implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			printService = (IPrintService) service;
		}
		@Override
		public void onServiceDisconnected(ComponentName name) {
			printService = null;
		}
	}
	
	private class onClickForSave implements  OnClickListener {
		@Override
		public void onClick(View arg0) {
			try {
				saveEvent();
				finish();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(TxtPatternActivity.this, "保存文件时出错", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	private void saveEvent() throws Exception {
		String patternTxt = patternEditor.getText() == null ? "empty" : patternEditor.getText().toString() ;
		// 调用服务类来 刷新模板数据到内存中
		printService.RefreshMen4Pattern(patternTxt);
	}
	
}
