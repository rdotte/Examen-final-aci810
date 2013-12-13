package com.androidrobertofutbol;

import com.example.examen.helpers.PreferencesHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences sharedPref = getSharedPreferences("app-data",Context.MODE_PRIVATE);
        Boolean isLoggedIn = sharedPref.getBoolean(PreferencesHelper.IS_LOGGED_IN_KEY, false);
        Boolean isRegistered = sharedPref.getBoolean(PreferencesHelper.IS_REGISTERES_KEY, false);
        
        if(!isRegistered)
        {
        	Intent register = new Intent(this, RegisterUser.class);
        	this.startActivity(register);
        }
        else if(!isLoggedIn)
        {
        	Intent login = new Intent(this, InicioUser.class);
        	this.startActivity(login);
        }
        else
        {
        	Intent app = new Intent(this, AplicacionFutbol.class);
        	this.startActivity(app);
        }
    }

    
}
