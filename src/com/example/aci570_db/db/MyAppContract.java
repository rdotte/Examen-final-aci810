package com.example.aci570_db.db;

import android.provider.BaseColumns;

public final class MyAppContract {

	public MyAppContract() {
		// this empty constructor prevent accidentally instantiating the contract class
	}
	
	public static abstract class Pais implements BaseColumns {
		public static final String TABLE_NAME = "pais";
		public static final String COLUMN_PAIS = "nombre_pais";
		public static final String COLUMN_RANKING = "ranking";
		public static final String COLUMN_MUNDIAL= "mundiales_ganados";
	}
}
