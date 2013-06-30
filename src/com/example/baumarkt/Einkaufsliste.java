package com.example.baumarkt;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baumarkt.model.Position;
import com.example.test.R;

/**
 * Darstellung des Warenkorbs
 *
 */
public class Einkaufsliste extends Activity {
	
	private GridView contentGrid;
	private GridView headlineResultGrid;
	private DecimalFormat nf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		nf = new DecimalFormat("##.##");
		System.out.println("Activity Einkaufsliste started....");
		setContentView(R.layout.activity_einkaufsliste);
		
		// Tabelle für die Spaltenbeschriftungen
		headlineResultGrid = (GridView) findViewById(R.id.gridView1);
		contentGrid = (GridView) findViewById(R.id.gridView2);
		TextView test = (TextView) findViewById(R.id.textView1);
		System.out.println(test.getText());
		
		String[] headlineResult = new String[4];
		
		headlineResult[0] = "Position";
		headlineResult[1] = "Beschreibung";
		headlineResult[2] = "Menge";
		headlineResult[3] = "Gesamtpreis";
		
		ArrayAdapter<String> resultHeadlineAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, headlineResult);
		headlineResultGrid.setAdapter(resultHeadlineAdapter);
		
		headlineResultGrid.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
			}
		});
		
		
		//Selektierte alle Einträge aus dem Objekt Einkaufszettel, welches während des gesamten Programms mitgeführt wird:
		String[] contentList = new String[StartPoint.WARENKORB.getBestellliste().size() * 4];
		int position = 1;
		int count = 0;
		// Füllen des Arrays für die Darstellung des Warenkorbs. Position 1, Position, 2: Berzeichnung, 3: Menge, 4: Summe des Preises
		for (Position p : StartPoint.WARENKORB.getBestellliste()) {
			contentList[count] = String.valueOf(position);
			contentList[++count] = p.getArtikel().getBezeichnung();
			contentList[++count] = String.valueOf(p.getMenge());
			// Ermittlung des Gesamtpreises, da in einer Position der Artikel mehrfach drin sein kann:
			contentList[++count] = nf.format(p.getArtikel().getPreis() * p.getMenge()) + " €";
			count++;
			position++;
		}
		
		
		// Tabelle contentGrid mit den Inhalten des Warenkorbs
		ArrayAdapter<String> resultContentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contentList);
		contentGrid.setAdapter(resultContentAdapter);
		
		contentGrid.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				System.out.println("Pressed on Warenkorb: pos: " + pos + " / " + id);
				Bundle b = new Bundle();
				List<Position> bestellliste = StartPoint.WARENKORB.getBestellliste();
				Position p = bestellliste.get(pos / 4);
				b.putInt("ARTICLE_ID", p.getArtikel().getId());
				System.out.println("Warenkorb: put ID " + p.getArtikel().getId() + " to Map");
				try{
					Intent openStartingPoint = new Intent("com.example.test.MAP");
					openStartingPoint.putExtras(b);
					startActivity(openStartingPoint);
					}catch(Exception e){
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
			}
		});
		
		
		Toast.makeText(getApplicationContext(), "Klicken Sie auf die Position um den Standort anzuzeigen", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.einkaufsliste, menu);
		return true;
	}

}
