package com.example.aci570_db.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aci570_db.db.MyAppContract.Pais;
import com.example.aci570_db.model.Team;

public class MyAppDataSource {

	private MyAppDbHelper dbHelper;
	private SQLiteDatabase db;
	
	private String[] allColumns = {
		    Pais._ID,
		    Pais.COLUMN_PAIS,
		    Pais.COLUMN_RANKING,
		    Pais.COLUMN_MUNDIAL,
		    };

	public MyAppDataSource(Context context) {
		this.dbHelper = new MyAppDbHelper(context);
	}
	
	public void open() throws SQLException {
		this.db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public Team createTeam(String nombrePais, String ranking, String mundialesGanados) {
		ContentValues values = new ContentValues();
		values.put(Pais.COLUMN_PAIS, nombrePais);
		values.put(Pais.COLUMN_RANKING, ranking);
		values.put(Pais.COLUMN_MUNDIAL, mundialesGanados);
		
	    long insertId = db.insert(Pais.TABLE_NAME, null, values);
	    
	    Cursor c = db.query(
	    		Pais.TABLE_NAME,
	    		this.allColumns, Pais._ID + " = " + insertId, 
	    		null,
	    		null, 
	    		null, 
	    		null
	    	);
	    c.moveToFirst();
	    
	    Team t = cursorToTeam(c);
	    c.close();
	    
	    return t;
	}
	
	public Team updateTeam(Team t, String nombrePais, String ranking, String mundialesGanados) {
		ContentValues values = new ContentValues();
		values.put(Pais.COLUMN_PAIS, nombrePais);
		values.put(Pais.COLUMN_RANKING, ranking);
		values.put(Pais.COLUMN_MUNDIAL, mundialesGanados);
	
		
	    db.update(Pais.TABLE_NAME, values, Pais._ID + " = " + t.getId(), null);
	    
	    t.setNombrePais(nombrePais);
	    t.setRanking(ranking);
	    t.setMundialesGanados(mundialesGanados);

	    
	    return t;
	}
	
	public List<Team> getPais() {
	    List<Team> pais = new ArrayList<Team>();
	    
	    String sortOrder = Pais.COLUMN_PAIS + " DESC";
	    
	    Cursor c = db.query(
			    Pais.TABLE_NAME,	// The table to query
			    this.allColumns,			// The columns to return
			    null,				// The columns for the WHERE clause
			    null,				// The values for the WHERE clause
			    null,				// don't group the rows
			    null,				// don't filter by row groups
			    sortOrder			// The sort order
		    );

	    c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Team p = cursorToTeam(c);
	      pais.add(p);
	      c.moveToNext();
	    }
	    
	    // make sure to close the cursor
	    c.close();
	    
	    return pais;
	}
	
	public Team deleteTeam(Team t) {
	    long id = t.getId();
	    db.delete(Pais.TABLE_NAME, Pais._ID + " = " + id, null);
	    t.setId(0);
	    return t;
	}

	
	private Team cursorToTeam(Cursor cursor) {
		Team t = new Team();
	    t.setId(cursor.getLong(0));
	    t.setNombrePais(cursor.getString(1));
	    t.setRanking(cursor.getString(2));
	    t.setMundialesGanados(cursor.getString(3));
	    return t;
	}
}
