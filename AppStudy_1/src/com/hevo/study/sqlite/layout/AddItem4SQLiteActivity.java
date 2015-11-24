package com.hevo.study.sqlite.layout;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hevo.study.R;
import com.hevo.study.sqlite.bean.DBPerson;
import com.hevo.study.sqlite.service.DBPersonService;


public class AddItem4SQLiteActivity extends Activity {

	private EditText nameEdit = null;
	private EditText phoneEdit = null;
	private Button addBtn = null;
	
	// layout 
	private LinearLayout layout = null;
	
	private DBPersonService dbServer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_add_item_activity);
		
		nameEdit = (EditText) findViewById(R.id.add_item_name);
		phoneEdit = (EditText) findViewById(R.id.add_item_phone);
		addBtn = (Button) findViewById(R.id.add_item_btn);
		
		layout=(LinearLayout)findViewById(R.id.sqlite_add_item_layout);
        layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Popup Window exit", Toast.LENGTH_SHORT)
					.show();
				exit();
			}
        });
		
		addBtn.setOnClickListener(new SqliteAddItemBtnClickListener());
		
		dbServer = new DBPersonService(this);
		/**
		 * 1.colorPicker
		 * 2.popup Activity
		 */
	}
	
	private final class SqliteAddItemBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			String name = nameEdit.getText().toString();
			String phone = phoneEdit.getText().toString();
			
			if(StringUtils.isEmpty(name) 
					|| StringUtils.isEmpty(phone)) {
				Toast.makeText(getApplicationContext(), "Empty is NOT good, Please check the input", Toast.LENGTH_SHORT)
				.show();
				return ;
			}
			
			DBPerson person = new DBPerson();
			dbServer.save(person);
			
			Intent intent = new Intent();
			intent.setClass(view.getContext(), SQLiteMainActivity.class);
			startActivity(intent);
		}
	}
	
	private void exit() {
		this.finish();
	}
	
}
