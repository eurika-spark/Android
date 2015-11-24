package com.hevo.study.sqlite.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
//		super(context, dbName, factory, version);
		super(context, "appStudy.db", null, 2);		// null 表示使用默认的游标工厂
	}

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	// 数据库每一次被创建的时候
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("	CREATE TABLE person (	");
		sb.append("	person_id integer PRIMARY KEY AUTOINCREMENT,	");
		sb.append("	person_name VARCHAR	, ");
		sb.append("	cell_phone VARCHAR NULL	)	");
		db.execSQL(sb.toString());
	}

	// 每次版本号更新的时候调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL(" ALERT TABLE person ADD gender VARCHAR NULL ");
	}

}
