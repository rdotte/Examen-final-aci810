package com.androidrobertofutbol;

import com.example.aci570_db.db.MyAppDataSource;
import com.example.aci570_db.model.Team;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


@SuppressLint("NewApi")
public class Equipos extends Activity {

        private MyAppDataSource ds;
        private Team teamToUpdate;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_equipos);
                // Show the Up button in the action bar.
                setupActionBar();
                
                ds = new MyAppDataSource(this);
            ds.open();
            
            Intent i = this.getIntent();
            
            if(i.hasExtra(AplicacionFutbol.EXTRA_TEAM))
            {
                   Team  t = (Team) i.getSerializableExtra(AplicacionFutbol.EXTRA_TEAM);
                    
                    EditText paisEquipo = (EditText) this.findViewById(R.id.paisEquipo);
                        paisEquipo.setText(t.getNombrePais());
                        
                        EditText rankingEquipo = (EditText) this.findViewById(R.id.rankingEquipo);
                        rankingEquipo.setText(t.getRanking());
                        
                        EditText mundialGanado = (EditText) this.findViewById(R.id.mundialesEquipo);
                        mundialGanado.setText(t.getMundialesGanados());
                        
                        
                        
                        Button saveButton = (Button) this.findViewById(R.id.saveButton);
                        saveButton.setText("Update");
                        
                        Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
                        deleteButton.setVisibility(Button.VISIBLE);
                        deleteButton.setText("Delete");
                        
                        this.setTitle("Update Team");
                        
                        this.teamToUpdate = t;
            }
            else
            {
                    Button saveButton = (Button) this.findViewById(R.id.saveButton);
                    saveButton.setText("Create");
                    
                    Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
                    deleteButton.setVisibility(Button.INVISIBLE);
                    
                    this.setTitle("Create Team");
                    
                    this.teamToUpdate = null;
            }
        }

        /**
         * Set up the {@link android.app.ActionBar}.
         */
        @SuppressLint("NewApi")
        private void setupActionBar() {

                getActionBar().setDisplayHomeAsUpEnabled(true);

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.aplicacion_futbol, menu);
                return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                case android.R.id.home:
                        // This ID represents the Home or Up button. In the case of this
                        // activity, the Up button is shown. Use NavUtils to allow users
                        // to navigate up one level in the application structure. For
                        // more details, see the Navigation pattern on Android Design:
                        //
                        // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                        //
                        NavUtils.navigateUpFromSameTask(this);
                        return true;
                }
                return super.onOptionsItemSelected(item);
        }
        
        
		public void saveteam(View view) {
        	
                EditText paisEquipo = (EditText) this.findViewById(R.id.paisEquipo);
                String pais = paisEquipo.getText().toString();
                
                EditText rankingEquipo = (EditText) this.findViewById(R.id.rankingEquipo);
                String ranking = rankingEquipo.getText().toString();
                
                EditText mundialesEquipo = (EditText) this.findViewById(R.id.mundialesEquipo);
                String mundiales = mundialesEquipo.getText().toString();
                
                if(pais.isEmpty() || ranking.isEmpty() || mundiales.isEmpty())
                {
                        Toast.makeText(this, "Complete the form before saving", Toast.LENGTH_LONG).show();
                        return;
                }
                
                Team t = null;
                
                if(this.teamToUpdate != null)
                {
                        t = ds.updateTeam(this.teamToUpdate, pais, ranking, mundiales);
                }
                else
                {
                        t = ds.createTeam(pais, ranking, mundiales);
                }
                
                Intent i = new Intent();
                i.putExtra(AplicacionFutbol.EXTRA_TEAM, t);
                i.putExtra(AplicacionFutbol.EXTRA_REMOVE, false);
                this.setResult(RESULT_OK, i);
                
                this.finish();
        }
        
        public void deleteteam(View view) {
                
                Team t = ds.deleteTeam(this.teamToUpdate);
                
                Intent i = new Intent();
                i.putExtra(AplicacionFutbol.EXTRA_TEAM, t);
                i.putExtra(AplicacionFutbol.EXTRA_REMOVE, true);
                this.setResult(RESULT_OK, i);
                
                this.finish();
        }
        
        
        @Override
        protected void onResume() {
                ds.open();
                super.onResume();
        }

        @Override
        protected void onPause() {
                ds.close();
                super.onPause();
        }
}