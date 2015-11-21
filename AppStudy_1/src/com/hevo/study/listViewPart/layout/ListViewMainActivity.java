package com.hevo.study.listViewPart.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hevo.study.R;
import com.hevo.study.listViewPart.CustomAdapter;
import com.hevo.study.listViewPart.bean.Person;

public class ListViewMainActivity extends Activity {
	
	private ListView listView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_main_activity);
		
		listView = (ListView) findViewById(R.id.listview_id);
		listView.setOnItemClickListener(new ItemClickListener());
		
		List<Person> personList = getDataForListView();
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		
		HashMap<String, Object> item = null;
		for (Person person : personList) {
			item = new HashMap<String, Object>();
			item.put("id", person.getId());
			item.put("name", person.getName());
			item.put("phone", person.getPhone());
			item.put("amount", person.getAmount());
			data.add(item);
		}

		/* Android 自带的Adapter
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_view_item, 
				// 对应关系
				new String[] {"name", "phone", "amount"}, 		
				new int[]{R.id.listview_name, R.id.listview_phone, R.id.listview_amount});
		 */
		
		// Custom Adapter
		CustomAdapter<Person> adapter = new CustomAdapter<Person>(this, personList, R.layout.list_view_item);
		listView.setAdapter(adapter);
	}
	
	
	private final class ItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			ListView lView = (ListView) parent;
			Person person = (Person) lView.getItemAtPosition(position);
			Toast.makeText(getApplicationContext(), 
					"Item u Clicked is {"+ person.getName() + 
					", Tel:"+ person.getPhone() + 
					",  Amount:" + person.getAmount() + "}", 
					Toast.LENGTH_SHORT).show();
		}
	}
	
	
	private List<Person> getDataForListView() {
		List<Person> personList = new ArrayList<Person>();
		
		Person person = null;
		for (int i = 0; i < 20; i++) {
			person = new Person();
			person.setId("id00000" + i + 1);
			person.setName("Jr" +i +1+".Hevo");
			person.setPhone("1388888568" + i);
			person.setAmount("$100," + i + "00,000");
			personList.add(person);
		}
		return personList;
	}
	
}
