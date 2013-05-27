package com.example.baumarkt;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.baumarkt.model.Hauptkategorie;
import com.example.baumarkt.model.Produktkategorie;
import com.example.baumarkt.model.Unterkategorie;
import com.example.test.R;


public class StartPoint extends Activity{
	
	ImageButton category,search; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Start Baumarkt App!");
		setContentView(R.layout.choice);
	
        
        
	
		category = (ImageButton) findViewById(R.id.imageButton1);
		search = (ImageButton) findViewById(R.id.imageButton2);
		
		
					category.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							try{
								Intent openStartingPoint = new Intent("com.example.test.MAINACTIVITY");
								startActivity(openStartingPoint);
								}catch(Exception e){
									System.out.println("SFGSG");
								}
						}
					});
		
	
	
		search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
				Intent openStartingPoint = new Intent("com.example.test.SEARCH");
				startActivity(openStartingPoint);
				}catch(Exception e){
					System.out.println("SFGSG");
				}
				
			}
		});
}
	
}
