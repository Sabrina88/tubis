package com.example.baumarkt;

import java.io.IOException;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.test.R;

public class Search extends Activity implements
	OnItemSelectedListener {

	
	EditText search;
	ImageButton searchButton;
	ImageButton deleteButton;
	String text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		System.out.println("Start Search!");
		
		
		search = (EditText) findViewById(R.id.searchField);
		searchButton = (ImageButton) findViewById(R.id.imageButton2);
		deleteButton = (ImageButton) findViewById(R.id.imageButton1);
		
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
	 	
	
	 	
	 	searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
				 text = search.getText().toString();
				loadSearch(text);
				}catch(Exception e){
					System.out.println("SFGSG");
				};
				
			}
		});
	 	
	}	
	
	  private void loadSearch(String text) {
		  System.out.println("Start search....");
	        // database handler
	/*        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
	 
	        // Spinner Drop down elements
	        List<Hauptkategorie> hauptkategorien = new ArrayList<Hauptkategorie>(db.getAllHauptkategorien());
	 
	        // Creating adapter for spinner
	        ArrayAdapter<Hauptkategorie> dataAdapter = new ArrayAdapter<Hauptkategorie>(this,
	                android.R.layout.simple_spinner_item, hauptkategorien);
	 
	        // Drop down layout style - list view with radio button
	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
	        spinnerHauptkategorie.setAdapter(dataAdapter);      
	   */
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
