package com.example.baumarkt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.baumarkt.model.Hauptkategorie;
import com.example.test.R;

public class Search extends Activity implements
	OnItemSelectedListener {

	
	EditText search;
	ImageButton searchButton;
	ImageButton deleteButton;
	String text;
	DataBaseHelper myDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		System.out.println("Start Search!");
		
		
		search = (EditText) findViewById(R.id.searchField);
		searchButton = (ImageButton) findViewById(R.id.imageButton1);
		deleteButton = (ImageButton) findViewById(R.id.imageButton2);
		
		myDbHelper = new DataBaseHelper(this);
	 
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
	 	
	
	 	deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				search.setText("");
			}
		});
	 	
	 	searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
				 text = search.getText().toString();
//				loadSearch(text);
				 
					Intent openStartingPoint = new Intent("com.example.test.MAINACTIVITY");
					startActivity(openStartingPoint);
				DataBaseHelper db = new DataBaseHelper(getApplicationContext());
				

		        Spinner spinnerHauptkategorie = (Spinner) findViewById(R.id.spinner1);
		        
		        List<Hauptkategorie> hauptkategorien =  new ArrayList<Hauptkategorie>(db.searchHauptkategorie(text));
		         
		        if (hauptkategorien.size() >= 1) {
		        	Hauptkategorie searchResult = hauptkategorien.get(0);
			        for (int i = 0 ; i < spinnerHauptkategorie.getAdapter().getCount(); i++) {
			        	Hauptkategorie h  = (Hauptkategorie) spinnerHauptkategorie.getItemAtPosition(i);
			        	System.out.println("Check: " + h);
			        	if (h.equals(searchResult)) {
			        		System.out.println("Found on Index: "  + i);
			        		spinnerHauptkategorie.setSelection(i);
			        	}
			        }
			        
			        Spinner spinnerUnterkategorie = (Spinner) findViewById(R.id.spinner2);
			        Spinner spinnerProduktkategorie = (Spinner) findViewById(R.id.spinner3);
		        }
		        
		        
				
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("SFGSG");
				};
			}
		});
	}	
	
	  private void loadSearch(String text) {
		  System.out.println("Start search....");
	        // database handler
		  DataBaseHelper db = new DataBaseHelper(getApplicationContext());
	 
	        // Spinner Drop down elements
//	        List<Hauptkategorie> hauptkategorien = new ArrayList<Hauptkategorie>(db.getAllHauptkategorien());
	 
	        // Creating adapter for spinner
//	        ArrayAdapter<Hauptkategorie> dataAdapter = new ArrayAdapter<Hauptkategorie>(this,
//	                android.R.layout.simple_spinner_item, hauptkategorien);
	 
	        // Drop down layout style - list view with radio button
//	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
		  System.out.println("Search Done!");
		  
	  }
	
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
