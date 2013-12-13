package com.example.aci570_db.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.androidrobertofutbol.AplicacionFutbol;
import com.androidrobertofutbol.Equipos;
import com.example.aci570_db.model.Team;


public class ListViewItemClickListener implements AdapterView.OnItemClickListener {

	private Activity activity;
	
	public ListViewItemClickListener(Activity activity) {
		this.activity = activity;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Team p = (Team) parent.getItemAtPosition(position);
		
		if(p != null)
		{
			Intent i = new Intent(this.activity, Equipos.class);
			i.putExtra("team", p);
			this.activity.startActivityForResult(i, AplicacionFutbol.REQUEST_CODE_UPDATE_TEAM);			
		}
	}
}
