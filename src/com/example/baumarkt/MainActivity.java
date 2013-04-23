package com.example.baumarkt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.baumarkt.model.Artikel;
import com.example.baumarkt.model.Hauptkategorie;
import com.example.baumarkt.model.Produktkategorie;
import com.example.baumarkt.model.Unterkategorie;
import com.example.test.R;

public class MainActivity extends Activity implements
OnItemSelectedListener {

	 // Spinner element
	Spinner spinnerHauptkategorie, spinnerUnterkategorie, spinnerProduktkategorie;
	
	// String      onSelect;
	TextView tv;
	TableRow tableRow1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 DataBaseHelper myDbHelper = new DataBaseHelper(this);
	 
	        try {
	 
	        	myDbHelper.createDataBase();
	 
	 	} catch (IOException ioe) {
	 
	 		throw new Error("Unable to create database");
	 
	 	}
	 
	 	try {
	 
	 		myDbHelper.openDataBase();
	 
	 	}catch(SQLException sqle){
	 
	 		throw sqle;
	 
	 	}

        spinnerHauptkategorie = (Spinner) findViewById(R.id.spinner1);
        spinnerUnterkategorie = (Spinner) findViewById(R.id.spinner2);
        spinnerProduktkategorie = (Spinner) findViewById(R.id.spinner3);
 
 
        // Loading spinner data from database
        loadSpinnerHauptkategorien(); 
        
        tv= (TextView) findViewById(R.id.textView1);
  //     tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        
        
//        TableLayout tl = (TableLayout)findViewById(R.id.myLayout);
//        /* Create a new row to be added. */
//        TableRow tr = new TableRow(this);
//        tr.setLayoutParams(new LayoutParams(
//                       LayoutParams.FILL_PARENT,
//                       LayoutParams.WRAP_CONTENT));
//             /* Create a Button to be the row-content. */
//             Button b = new Button(this);
//             b.setText("Dynamic Button");
//             b.setLayoutParams(new LayoutParams(
//                       LayoutParams.FILL_PARENT,
//                       LayoutParams.WRAP_CONTENT));
//             /* Add Button to row. */
//             tr.addView(b);
//   /* Add row to TableLayout. */
//   tl.addView(tr,new TableLayout.LayoutParams(
//             LayoutParams.FILL_PARENT,
//             LayoutParams.WRAP_CONTENT));
	 	
       // Spinner click listener
  //      spinner.setOnItemSelectedListener((OnItemSelectedListener) this);
        
        
        spinnerHauptkategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//               String s = item.toString();
                Hauptkategorie hk = (Hauptkategorie) item;
                loadSpinnerUnterkategorien(hk);
//                showData(s);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        
        spinnerUnterkategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//               String s = item.toString();
                Unterkategorie uk = (Unterkategorie) item;
                loadSpinnerProduktkategorien(uk);
//                showData(s);
            }

			public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        
        spinnerProduktkategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//               String s = item.toString();
                Produktkategorie pk = (Produktkategorie) item;
                showData(pk);
//                showData(s);
            }

			public void onNothingSelected(AdapterView<?> parent) {
            }
        });
       
       
	}              
        
	

	

	  /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerHauptkategorien() {
        // database handler
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
 
        // Spinner Drop down elements
        List<Hauptkategorie> hauptkategorien = new ArrayList<Hauptkategorie>(db.getAllHauptkategorien());
 
        // Creating adapter for spinner
        ArrayAdapter<Hauptkategorie> dataAdapter = new ArrayAdapter<Hauptkategorie>(this,
                android.R.layout.simple_spinner_item, hauptkategorien);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerHauptkategorie.setAdapter(dataAdapter);       
    }
    
    
    /**2 spinner2
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerUnterkategorien(Hauptkategorie hk) {
        // database handlerS
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        
        // Spinner Drop down elements
       
       
        System.out.println("Select Unterkategorien mit: " + hk.getId());
        List<Unterkategorie> unterkategorien = new ArrayList<Unterkategorie>(db.getUnterkategorien(hk));
 
        // Creating adapter for spinner
        ArrayAdapter<Unterkategorie> dataAdapter = new ArrayAdapter<Unterkategorie>(this,
                android.R.layout.simple_spinner_item, unterkategorien);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerUnterkategorie.setAdapter(dataAdapter);
    }

    private void loadSpinnerProduktkategorien(Unterkategorie uk) {
    	// database handlerS
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        
        // Spinner Drop down elements
       
       
        System.out.println("Select Produktkategorie mit: " + uk.getId());
        List<Produktkategorie> produktkategorien = new ArrayList<Produktkategorie>(db.getProduktkategorie(uk));
 
        // Creating adapter for spinner
        ArrayAdapter<Produktkategorie> dataAdapter = new ArrayAdapter<Produktkategorie>(this,
                android.R.layout.simple_spinner_item, produktkategorien);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerProduktkategorie.setAdapter(dataAdapter);
		
	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
   
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
//	public void showData(String s){
//		DataBaseHelper db = new DataBaseHelper(getApplicationContext()); 
//	    db.openDataBase();
//		String data = db.getArtikel(s);
//		db.close();
//		tv.setText(data);
//	}
	
	public void showData(Produktkategorie pk){
		DataBaseHelper db = new DataBaseHelper(getApplicationContext()); 
	    db.openDataBase();
		Set<Artikel> artikel = new HashSet<Artikel>(db.getArtikel(pk));
		db.close();
		
		//TODO: ändern!
		
		StringBuilder sb = new StringBuilder();
		for (Artikel a : artikel) {
			sb.append(a.toString());
			sb.append("\n");
		}
		tv.setText(sb.toString());
			
	}
	
}
