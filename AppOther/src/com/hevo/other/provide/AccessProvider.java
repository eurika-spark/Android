package com.hevo.other.provide;

import java.util.Random;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class AccessProvider extends AndroidTestCase {

	public void testInsert() {
		Random rand = new Random();
		int intRand = rand.nextInt(1000);
		
		Uri uri = Uri.parse("content://com.hevo.study.providers.personprovider/person");
		
		ContentResolver resolver = this.getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("person_name", "Provider_" + intRand);
		values.put("cell_phone", "139-" + intRand + "-" + rand.nextInt(1000));
		resolver.insert(uri, values);
	}
	

	public void testDelete() {
		Uri uri = Uri.parse("content://com.hevo.study.providers.personprovider/person/4");
		ContentResolver resolver = this.getContext().getContentResolver();
		resolver.delete(uri, null, null);
	}
	
	public void testUpdate() {
		Random rand = new Random();
		int intRand = rand.nextInt(1000);
		Uri uri = Uri.parse("content://com.hevo.study.providers.personprovider/person/5");
		
		ContentResolver resolver = this.getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("cell_phone", "UPDATE:170-" + intRand + "-" + rand.nextInt(1000));
		resolver.update(uri, values, null, null);
	}
	
	public void testQuery() {
		Uri uri = Uri.parse("content://com.hevo.study.providers.personprovider/person");
		ContentResolver resolver = this.getContext().getContentResolver();
		
		Cursor cursor = resolver.query(uri, null, null, null, " person_id asc ");
		
		System.out.println("ID		person_name		cell_phone");
		while (cursor.moveToNext()) {
			String id = cursor.getString(cursor.getColumnIndex("person_id"));
			String name = cursor.getString(cursor.getColumnIndex("person_name"));
			String phone = cursor.getString(cursor.getColumnIndex("cell_phone"));
			System.out.println(id+"	" + name + "	" + phone);
		}
	}
	
}
