package com.hevo.study.listViewPart;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hevo.study.R;
import com.hevo.study.listViewPart.bean.Person;

/**
 * 自定义适配器
 * @author Hevo
 */
public class CustomAdapter<T> extends BaseAdapter {
	
	private List<T> list = null;
	private int resource ;
	// 布局填充器
	private LayoutInflater inflater = null;
	
	public CustomAdapter(Context context, List<T> list, int resource) {
		this.list = list;
		this.resource = resource;
		// 取得系统服务
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if(list == null || list.size() == 0) {
			return 0;
		}
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position) == null ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// IMPORTANT, have function of cache that initial item when meet first page
	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TextView nameView = null;
		TextView phoneView = null;
		TextView amountView = null;
		ViewCache cache = null;
		
		// if current is first page that convertView is null
		if(convertView == null ) {
			// generate item object for list view
			convertView = inflater.inflate(resource, null);
			
			nameView = (TextView) convertView.findViewById(R.id.listview_name);
			phoneView = (TextView) convertView.findViewById(R.id.listview_phone);
			amountView = (TextView) convertView.findViewById(R.id.listview_amount);
			
			cache = new ViewCache();
			cache.name = nameView;
			cache.phone = phoneView;
			cache.amount = amountView;
			
			// 使用标志，临时存放缓存数据
			convertView.setTag(cache);
		} else {
			cache = (ViewCache) convertView.getTag();
			nameView = cache.name ;
			phoneView = cache.phone ;
			amountView = cache.amount ;
		}
		
		Person person = (Person) list.get(position);
		nameView.setText(person.getName() + "  [Custom]");
		phoneView.setText(person.getPhone() + "  [Custom]");
		amountView.setText(person.getAmount() + " [Custom]");
		
		return convertView;
	}

	
	private final class ViewCache {
		public TextView name = null;
		public TextView phone = null;
		public TextView amount = null;
	}
	
}
