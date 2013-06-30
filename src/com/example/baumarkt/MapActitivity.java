package com.example.baumarkt;

import java.io.IOException;
import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.baumarkt.model.Artikel;
import com.example.test.R;

public class MapActitivity extends Activity {
	
	private Properties coords;
	private Artikel artikel;
	
	private int[] getPoint(String key) throws IllegalArgumentException {
		System.out.println("Map.getPoint search for: " + key);
		int[] result = new int[2];
		if (coords != null) {
			String value = coords.getProperty(key);
			String[] point = value.split(",");
			if (point.length != 2) {
				throw new IllegalArgumentException("More than one point for key " + key + " in properties for coords");
			}
			
			try {
				result[0] = Integer.parseInt(point[0]);
			} catch (Exception e) {
				System.out.println(point[0] + " cannot be parsed into an int");
				throw new IllegalArgumentException(point[0] + " cannot be parsed into an int");
			}
			try {
				result[1] = Integer.parseInt(point[1]);
			} catch (Exception e) {
				System.out.println(point[1] + " cannot be parsed into an int");
				throw new IllegalArgumentException(point[1] + " cannot be parsed into an int");
			}
			
			return result;
		}
		else {
			return null;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_actitivity);
		coords = new Properties();
		try {
			coords.load(this.getResources().getAssets().open("coords.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Bundle input = getIntent().getExtras();
		if (input != null) {
			int artikelid = input.getInt("ARTICLE_ID");
			System.out.println("Map with Artikelid: "+ artikelid);
			
			 DataBaseHelper myDbHelper = new DataBaseHelper(this);
			 artikel = myDbHelper.getArtikelById(artikelid);
			 System.out.println("Map: Artikel found: " + artikel);
		}
		
		int[] standort = getPoint(artikel.getStandort().trim());
		System.out.println("Markiere Standort: " + standort[0] + " / " + standort[1]);
		
		DrawView drawView = new DrawView(this);
//		drawView.setBackgroundColor(Color.WHITE);
		drawView.setBackground(this.getResources().getDrawable(this.getResources().getIdentifier("backgroundbaumarkt", "drawable", "com.example.test")));
		drawView.newDrawPositionCircle(standort[0], standort[1]);
		setContentView(drawView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_actitivity, menu);
		return true;
	}
	
	
	
	public class DrawView extends View {
		
		private Paint mPaint;
		private int circleX;
		private int circleY;
		
		public DrawView(Context context) {
			super(context);
			circleX = 100;
			circleY = 100;
			mPaint = new Paint();
		}
		
		public void newDrawPositionCircle(int x, int y) {
			circleX = x;
			circleY = y;
		}
		
		@Override
		public void onDraw(Canvas canvas) {
			mPaint.setColor(Color.RED);
			canvas.drawCircle(circleX, circleY, 10, mPaint);
		}
	}
}


