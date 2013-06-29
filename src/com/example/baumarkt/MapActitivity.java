package com.example.baumarkt;

import com.example.test.R;
import com.example.test.R.layout;
import com.example.test.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapActitivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_actitivity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_actitivity, menu);
		return true;
	}

}
