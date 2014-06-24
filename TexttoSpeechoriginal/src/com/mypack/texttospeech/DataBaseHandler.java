package com.mypack.texttospeech;


import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler {

	public static final String DataBase_Name="Writing_practises11";
	public static final int DATABASE_VERSION=1;
	public static final String Writing_practise="Writingpractise";
	
	public static final String Username="name";

	public static final String Create_Plan_table="create table " + Writing_practise + "(name text);";

	
	DataBaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;

	public DataBaseHandler(Context ctx)
	{
		this.ctx=ctx;
		dbhelper=new DataBaseHelper(ctx);
	}


	private static class DataBaseHelper extends SQLiteOpenHelper
	{
		public DataBaseHelper(Context ctx)
		{
			super(ctx,DataBase_Name,null,DATABASE_VERSION);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS " + Writing_practise);
			
			onCreate(db);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{		
			try 
			{
				db.execSQL(Create_Plan_table);

				ContentValues content=new ContentValues();
				content.put(Username, "null");
				
				db.insertOrThrow(Writing_practise, null, content);
			
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void close()
	{
		dbhelper.close();
	}

	public DataBaseHandler open() {
		db=dbhelper.getWritableDatabase();
		return this;
	}
	
//	public long insertName(String name)
//	{
//		long id = 0;
//		try{
//			open();
//			ContentValues content=new ContentValues();
//			content.put(Username, name);
//
//			id = db.insertOrThrow(Writing_practise, null, content);
//		}catch(Exception ex){
//			Log.d("EXCEPTION", ex.getMessage());
//		}		
//		return id;
//	}

	public Cursor getname(String gname) {
		// TODO Auto-generated method stub
		return db.rawQuery("select * from Writingpractise", null);
		
	}

//	public void update(String text) {
//		try{
//		this.open();
//		String selectQuery ="UPDATE Writing_practise SET name='"+text+"'";
//		Cursor cursor =db.rawQuery(selectQuery, null);
//		System.out.println("updated1:"+cursor.getCount());
//		}catch(Exception ex){
//			Log.i("DatabaseError", ex.getMessage());
//		}	
//	}
	public void update(String name1) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put(Username, name1);
		db.update(Writing_practise, values, null, null);
	}


}
