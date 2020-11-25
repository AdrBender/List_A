package com.adriano.lista.database ;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.adriano.lista.model.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "listas_db";
	
	private static final String TABLE_LISTAS = "listas";
	private static final String TABLE_ITENS = "itens";
   
	private static final String COLUMN_LISTA_ID = "lista_id";
    private static final String COLUMN_NOME_LISTA = "lista";
	
    private static final String COLUMN_ITEM_ID = "item_id";
	private static final String COLUMN_NOME_ITEM = "item";
	private static final String COLUMN_ITENS_LISTA_ID = "id_lista";
	private static final String COLUMN_CHECKED_ITEM = "checked_itens";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
    @Override
    public void onCreate(SQLiteDatabase db){
		String lista_de_compras = 
			"CREATE TABLE IF NOT EXISTS " + TABLE_LISTAS + "("
			+ COLUMN_LISTA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_NOME_LISTA + " TEXT NOT NULL"
			+")";
			
		String lista_de_produtos = 
			"CREATE TABLE IF NOT EXISTS " + TABLE_ITENS + "("
			+ COLUMN_ITEM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_NOME_ITEM + " TEXT NOT NULL,"
			+ COLUMN_ITENS_LISTA_ID + " INTEGER NOT NULL,"
			+ COLUMN_CHECKED_ITEM + " INTEGER NOT NULL,"
			+"FOREIGN KEY ("+COLUMN_ITENS_LISTA_ID +") REFERENCES "+TABLE_LISTAS+"("+COLUMN_LISTA_ID+")"
			+")";
			
        db.execSQL(lista_de_compras);
		db.execSQL(lista_de_produtos);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTAS);
        onCreate(db);
    }
	
	public boolean insertItem(String item, String idLista) {
		SQLiteDatabase db = this.getWritableDatabase();
		
        ContentValues contentValues = new ContentValues();
	 	contentValues.put(COLUMN_ITENS_LISTA_ID, idLista);
		contentValues.put(COLUMN_NOME_ITEM, item);
		contentValues.put(COLUMN_CHECKED_ITEM, 0);
		
	 	long result = db.insert(TABLE_ITENS , null, contentValues);
		db.close();
		if (result == -1) return false; 
	    else return true; 
	}
	
	public boolean insertLista(Lista lista) { 
		SQLiteDatabase db = this.getWritableDatabase();
		
	    ContentValues cv = new ContentValues(); 
	    cv.put(COLUMN_NOME_LISTA, lista.getLista());
		
		long result = db.insert(TABLE_LISTAS, null, cv);
		db.close();
		
	    if (result == -1) return false; 
	    else return true;
   }
   
	public ArrayList<Item> getItems(String idLista) {
        ArrayList<Item> itemList = new ArrayList<>();
		String selectQuery = "SELECT * FROM " + TABLE_ITENS + " WHERE "+ COLUMN_ITENS_LISTA_ID +" ='"+idLista+"'";
		SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item i = new Item();
				i.setIdItem(cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_ID)));
				i.setItem(cursor.getString(cursor.getColumnIndex(COLUMN_NOME_ITEM)));
				i.setStatus(cursor.getInt(cursor.getColumnIndex(COLUMN_CHECKED_ITEM)));
				//i.setIdItem(cursor.getInt(cursor.getColumnIndex("item_id")));
				//i.setItem(cursor.getString(cursor.getColumnIndex("item")));
				//i.setStatus(cursor.getInt(cursor.getColumnIndex("checked_itens")));
                itemList.add(i);
            } while (cursor.moveToNext());
        }
        return itemList;
    }
	
    public List<Lista> getListas() {
		SQLiteDatabase db = getWritableDatabase();
        ArrayList<Lista> listas = new ArrayList<Lista>();
        String query = "SELECT * FROM " + TABLE_LISTAS;

        Cursor cursor = db.rawQuery(query, null);
        	if (cursor != null && cursor.moveToFirst()) {
            	do {
                	Lista lista = new Lista();
					lista.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_LISTA_ID)));
					//lista.setId(cursor.getInt((0)));
					lista.setLista(cursor.getString(cursor.getColumnIndex(COLUMN_NOME_LISTA)));
                	//lista.setLista(cursor.getString(1));
                	listas.add(lista);
            	} while (cursor.moveToNext());
        	}
		db.close();
        return listas;
    }
	
	public void updateStatus(int idItem, int status) {
	 	SQLiteDatabase db = getWritableDatabase();
	 	ContentValues values = new ContentValues();
		
		values.put(COLUMN_CHECKED_ITEM, status);
		
        db.update(TABLE_ITENS, values, "item_id=?",new String[]{String.valueOf(idItem)});
		db.close();
	 }
	
	public void deleteLista(Lista lista) {
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("DELETE FROM " + TABLE_LISTAS + " WHERE " + COLUMN_LISTA_ID +
				   " LIKE " + lista.getId());	
		db.close();
	}
}
