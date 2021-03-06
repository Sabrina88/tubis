package com.example.baumarkt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.baumarkt.model.Artikel;
import com.example.baumarkt.model.Hauptkategorie;
import com.example.baumarkt.model.Produktkategorie;
import com.example.baumarkt.model.Unterkategorie;

/**
 * Klasse f�r den Zugriff auf die Android interne Datenbank.
 * 
 * @author Sabrina
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.example.test/databases/";

    // Database Version
    private static final int DATABASE_VERSION = 2;
    
    private static String DB_NAME = "baumarkt.db";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
    
    // Labels table name
    private static final String TABLE_PRODUKTKATEGORIEN = "produktkategorien";
    // Labels table name
    private static final String TABLE_ARTICLE = "artikel";
    private static final String TABLE_HAUPTKATEGORIEN = "hauptkategorien";
    private static final String TABLE_UNTERKATEGORIE = "unterkategorien";
    
    

    // Labels Table Columns names
//    private static final String KEY_ARTIKELBEZEICHNUNG = "artikelbezeichnung";
//    private static final String KEY_ARTIKELPREIS = "preis";
//    private static final String KEY_ARTIKELSTANDORT = "artikelstandort";
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    		System.out.println("DB already existing!");
    	}else{
    		System.out.println("DB not available");
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
        		this.close();
//    			copyDataBase();
        		fillDatabase();
 
    		} catch (IOException e) {
    			e.printStackTrace();
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
    
    /**
     * Verwendet das SQL File ebenen.sql um die Datenbank mit den Daten zu f�llen, sollte die DB nicht vorhanden
     * sein.
     * Alle enthaltenen SQL Statements werden ausgelesen und einzeln ausgef�hrt.
     * 
     * @throws IOException
     */
    private void fillDatabase() throws IOException {
    	System.out.println("fillDatabase()");
    	openDataBase(SQLiteDatabase.CREATE_IF_NECESSARY);
    	
    	System.out.println("Get SQL File...");
    	InputStream myInput = myContext.getAssets().open("ebenen.sql");
    	InputStreamReader isr = new InputStreamReader(myInput);
    	
//    	BufferedReader br = new BufferedReader(new FileReader("ebenen.sql"));
    	BufferedReader br = new BufferedReader(isr);
    	String line = "";
    	String sql = "";
    	while((line = br.readLine()) != null) {
    		if (line.startsWith("--")) {
    			continue;
    		}
    		
    		sql += " " + line.trim();
    	
    		if (line.endsWith(";")) {
    			System.out.println("============================ NEXT SQL STATEMENT FOUND =====================0");
    			System.out.println("[SQL] Execute DB insert SQL: " + sql);
    			myDataBase.execSQL(sql);
    			sql = "";
    			System.out.println("[SQL] SUCCESS!");
    		}
    	}
    	System.out.println("Fill Database end!");
    	myDataBase.close();
    	System.out.println("Database closed");
    }
 
    public void openDataBase() throws SQLException{
    	openDataBase(SQLiteDatabase.OPEN_READONLY);
    }
    
    public void openDataBase(int access) {
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, access);
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}


	
	/**
	 * Lie�t alle Hauptkategorien aus der internen Datenbank aus und gibt diese als Liste zur�ck. 
	 * @return
	 */
	public Collection<Hauptkategorie> getAllHauptkategorien() {
		
		Collection<Hauptkategorie> result = new HashSet<Hauptkategorie>();
		
      // Select All Query
      String selectQuery = "SELECT  * FROM " + TABLE_HAUPTKATEGORIEN;
      SQLiteDatabase db = this.getReadableDatabase();
      
      System.out.println("[SQL] Hauptkategorie SQL Query: " + selectQuery);
      Cursor cursor = db.rawQuery(selectQuery, null);

      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
          do {
              int id = cursor.getInt(0);
              String bezeichnung = cursor.getString(1);
              String standort = cursor.getString(2);
              
              result.add(new Hauptkategorie(id, bezeichnung, standort));
              
          } while (cursor.moveToNext());
      }

      // closing connection
      cursor.close();
      db.close();

      // returning lables
      return result;
	}
	
	/**
	 * Gibt alle Unterkategorien die zu einer bestimmten Hauptkategorie zur�ck. 
	 * Hauptkategorie und Unterkategorie sind �ber einen Foreign Key verbunden. Somit lassen sich
	 * Unterkategorien zu Hauptkategorien zuordnen.
	 * @param hk
	 * @return
	 */
	public Collection<Unterkategorie> getUnterkategorien(Hauptkategorie hk) {
		
		Collection<Unterkategorie> result = new HashSet<Unterkategorie>();
		
      // Select All Query
      String selectQuery = "SELECT  * FROM " + TABLE_UNTERKATEGORIE + " u WHERE u.fk_hauptkategorien = " + hk.getId();
      SQLiteDatabase db = this.getReadableDatabase();
      
      System.out.println("[SQL] Unterkategorien SQL Query: " + selectQuery);
      Cursor cursor = db.rawQuery(selectQuery, null);

      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
          do {
              int id = cursor.getInt(0);
              String bezeichnung = cursor.getString(1);
              String standort = cursor.getString(2);
              
              result.add(new Unterkategorie(id, bezeichnung, standort));
              
          } while (cursor.moveToNext());
      }

      // closing connection
      cursor.close();
      db.close();

      // returning lables
      return result;
	}

	
    public List<String> getProduktkategorien(String unterkategorie) {
    	return null;
    }
   
    
    /**
     * Gibt alle Artikel die zu einer Produktkategorie geh�ren zur�ck. 
     * Artikel und Produktkategorie sind �ber einen Fremdschl�ssel verbunden
     * @param pk
     * @return
     */
    public Collection<Artikel> getArtikel(Produktkategorie pk){
    	
    	Collection<Artikel> result = new ArrayList<Artikel>();
    	
	    String selectQuery = "SELECT  * FROM " + TABLE_ARTICLE + " WHERE  fk_produktkategorien = " + pk.getId();
	      
	    System.out.println("[SQL] Select alle artikel for Unterkategorie: " + pk.getId());
	    System.out.println("[SQL] Query: " + selectQuery);
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery,null);
	    
		if (cursor.moveToFirst()) {
	          do {
	              int id = cursor.getInt(0);
	              String standort = cursor.getString(1);
	              String bezeichnung = cursor.getString(2);
	              float preis = cursor.getFloat(3);
	              String bildname = cursor.getString(4);
	              
	              result.add(new Artikel(id, bezeichnung, standort, preis, bildname));
	              
	          } while (cursor.moveToNext());
	      }

		

		return result;
    }
    
    /**
     * Selektiert einen Artikel anhand der �bergebenen ID
     * 
     * @param id
     * @return Artikel
     */
    public Artikel getArtikelById(int id) {
    	Artikel result = null;
    	String sql = "SELECT * FROM " + TABLE_ARTICLE + " WHERE _id = " + id;
    	System.out.println("[SQL] select artikel for ID" + id);
    	System.out.println("[SQL] Query: " + sql);
    	
    	SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(sql,null);
	    
		if (cursor.moveToFirst()) {
			System.out.println("Artikel found...");
			int idArtikel = cursor.getInt(0);
            String standort = cursor.getString(1);
            String bezeichnung = cursor.getString(2);
            float preis = cursor.getFloat(3);
            String bildname = cursor.getString(4);
            String beschreibung = cursor.getString(6);
            
            result = new Artikel(idArtikel, bezeichnung, standort, preis, bildname, beschreibung);
		}
		else {
			System.out.println("Artikel not found!");
		}
    	
    	return result;
    }


    /**
     * Gibt die Produktkategorie zu einer Unterkategorie zur�ck.
     * @param uk
     * @return
     */
	public Collection<Produktkategorie> getProduktkategorie(Unterkategorie uk) {
		Collection<Produktkategorie> result = new HashSet<Produktkategorie>();
		String selectQuery = "SELECT  * FROM " + TABLE_PRODUKTKATEGORIEN + " u WHERE u.fk_unterkategorien = " + uk.getId();
		SQLiteDatabase db = this.getReadableDatabase();
		  
		  System.out.println("[SQL] Produktkategorie SQL Query: " + selectQuery);
		  Cursor cursor = db.rawQuery(selectQuery, null);
		
		  // looping through all rows and adding to list
		  if (cursor.moveToFirst()) {
		      do {
		          int id = cursor.getInt(0);
		          String bezeichnung = cursor.getString(1);
		          String standort = cursor.getString(2);
		          
		          result.add(new Produktkategorie(id, bezeichnung, standort));
		          
		      } while (cursor.moveToNext());
		  }
		
		  // closing connection
		  cursor.close();
		  db.close();
		
		  // returning lables
		  return result;
	}
	
	/**
	 * L�dt eine Hauptkategorie die zum �bergebenen Suchtext passt
	 * 
	 * @param searchString
	 * @return
	 */
	public Collection<Hauptkategorie> searchHauptkategorie(String searchString) {
		Collection<Hauptkategorie> result = new HashSet<Hauptkategorie>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		//Die Query sucht nach einer Hauptkategorie die den Suchstring enth�lt. Zuvor d�rfen auch anderen Zeichen stehen. Das wird �ber % im SQL Query realisiert
		String selectQuery =  "SELECT  * FROM " + TABLE_HAUPTKATEGORIEN + " WHERE hptkbezeichnung like '%"+ searchString +"%'" ;
		System.out.println("[SQL] search Query: " + selectQuery);
		Cursor cursor = db.rawQuery(selectQuery, null);
		
	      // looping through all rows and adding to list
	      if (cursor.moveToFirst()) {
	          do {
	              int id = cursor.getInt(0);
	              String bezeichnung = cursor.getString(1);
	              String standort = cursor.getString(2);
	              
	              Hauptkategorie h = new Hauptkategorie(id, bezeichnung, standort);
	              result.add(h);
	              System.out.println("Found in DB: " + h);
	              
	          } while (cursor.moveToNext());
	      }

	      // closing connection
	      cursor.close();
	      db.close();
		
		return result;
	}
	
	/**
	 * L�dt eine Produktkategorie die zum �bergebenen Suchtext passt
	 * 
	 * @param searchText
	 * @return
	 */
	public List<Produktkategorie> searchProduktkategorie(String searchText) {
		List<Produktkategorie> produktkategorien = new ArrayList<Produktkategorie>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		String sql = "SELECT * FROM produktkategorien WHERE prodkbezeichnung LIKE '%"+ searchText + "%'";
		System.out.println("[SQL] search produktkategorien: " + sql);
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
	              String bezeichnung = cursor.getString(1);
	              String standort = cursor.getString(2);
				  int idUnterkategorie = cursor.getInt(3);
				  
				  Produktkategorie p = new Produktkategorie(id, bezeichnung, standort);
				  Unterkategorie relatedUnterkategorie = getUnterkategorieById(idUnterkategorie);
				  p.setUnterkategorien(relatedUnterkategorie);
				  
				  produktkategorien.add(p);
				
			} while (cursor.moveToNext());
		}
		
		cursor.close();
	    db.close();
		return produktkategorien;
	}
	


	/**
	 * L�dt eine Unterkategorie die zum �bergebenen Suchtext passt
	 * 
	 * @param searchText
	 * @return
	 */
	public List<Unterkategorie> searchUnterkategorie(String searchText) {
		List<Unterkategorie> unterkategorien = new ArrayList<Unterkategorie>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		String sql = "SELECT * FROM unterkategorien WHERE untkbezeichnung LIKE '%"+ searchText + "%'";
		System.out.println("[SQL] search unterkategorien: " + sql);
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
	              String bezeichnung = cursor.getString(1);
	              String standort = cursor.getString(2);
				  int idHauptkategorie = cursor.getInt(3);
				  
				  Unterkategorie u = new Unterkategorie(id, bezeichnung, standort);
				  Hauptkategorie relatedHauptkategorie = getHauptkategorieById(idHauptkategorie);
				  u.setHauptkategorie(relatedHauptkategorie);
				  
				  unterkategorien.add(u);
				
			} while (cursor.moveToNext());
		}
		
		cursor.close();
	    db.close();
		return unterkategorien;
		
	}
	
	/**
	 * Sucht eine Artikel der zum �bergebenen Suchtext passt
	 * @param searchText
	 * @return
	 */
	public List<Artikel> searchArtikel(String searchText) {
		List<Artikel> artikel = new ArrayList<Artikel>();
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		String sql = "SELECT * FROM artikel WHERE artikelbezeichnung LIKE '%"+ searchText + "%'";
		System.out.println("[SQL] search artikel: " + sql);
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst()) {
			do {
				  int id = cursor.getInt(0);
	              String bezeichnung = cursor.getString(2);
	              String standort = cursor.getString(1);
	              float preis = cursor.getFloat(3);
	              String bildname = cursor.getString(4);
				  int idPrduktkategorie = cursor.getInt(5);
				  
				  Artikel a = new Artikel(id, bezeichnung, standort, preis, bildname);
				  Produktkategorie relatedProduktkategorie= getProduktkategorieById(idPrduktkategorie);
				  a.setProduktkategorie(relatedProduktkategorie);
				  
				  artikel.add(a);
				
			} while (cursor.moveToNext());
		}
		
		cursor.close();
	    db.close();
		
		return artikel;
	}
	
	/**
	 * L�dt eine Produktkategorie anhand der ID
	 * 
	 * @param idProduktkategorie
	 * @return
	 */
	public Produktkategorie getProduktkategorieById(int idProduktkategorie) {
		Produktkategorie result = null;
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM produktkategorien WHERE _id = " + idProduktkategorie;
		System.out.println("[SQL] select Produktkategorie by ID: " + sql);
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst()) {
			int id = cursor.getInt(0);
			String bezeichnung = cursor.getString(1);
			String standort = cursor.getString(2);
			int idUnterkategorie = cursor.getInt(3);
			
			Unterkategorie u = getUnterkategorieById(idUnterkategorie);
			
			result= new Produktkategorie(id, bezeichnung, standort);
			result.setUnterkategorien(u); 
			
			System.out.println("Found for ID: " + idProduktkategorie + " Produktkategorie " + result);
		}
		
		cursor.close();
	    db.close();
		return result;
	}
	
	/**
	 * L�dt eine Unerkategorie anhand der ID
	 * @param idUnterkategorie
	 * @return
	 */
	public Unterkategorie getUnterkategorieById(int idUnterkategorie) {
		Unterkategorie result = null;
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM unterkategorien WHERE _id = " + idUnterkategorie;
		System.out.println("[SQL] select Unterkategorien by ID: " + sql);
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst()) {
			int id = cursor.getInt(0);
			String bezeichnung = cursor.getString(1);
			String standort = cursor.getString(2);
			int idHauptkategorie = cursor.getInt(3);
			
			Hauptkategorie h  = getHauptkategorieById(idHauptkategorie);
			
			result= new Unterkategorie(id, bezeichnung, standort);
			result.setHauptkategorie(h); 
			
			System.out.println("Found for ID: " + idUnterkategorie + " Unerkategorie: " + result);
		}
		
		cursor.close();
	    db.close();
		return result;
	}

	/**
	 * Holt Hauptkategorien anhand der ID
	 * 
	 * @param idHauptkategorie
	 * @return
	 */
	public Hauptkategorie getHauptkategorieById(int idHauptkategorie) {
		Hauptkategorie result = null;
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM hauptkategorien WHERE _id=" + idHauptkategorie;
		System.out.println("[SQL] select Hauptkategorie by ID: " + sql);
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst()) {
			int id = cursor.getInt(0);
			String bezeichnung = cursor.getString(1);
			String standort = cursor.getString(2);
			
			
			result= new Hauptkategorie(id, bezeichnung, standort);
			System.out.println("Found for id: " + idHauptkategorie + " Hauptkategorie: " + result);
		}
		
		cursor.close();
	    db.close();
		return result;
	}
}