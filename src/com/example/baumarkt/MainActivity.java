package com.example.baumarkt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baumarkt.dialog.ArticelDetailDialog;
import com.example.baumarkt.dialog.ChooseArticleAction;
import com.example.baumarkt.model.Artikel;
import com.example.baumarkt.model.Hauptkategorie;
import com.example.baumarkt.model.Produktkategorie;
import com.example.baumarkt.model.Unterkategorie;
import com.example.test.R;

public class MainActivity extends FragmentActivity implements OnItemSelectedListener, ChooseArticleAction.ArticelActionChoiceresult {

	// Set all instance variables to private to avoid access from outer space
	 // Spinner element
	private Spinner spinnerHauptkategorie, spinnerUnterkategorie, spinnerProduktkategorie;
	private GridView resultGrid;
	private GridView headlineResultGrid;
	
	private List<Hauptkategorie> hauptkategorien;
	private List<Produktkategorie> produktkategorien;
	private List<Unterkategorie> unterkategorien;
	
	// String      onSelect;
	private TextView tv;
	private TableRow tableRow1;
	private int searchResultHauptkategorie;
	private int searchResultProduktkategorie;
	private int searchResultUnterkategorie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("Start Main Activity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		resultGrid = (GridView) findViewById(R.id.gridView1);
		headlineResultGrid = (GridView) findViewById(R.id.gridView2);
		
		Bundle input = getIntent().getExtras();
		if (input != null) {
			searchResultHauptkategorie = input.getInt(Search.KEY_HAUPTKATEGORIE);
			System.out.println("Found " + Search.KEY_HAUPTKATEGORIE + " with ID: " + searchResultHauptkategorie);
			searchResultProduktkategorie = input.getInt(Search.KEY_PRODUKTKATEORIE);
			System.out.println("Found " + Search.KEY_PRODUKTKATEORIE + " with ID: " + searchResultProduktkategorie);
			searchResultUnterkategorie = input.getInt(Search.KEY_UNTERKATEGORIE);
			System.out.println("Found " + Search.KEY_UNTERKATEGORIE + " with ID: " + searchResultUnterkategorie);
		}
		
		
		
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

	 	/* Drop Down GUI Elemente für die Auswahl der Kategorien*/
        spinnerHauptkategorie = (Spinner) findViewById(R.id.spinner1);
        spinnerUnterkategorie = (Spinner) findViewById(R.id.spinner2);
        spinnerProduktkategorie = (Spinner) findViewById(R.id.spinner3);
 
 
        // Loading spinner data from database
        loadSpinnerHauptkategorien(); 
        
//        tv= (TextView) findViewById(R.id.texts);
  
        
        //     tableRow1 = (TableRow) findViewById(R.id.tableRow1);
        
        
//        TableLayout tl = (TableLayout)findViewById(R.id.myLayout);
//        /* Create a new row to be added. */
//        TableRow tr = new TableRow(this);
//        tr.setLayoutParams(new LayoutParams(
//                       LayoutParams.FILL_PARENT,
//                       LayoutParams.WRAP_CONTENT));
//             /* Create a Button to be the row-content. */
//             Button b = new Button(this);
//             b.setText("Dynamic Button");
//             b.setLayoutParams(new LayoutParams(
//                       LayoutParams.FILL_PARENT,
//                       LayoutParams.WRAP_CONTENT));
//             /* Add Button to row. */
//             tr.addView(b);
//   /* Add row to TableLayout. */
//   tl.addView(tr,new TableLayout.LayoutParams(
//             LayoutParams.FILL_PARENT,
//             LayoutParams.WRAP_CONTENT));
	 	
       // Spinner click listener
  //      spinner.setOnItemSelectedListener((OnItemSelectedListener) this);
        
        
        /* Listener um auf einen Klick auf den DropDown zu reagieren*/
        spinnerHauptkategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//               String s = item.toString();
                Hauptkategorie hk = (Hauptkategorie) item;
                loadSpinnerUnterkategorien(hk);
//                showData(s);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        
        /* Listener um auf einen Klick auf den DropDown zu reagieren*/
        spinnerUnterkategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//               String s = item.toString();
                Unterkategorie uk = (Unterkategorie) item;
                loadSpinnerProduktkategorien(uk);
//                showData(s);
            }

			public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        
        /* Listener um auf einen Klick auf den DropDown zu reagieren*/
        spinnerProduktkategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//               String s = item.toString();
                Produktkategorie pk = (Produktkategorie) item;
                showData(pk);
//                showData(s);
            }

			public void onNothingSelected(AdapterView<?> parent) {
            }
        });
       
       
	}              
        
	

	

	  /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerHauptkategorien() {
        // database handler
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
 
        // Spinner Drop down elements
        hauptkategorien = new ArrayList<Hauptkategorie>(db.getAllHauptkategorien());
        

        // Creating adapter for spinner
        ArrayAdapter<Hauptkategorie> dataAdapter = new ArrayAdapter<Hauptkategorie>(this,
                android.R.layout.simple_spinner_item, hauptkategorien);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerHauptkategorie.setAdapter(dataAdapter);  
        
        // Wenn es eine Hauptkategorie gab, die Durch die Suche gefunden wurde, dann wird diese jetzt als vorgewähtl in der Liste gesetzt:
        setSpinnerHauptkategorie(searchResultHauptkategorie);
    }
    
    
    /**2 spinner2
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerUnterkategorien(Hauptkategorie hk) {
        // database handlerS
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        
        // Spinner Drop down elements
        System.out.println("Select Unterkategorien mit: " + hk.getId());
        unterkategorien = new ArrayList<Unterkategorie>(db.getUnterkategorien(hk));
 
        // Creating adapter for spinner
        ArrayAdapter<Unterkategorie> dataAdapter = new ArrayAdapter<Unterkategorie>(this,
                android.R.layout.simple_spinner_item, unterkategorien);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerUnterkategorie.setAdapter(dataAdapter);
        
        
        // Wenn die Maske aus der Suche heraus aufgerufen wurde, und eine Unterkategorie gesetzt wurde, muss diese nun als
        // selected Item markiert werden.
        setSpinnerUnterkategorie(searchResultUnterkategorie);
        setSpinnerHauptkategorie(searchResultHauptkategorie);
    }

    private void loadSpinnerProduktkategorien(Unterkategorie uk) {
    	// database handlerS
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        
        // Spinner Drop down elements
       
       
        System.out.println("Select Produktkategorie mit: " + uk.getId());
        produktkategorien = new ArrayList<Produktkategorie>(db.getProduktkategorie(uk));
 
        // Creating adapter for spinner
        ArrayAdapter<Produktkategorie> dataAdapter = new ArrayAdapter<Produktkategorie>(this,
                android.R.layout.simple_spinner_item, produktkategorien);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerProduktkategorie.setAdapter(dataAdapter);
        
        setSpinnerProduktkategorie(searchResultProduktkategorie);
        setSpinnerUnterkategorie(searchResultProduktkategorie);
        setSpinnerHauptkategorie(searchResultHauptkategorie);
		
	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
   
   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Zeigt die Artikeldaten in der Tabelle an. 
	 * @param pk
	 */
	public void showData(Produktkategorie pk){
		DataBaseHelper db = new DataBaseHelper(getApplicationContext()); 
	    db.openDataBase();
		Set<Artikel> artikel = new HashSet<Artikel>(db.getArtikel(pk));
		db.close();

		// Sets the result into the arra plus the headline ( +1). But for each Artikel we'll need 4 columns! So Length of result 4 times! (*4)
		String[] result = new String[(artikel.size()) * 4]; 
		String[] headlineResult = new String[4];
		StringBuilder sb = new StringBuilder();
		
		headlineResult[0] = "Bezeichnung";
		headlineResult[1] = "Peis";
		headlineResult[2] = "Standort";
		headlineResult[3] = "Bild";
		
		int i = 0;
		
		
		for (Artikel a : artikel) {
			sb.append(a.toString());
			sb.append("\n");
			
			result[i] = a.getBezeichnung();
			result[++i] = String.valueOf(a.getPreis() + " €");
			result[++i] = a.getStandort();
			result[++i] = a.getBildname();
			i++;
		}
		
		ArrayAdapter<String> resultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
		resultGrid.setAdapter(resultAdapter);
		
		resultGrid.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				System.out.println("Click on ResultGrid! Pos: " + pos + " id: " + id);
				DialogFragment dialog = new ChooseArticleAction();
				dialog.show(getSupportFragmentManager(), "ChooseArticleAction");
			}
		});
		
		ArrayAdapter<String> resultHeadlineAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, headlineResult);
		headlineResultGrid.setAdapter(resultHeadlineAdapter);
		
		headlineResultGrid.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				// do noting. Idea: add a sort
			}
		});
		
//		tv.setText(sb.toString());
		
		Toast.makeText(getApplicationContext(), "Klicken Sie für weitere Informatinen auf den Artikel", Toast.LENGTH_LONG).show();
	}
	
	private void setSpinnerUnterkategorie(int id) {
		Unterkategorie ukSearch = null;
		for (Unterkategorie uk : unterkategorien) {
        	if (uk.getId() == id) {
        		ukSearch = uk;
        		break;
        	}
        }
        
		if (ukSearch != null) {
	        int ukPos = ((ArrayAdapter<Unterkategorie>) spinnerUnterkategorie.getAdapter()).getPosition(ukSearch);
	        spinnerUnterkategorie.setSelection(ukPos);
	        System.out.println("Set Spinner Unterkategorie to position: "  + ukPos);
		}
	}
	
	private void setSpinnerHauptkategorie(int id) {
		Hauptkategorie hkSearch = null;
        for (Hauptkategorie suchergebnisHauptkategorie : hauptkategorien) {
        	if (suchergebnisHauptkategorie.getId() == id) {
        		hkSearch = suchergebnisHauptkategorie;
        	}
        }
        
        if (hkSearch != null) {
	        int hkPos = ((ArrayAdapter<Hauptkategorie>) spinnerHauptkategorie.getAdapter()).getPosition(hkSearch);
	        spinnerHauptkategorie.setSelection(hkPos);
	        System.out.println("Set Spinner Hauptkategorie to position: " + hkPos);
        }
	}
	
	private void setSpinnerProduktkategorie(int id) {
		Produktkategorie pkSearch = null;
		for (Produktkategorie pk : produktkategorien) {
			if (pk.getId() == id) {
				pkSearch = pk;
			}
		}
		
		if (pkSearch != null) {
			int pkPos = ((ArrayAdapter<Produktkategorie>) spinnerProduktkategorie.getAdapter()).getPosition(pkSearch);
	        spinnerProduktkategorie.setSelection(pkPos);
	        System.out.println("Set Spinner Produktkategorie to positioin: " + pkPos);
		}
	}
	
	@Override
	public void itemSelected(DialogFragment dialog){
		if (dialog instanceof ChooseArticleAction) {
			ChooseArticleAction action = (ChooseArticleAction) dialog;
			int item = action.getSelectedItem();
			System.out.println("Main Activity knows which Item was presse: " + item);
			
			//Auswahl Artikeldetail
			if (item == 0) {
				DialogFragment detailDialog = new ArticelDetailDialog();
				detailDialog.show(getSupportFragmentManager(), "ArticelDetailDialog");
			}
			
		}
	}
}
