package com.hevo.study.sqlite.layout;

import android.app.Activity;
import android.os.Bundle;

import com.hevo.study.R;
import com.hevo.study.sqlite.service.DBPersonService;

public class SQLiteMainActivity extends Activity {

	
	private DBPersonService personDBService= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_main_activity);
		
		personDBService = new DBPersonService(this);
		
	}
	
}
