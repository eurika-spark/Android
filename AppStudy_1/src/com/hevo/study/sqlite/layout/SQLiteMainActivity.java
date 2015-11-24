package com.hevo.study.sqlite.layout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.hevo.study.R;
import com.hevo.study.sqlite.service.DBPersonService;

public class SQLiteMainActivity extends Activity {

	
	private DBPersonService personDBService= null;
	private Cursor cursor = null;

	private ListView lView = null;
	private Button addBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_main_activity);
		
		lView = (ListView) findViewById(R.id.sqlite_list_id);
		
		personDBService = new DBPersonService(this);
		
		cursor = personDBService.getScrollDataInCursor(0, 15);
		
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, 
				R.layout.sqlite_list_item, cursor, 
				new String[]{"person_name", "cell_phone"}, 
				new int[] {R.id.sqlite_name, R.id.sqlite_phone});
		// you will need to unset the current Cursor from the adapter to avoid leaks due to its registered observers. 
//				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		lView.setAdapter(cursorAdapter);
		
		addBtn = (Button) findViewById(R.id.sqlite_btn_add);
		addBtn.setOnClickListener(new SQLiteAddBtnClickListener(AddItem4SQLiteActivity.class));
	}
	
	private final class SQLiteAddBtnClickListener implements View.OnClickListener {
		private Class<?> clazz = null;
		
		public SQLiteAddBtnClickListener(Class<?> clazz) {
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
