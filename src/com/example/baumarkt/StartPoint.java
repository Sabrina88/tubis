package com.example.baumarkt;

import com.example.test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StartPoint extends Activity{
	
	Button category,search; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice);
	
		
	
	category = (Button) findViewById(R.id.category);
	search = (Button) findViewById(R.id.search);
	
	
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
