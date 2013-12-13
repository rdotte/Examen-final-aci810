package com.androidrobertofutbol;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class JugadoresActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jugadores);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jugadores, menu);
		return true;
	}
	
	public void favoritosclick(View view) {
        Intent favoritos = new Intent(this, EncuestaActivity.class);
        this.startActivity(favoritos);
}

}
