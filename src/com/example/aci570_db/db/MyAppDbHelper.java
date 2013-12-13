package com.example.aci570_db.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aci570_db.db.MyAppContract.Pais;

public class MyAppDbHelper extends SQLiteOpenHelper {

	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "team.db";
    
//  private static final String NULL_TYPE = " NULL";
	private static final String TEXT_TYPE = " TEXT";
//	private static final String INTEGER_TYPE = " INTEGER";
//	private static final String REAL_TYPE = " REAL";
//	private static final String BLOB_TYPE = " BLOB";
	
	private static final String COMMA_SEPARATOR = ",";
	
	private static final String SQL_CREATE_PAIS =
		    "CREATE TABLE " + Pais.TABLE_NAME + " (" +
		    Pais._ID + " INTEGER PRIMARY KEY," +
		    Pais.COLUMN_PAIS + TEXT_TYPE + COMMA_SEPARATOR +
		    Pais.COLUMN_RANKING + TEXT_TYPE + COMMA_SEPARATOR +
		    Pais.COLUMN_MUNDIAL + TEXT_TYPE + 
		    " )";
	
	private static final String SQL_DROP_PAIS=
		    "DROP TABLE IF EXISTS " + Pais.TABLE_NAME;
    
    public MyAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PAIS);
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	Log.w(
    			MyAppDbHelper.class.getName(),
    			"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data"
    	);

    	db.execSQL(SQL_DROP_PAIS);
    	onCreate(db);
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
