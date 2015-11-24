package com.hevo.study.sqlite.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hevo.study.sqlite.bean.DBPerson;
import com.hevo.study.sqlite.common.DBOpenHelper;

public class DBPersonService {
	
	private DBOpenHelper dbOpenHelper = null ;
	
	public DBPersonService(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}
	
	public void save(DBPerson person) {
		// dbOpenHelper.getWritableDatabase();   
		// dbOpenHelper能够缓存数据库实例，在此调用getWritableDatabase()后，会获得相同的对象，前提是同一个dbOpenHelper对象
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(" INSERT into person(person_name, cell_phone) VALUES(?,?) ", 
				new Object[]{ person.getName(), person.getPhone()});
//		db.close();		可以不加
	}
	
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(" DELETE from person WHERE person_id=? ", 
				new Object[]{ id});
	}
	
	public void update(DBPerson person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(" UPDATE person set person_name=?, cell_phone=? where person_id=? ", 
				new Object[]{ person.getName(), person.getPhone(), person.getId()});
	}
	
	/**
	 * dbOpenHelper.getReadableDatabase() 首先尝试调用dbOpenHelper.getWritableDatabase()并返回结果<br>
	 * 如果数据库的磁盘空间已满，则会返回<b>getReadableDatabase()--只读</b>的实例<br>
	 * 反之，如果磁盘未满，则返回getWritableDatabase()的实例
	 * @param id
	 * @return
	 */
	public DBPerson find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(" select * from person where person=? ", new String[]{ id.toString() });
		if(cursor.moveToFirst()) {
			DBPerson person = new DBPerson();
			person.setId(cursor.getString(cursor.getColumnIndex("person_id")));
			person.setName(cursor.getString(cursor.getColumnIndex("person_name")));
			person.setPhone(cursor.getString(cursor.getColumnIndex("cell_phone")));
			return person ;
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 分页查询，为ListView提供数据，绑定cursorAdapter
	 * @param offset
	 * @param maxResult
	 * @return
	 */
	public Cursor getScrollDataInCursor(int offset, int maxResult) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(" select person_id as _id, person_name, cell_phone from person order by person_id desc limit ?,? ",
				new String[]{ String.valueOf(offset), String.valueOf(maxResult) });
		return cursor;
	}
	
	public List<DBPerson> getScrollData(int offset, int maxResult) {
		List<DBPerson> personList = new ArrayList<DBPerson>();
		
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(" select * from person order by person_id desc limit ?,? ",
				new String[]{ String.valueOf(offset), String.valueOf(maxResult) });
		
		DBPerson person = null;
		while(cursor.moveToNext()){
			person = new DBPerson();
			person.setId(cursor.getString(cursor.getColumnIndex("person_id")));
			person.setName(cursor.getString(cursor.getColumnIndex("person_name")));
			person.setPhone(cursor.getString(cursor.getColumnIndex("cell_phone")));
			personList.add(person);
		}
		return personList;
	}
	
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(" select * from person ", null);
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
}
