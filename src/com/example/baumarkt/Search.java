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
import android.widget.Toast;

import com.example.baumarkt.model.Artikel;
import com.example.baumarkt.model.Hauptkategorie;
import com.example.baumarkt.model.Produktkategorie;
import com.example.baumarkt.model.Unterkategorie;
import com.example.test.R;

public class Search extends Activity implements
	OnItemSelectedListener {

	public static String KEY_HAUPTKATEGORIE = "hauptkategorie";
	public static String KEY_UNTERKATEGORIE = "unterkategorie";
	public static String KEY_PRODUKTKATEORIE = "produktkategorie";
	
	
	private EditText search;
	private ImageButton searchButton;
	private ImageButton deleteButton;
	private String text;
	private DataBaseHelper myDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		System.out.println("Start Search!");
		Toast.makeText(getApplicationContext(), "Bitte Suchtext eingeben und >>Suche<< Klicken", Toast.LENGTH_LONG).show();
		
		
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
				 
					DataBaseHelper db = new DataBaseHelper(getApplicationContext());
					List<Hauptkategorie> hauptkategorien =  new ArrayList<Hauptkategorie>(db.searchHauptkategorie(text));
					Intent openStartingPoint = new Intent("com.example.test.MAINACTIVITY");
					Bundle b = new Bundle();
				 
					
					// Suchen in den Kategrien nach dem Suchwort. Es geht von "oben" nach "unten". Zuerst werden die Hauptkategorien durchsucht
					// dann die Unterkatagorien, dann die Produktkategorien und dan die Artikel.
					if (hauptkategorien.size() > 0) {
						b.putInt(KEY_HAUPTKATEGORIE, hauptkategorien.get(0).getId());
						openStartingPoint.putExtras(b);
						startActivity(openStartingPoint);
					}
					else {
						List<Unterkategorie> unterkategorien =  new ArrayList<Unterkategorie>(db.searchUnterkategorie(text));
						
						if (unterkategorien.size() > 0) {
							b.putInt(KEY_UNTERKATEGORIE, unterkategorien.get(0).getId());
							b.putInt(KEY_HAUPTKATEGORIE, unterkategorien.get(0).getHauptkategorie().getId());
							openStartingPoint.putExtras(b);
							startActivity(openStartingPoint);
						}
						else
						{
							List<Produktkategorie> produktkategorien =  new ArrayList<Produktkategorie>(db.searchProduktkategorie(text));
							if (produktkategorien.size() > 0) {
								b.putInt(KEY_PRODUKTKATEORIE, produktkategorien.get(0).getId());
								b.putInt(KEY_HAUPTKATEGORIE, produktkategorien.get(0).getUnterkategorien().getHauptkategorie().getId());
								b.putInt(KEY_UNTERKATEGORIE, produktkategorien.get(0).getUnterkategorien().getId());
								openStartingPoint.putExtras(b);
								startActivity(openStartingPoint);	
							}
							else
							{
								List<Artikel> artikel =  new ArrayList<Artikel>(db.searchArtikel(text));
								if (artikel.size() > 0) {
									b.putInt(KEY_PRODUKTKATEORIE, artikel.get(0).getProduktkategorie().getId());
									b.putInt(KEY_HAUPTKATEGORIE, artikel.get(0).getProduktkategorie().getUnterkategorien().getHauptkategorie().getId());
									b.putInt(KEY_UNTERKATEGORIE, artikel.get(0).getProduktkategorie().getUnterkategorien().getId());
									openStartingPoint.putExtras(b);
									startActivity(openStartingPoint);	
								}
								else
								{
									Toast.makeText(getApplicationContext(), "Es wurden keine Kategorien für das Suchkriterium gefunden",Toast.LENGTH_LONG).show();

								}
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("SFGSG");
				};
			}
		});
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
