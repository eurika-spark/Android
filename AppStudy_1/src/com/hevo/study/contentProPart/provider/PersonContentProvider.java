package com.hevo.study.contentProPart.provider;

import org.apache.commons.lang3.StringUtils;

import com.hevo.study.sqlite.common.DBOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonContentProvider extends ContentProvider {

	private DBOpenHelper dbOpenHelper = null;
	
	private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	
	private static final int PERSON_S = 1;
	private static final int PERSON = 0;
	
	static {
		MATCHER.addURI("com.hevo.study.providers.personprovider", "person", PERSON_S);
		// # 代表数字 		*代表所有字符
		MATCHER.addURI("com.hevo.study.providers.personprovider", "person/#", PERSON);
	}
	
	@Override
	public boolean onCreate() {
		dbOpenHelper = new DBOpenHelper(getContext());
		return true;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		
		switch (MATCHER.match(uri)) {
		case PERSON_S:
				
			long rawid = db.insert("person", "default_name", values);
			// uri : content://com.hevo.study.providers.personprovider/person
			Uri insertUri = ContentUris.withAppendedId(uri, rawid);
			return insertUri;

		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int num = 0;
		
		switch (MATCHER.match(uri)) {
		// delete all data in Person table
		case PERSON_S:	
			num = db.delete("person", selection, selectionArgs);
			break;
			
		// delete one person data with specified
		case PERSON:
			// 获得person/# 后面的ID
			long rowId = ContentUris.parseId(uri);
			String where = " person_id=" + rowId; 
			if(StringUtils.isNotBlank(selection)) {
				where += " and " + selection;
			}
			num = db.delete("person", where, selectionArgs);
			break;
			
		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
				
		return num;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (MATCHER.match(uri)) {
		case PERSON_S:
			return db.query("person", projection, selection, selectionArgs, null, null, sortOrder); 

		case PERSON:
			// 获得person/# 后面的ID
			long rowId = ContentUris.parseId(uri);
			String where = " person_id=" + rowId; 
			if(StringUtils.isNotBlank(selection)) {
				where += " and " + selection;
			}
			return db.query("person", projection, where, selectionArgs, null, null, sortOrder); 
			
		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int num = 0;
		
		switch (MATCHER.match(uri)) {
		// update all data in Person table
		case PERSON_S:	
			num = db.update("person", values, selection, selectionArgs);
			break;
			
		// update one person data with specified
		case PERSON:
			// 获得person/# 后面的ID
			long rowId = ContentUris.parseId(uri);
			String where = " person_id=" + rowId; 
			if(StringUtils.isNotBlank(selection)) {
				where += " and " + selection;
			}
			num = db.update("person", values, where, selectionArgs);
			break;
			
		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
				
		return num;
	}

}
