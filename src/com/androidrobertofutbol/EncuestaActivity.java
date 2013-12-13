package com.androidrobertofutbol;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class EncuestaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encuesta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favoritos, menu);
		return true;
	}

	public void mandardatos(View view) {
        Intent i = new Intent(this, AplicacionFutbol.class);
        Toast.makeText(this, "Formulario Enviado", Toast.LENGTH_LONG).show();
        this.startActivity(i);
}
}