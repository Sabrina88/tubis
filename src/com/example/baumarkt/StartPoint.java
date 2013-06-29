package com.example.baumarkt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.baumarkt.model.Einkaufszettel;
import com.example.test.R;


public class StartPoint extends Activity{
	
	private ImageButton category,search, einkaufsliste; 
	public static Einkaufszettel WARENKORB = new Einkaufszettel();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Start Baumarkt App!");
		setContentView(R.layout.choice);
	
        
        
	
		category = (ImageButton) findViewById(R.id.imageButton1);
		search = (ImageButton) findViewById(R.id.imageButton2);
		einkaufsliste = (ImageButton) findViewById(R.id.imageButton3);
		
		
		category.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					Intent openStartingPoint = new Intent("com.example.test.MAINACTIVITY");
					startActivity(openStartingPoint);
					}catch(Exception e){
						System.out.println(e.getMessage());
						e.printStackTrace();
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
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}
		});
		
		einkaufsliste.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try{
				Intent openStartingPoint = new Intent("com.example.test.EINKAUFSLISTE");
				startActivity(openStartingPoint);
				}catch(Exception e){
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}
				
		});
}
	
}
