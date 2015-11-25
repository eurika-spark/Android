package com.hevo.study.sqlite.layout;

import org.apache.commons.lang3.math.NumberUtils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hevo.study.R;
import com.hevo.study.sqlite.service.DBPersonService;

public class SQLiteMainActivity extends Activity {

	public static final int REQUEST_CODE = 3; 
	
	private DBPersonService personDBService= null;
	private Cursor cursor = null;

	private ListView lView = null;
	private Button addBtn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_main_activity);
		
		lView = (ListView) findViewById(R.id.sqlite_list_id);
		lView.setOnCreateContextMenuListener(new SqliteDeleteItemListener());
		
		refreshListView();
		
		addBtn = (Button) findViewById(R.id.sqlite_btn_add);
		addBtn.setOnClickListener(new SQLiteAddBtnClickListener(AddItem4SQLiteActivity.class));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		refreshListView();
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void refreshListView() {
		personDBService = new DBPersonService(this);
		
		cursor = personDBService.getScrollDataInCursor(0, 20);
		
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, 
				R.layout.sqlite_list_item, cursor, 
				new String[]{"person_name", "cell_phone"}, 
				new int[] {R.id.sqlite_name, R.id.sqlite_phone});
		// you will need to unset the current Cursor from the adapter to avoid leaks due to its registered observers. 
//				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		lView.setAdapter(cursorAdapter);
	}

	// List View 长按，弹出 context menu 
	private final class SqliteDeleteItemListener implements OnCreateContextMenuListener {

		@Override
		public void onCreateContextMenu(ContextMenu menu, View view,
				ContextMenuInfo menuInfo) {
			
			menu.setHeaderTitle(" Operation of SQLite Data ");
			menu.add(0, 1, Menu.NONE, "Edit..");
			menu.add(0, 2, Menu.NONE, "Delete");
		}
	}
	
	// 响应 弹出context menu的点击事件
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		String id = String.valueOf(menuInfo.id) ;
		
		TextView text = (TextView) (menuInfo.targetView).findViewById(R.id.sqlite_name);
		String content = text.getText().toString();
		
		switch(item.getItemId()) {
			case 1:
				Toast.makeText(getApplicationContext(), "Click Edit Item[" + content + "]", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				personDBService.delete(NumberUtils.toInt(id));
				Toast.makeText(getApplicationContext(), "Delete Item[" + content + "]", Toast.LENGTH_SHORT).show();
//				cursorAdapter.notifyDataSetChange();
				break;
			
			default:
				return super.onContextItemSelected(item);
		}
		refreshListView();
		return true;
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
		startActivityForResult(intent, REQUEST_CODE);
	}
	
}
