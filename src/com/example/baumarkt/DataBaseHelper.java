package com.example.baumarkt;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
    private static final String KEY_ARTIKELBEZEICHNUNG = "artikelbezeichnung";
    private static final String KEY_ARTIKELPREIS = "preis";
    private static final String KEY_ARTIKELSTANDORT = "artikelstandort";
 
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
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
    
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
 
        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
 
	
	 /**
     * Getting all labels
     * returns list of labels
     * */
//    public List<String> getAllHauptkategorien(){
//    	
//    	
//        List<String> labels = new ArrayList<String>();
// 
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_PRODUKTKATEGORIEN;
// 
//        SQLiteDatabase db = this.getReadableDatabase();
//        System.out.println("Hauptkategorie SQL Query: " + selectQuery);
//        Cursor cursor = db.rawQuery(selectQuery, null);
// 
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                labels.add(cursor.getString(1));
//            } while (cursor.moveToNext());
//        }
// 
//        // closing connection
//        cursor.close();
//        db.close();
// 
//        // returning lables
//        return labels;
//    }
	
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
    
    /**
     * Getting all labels
     * returns list of labels
     * */
//    public List<String> getUnterkategorie(Hauptkategorie hauptkategorie){
//        List<String> labels = new ArrayList<String>();
// 
//        String selectQuery;
//        if(hauptkategorie!=null){
//        // Select All Query
//    //    String selectQuery = "SELECT  * FROM " + TABLE_LABELS;
// 
//        // Select All Query
//        selectQuery = "SELECT  * FROM " + TABLE_ARTICLE + " WHERE bildname ='"+hauptkategorie.toLowerCase()+"'" ;
//   	// selectQuery = "SELECT * FROM " + TABLE_LABELS2;
//     
//        }
//        else{
//        	System.out.println("eslse");
//        	 selectQuery = "SELECT * FROM " + TABLE_ARTICLE;
//        }
//        SQLiteDatabase db = this.getReadableDatabase();
//        System.out.println("Unterkategorie SQL Query: " + selectQuery);
//        Cursor cursor = db.rawQuery(selectQuery, null);
// 
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                labels.add(cursor.getString(2));
//            } while (cursor.moveToNext());
//        }
// 
//        // closing connection
//        cursor.close();
//        db.close();
// 
//        // returning lables
//        return labels;
//    }
	
    public List<String> getProduktkategorien(String unterkategorie) {
    	return null;
    }
    
//    public String getArtikel(String spin1){
//    	String[] columns = new String[]{	KEY_ARTIKELBEZEICHNUNG, KEY_ARTIKELPREIS,KEY_ARTIKELSTANDORT };
//    	  String selectQuery;
//             selectQuery = "SELECT  * FROM " + TABLE_ARTICLE + " WHERE bildname ='"+spin1.toLowerCase()+"'" ;
//     	// selectQuery = "SELECT * FROM " + TABLE_LABELS2;
//       
//          
//         SQLiteDatabase db = this.getReadableDatabase();
//          Cursor cursor = db.rawQuery(selectQuery,null);
//   
//    	String result="";
//    	
//    	int bez = cursor.getColumnIndex(KEY_ARTIKELBEZEICHNUNG);
//    	int preis = cursor.getColumnIndex(KEY_ARTIKELPREIS);
//    	int ort= cursor.getColumnIndex(KEY_ARTIKELSTANDORT);
//    	
//    	for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
//    		result = result + cursor.getString(bez)+ " "+ cursor.getString(preis)+" "+cursor.getString(ort)+"\n";
//    		
//    	}
//    	return result;
//    }
    
    public Collection<Artikel> getArtikel(Produktkategorie pk){
    	
    	Collection<Artikel> result = new ArrayList<Artikel>();
    	
	    String selectQuery = "SELECT  * FROM " + TABLE_ARTICLE + " WHERE  fk_produktkategorien = " + pk.getId();
	      
	    System.out.println("[SQL] Select alle artikles for Unterkategorie: " + pk.getId());
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
}