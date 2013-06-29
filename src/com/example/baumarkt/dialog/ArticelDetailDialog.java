package com.example.baumarkt.dialog;

import java.lang.reflect.Field;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baumarkt.DataBaseHelper;
import com.example.baumarkt.model.Artikel;
import com.example.test.R;

/**
 * Dialog für die Detailansicht eines Artikels
 * 
 */
public class ArticelDetailDialog extends DialogFragment {
	
	// Alle relevanten Attribute für den Artikel
	private TextView textArtikelname;
	private TextView textPreis;
	private TextView textStandort;
	private TextView textBeschreibung;
	
	@Override
	public Dialog onCreateDialog(Bundle savedState) {
		Bundle args = getArguments();
		
		// hole die ArtikelID die beim Aufruf des Dialogs übergeben werden mus:
		int artikelid = args.getInt("ARTICLE_ID");
		
		System.out.println("Open Dialog for Artickel with ID: " + artikelid);
		
		// Aufbau des individuellen Dialogs
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.articel_detail_view, null);
		builder.setView(v);
		
		// Holen der einzelnen GUI Elemente für den dynamischen Text, der gesetzt werden soll:
		textArtikelname = (TextView) v.findViewById(R.id.textViewArtikelname);
		textPreis = (TextView) v.findViewById(R.id.textViewPreis);
		textStandort = (TextView) v.findViewById(R.id.textViewStandort);
		textBeschreibung = (TextView) v.findViewById(R.id.textViewBeschreibung);
		
		// Laden des Artikels über seine ID aus der Datenbank
		DataBaseHelper db = new DataBaseHelper(getActivity()); 
	    db.openDataBase();
	    Artikel artikel = db.getArtikelById(artikelid);
	    
	    textArtikelname.setText(artikel.getBezeichnung());
	    textPreis.setText(artikel.getPreis() + "€");
	    textStandort.setText(artikel.getStandort());
	    
	    if (artikel.getBeschreibung() == null || artikel.getBeschreibung().length() == 0) {
	    	textBeschreibung.setText("<Beschreibung fehlt>");
	    }
	    else {
	    	textBeschreibung.setText(artikel.getBeschreibung());
	    }
		
	    // Setzen des Bildes des Artikels
	    ImageView image = (ImageView) v.findViewById(R.id.imageView1);
	    image.setImageResource(R.drawable.keinbild);
	    // Holen aller verfügbaren Bilder für diese APP:
//	    Field[] drawables = android.R.drawable.class.getFields();
//	    for (Field f : drawables) {
//	        try {
//	            System.out.println("R.drawable." + f.getName());
//	            String searchString = f.getName().substring(0, f.getName().indexOf("."));
//	            System.out.println(searchString + " / " + artikel.getBildname());
//	            if(searchString.equals(artikel.getBildname())) {
//	            	System.out.println("Found!");
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
	    String fileName = artikel.getBildname();
	    int id = this.getResources().getIdentifier(fileName, "drawable", "com.example.test");
	    System.out.println("Found id: " + id + " for " + fileName);
	    image.setImageResource(id);
	    
	    // Wenn kein bild gefunden wurde, setzen das Platzhalterimage
	    if (id == 0) {
	    	image.setImageResource(R.drawable.keinbild);
	    }
	    
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				System.out.println("OK Klick");
			}
		});
		return builder.create();
	}
}
