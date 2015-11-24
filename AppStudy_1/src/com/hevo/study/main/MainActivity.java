package com.hevo.study.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hevo.study.R;
import com.hevo.study.listViewPart.layout.ListViewMainActivity;
import com.hevo.study.print.layout.PrintMainActivity;
import com.hevo.study.sqlite.layout.SQLiteMainActivity;

public class MainActivity extends Activity {

	private TextView toPrintView = null;
	
	private TextView toListView = null;
	
	private TextView toSqlite = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		toPrintView = (TextView) findViewById(R.id.page_2_print);
		toPrintView.setOnClickListener(new PageToClickListener(PrintMainActivity.class));
		
		toListView = (TextView) findViewById(R.id.page_2_listView);
		toListView.setOnClickListener(new PageToClickListener(ListViewMainActivity.class));
		
		toSqlite = (TextView) findViewById(R.id.page_2_sqlite);
		toSqlite.setOnClickListener(new PageToClickListener(SQLiteMainActivity.class));
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
}
