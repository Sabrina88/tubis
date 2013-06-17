package com.example.baumarkt.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.baumarkt.DataBaseHelper;
import com.example.baumarkt.model.Artikel;
import com.example.test.R;

public class ArticelDetailDialog extends DialogFragment {
	
	private TextView textArtikelname;
	private TextView textPreis;
	private TextView textStandort;
	private TextView textBeschreibung;
	
	@Override
	public Dialog onCreateDialog(Bundle savedState) {
		Bundle args = getArguments();
		int artikelid = args.getInt("ARTICLE_ID");
		
		System.out.println("Open Dialog for Artickel with ID: " + artikelid);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.articel_detail_view, null);
		builder.setView(v);
		
		textArtikelname = (TextView) v.findViewById(R.id.textViewArtikelname);
		textPreis = (TextView) v.findViewById(R.id.textViewPreis);
		textStandort = (TextView) v.findViewById(R.id.textViewStandort);
		textBeschreibung = (TextView) v.findViewById(R.id.textViewBeschreibung);
		
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
		
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				System.out.println("OK Klick");
			}
		});
		return builder.create();
	}
}
