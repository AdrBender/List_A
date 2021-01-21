package com.adriano.lista.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.adriano.lista.model.*;
import android.database.DatabaseUtils;

/**
 * Created by AdrBender 18/12/2020
 */
public class DbController {
	
	SQLiteDatabase db;
    DatabaseHelper dbh;
	
    public DbController(Context context){
        dbh = new DatabaseHelper(context);
    }
	
	public static DbController dbInstance;
	
	/*Padrao Singleton*/
	public static synchronized DbController getDbInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DbController(context.getApplicationContext());
        }
        return dbInstance;
    }
	
	@SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy / HH:mm:ss");
	
	/*Metodo que salva as compras*/
	public boolean addCompra(String id, String nomeLista, Double valor, Date data, int quantidade, Boolean b) {
        db = dbh.getWritableDatabase();
	    ContentValues cv = new ContentValues();
		db.beginTransaction();
		
		cv.put(dbh.COLUMN_LISTA_ID, id);
		cv.put(dbh.COLUMN_NOME_LISTA, nomeLista);
        cv.put(dbh.COLUMN_VALOR_TOTAL, valor);
		cv.put(dbh.COLUMN_DATA, dateFormat.format(data));
		cv.put(dbh.COLUMN_LISTAS_SALVAS, b ? 1 : 0);
		
		long result = db.insert(dbh.TABLE_HISTORICO, null, cv);
		
        db.setTransactionSuccessful();
        db.endTransaction();
		if (result == -1) 
			return false;
		else 
			return true;
	}
	
	/**
	* Pega todas as compras finalizadas
	* @param isSaved pega todas as listas com true.
	*/
	public List<Lista> getCompraLists(boolean isSaved) {
        ArrayList<Lista> compraLists = new ArrayList<>();
        db = dbh.getReadableDatabase();
        Cursor cursor = db.query(dbh.TABLE_HISTORICO, null,
								 dbh.COLUMN_LISTAS_SALVAS+" = "+(isSaved ? "1" : "0"), null, null, null,
								 "datetime ("+dbh.COLUMN_DATA+") DESC");
								 
        while (cursor.moveToNext()) {
            Lista lista = new Lista();
            lista.setId(cursor.getInt(cursor.getColumnIndex(dbh.COLUMN_LISTA_ID)));
			lista.setLista(cursor.getString(cursor.getColumnIndex(dbh.COLUMN_NOME_LISTA)));
			lista.setValor(Double.valueOf(cursor.getDouble(cursor.getColumnIndex(dbh.COLUMN_VALOR_TOTAL))));
            lista.setIsSaved(cursor.getInt(cursor.getColumnIndex(dbh.COLUMN_LISTAS_SALVAS)) == 1);
            try {
                lista.setData(dateFormat.parse(cursor.getString(cursor.getColumnIndex(dbh.COLUMN_DATA))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            compraLists.add(lista);
        }
        cursor.close();
        db.close();
        return compraLists;
    }
	
	/**
	* Metodo para adcionar itens
	* @param item pega o item a ser armazenado
	* @param idLista pega o id da lista na qual o item pertence
	*/
	public boolean insertItem(String item, String idLista) {
		db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
	 	contentValues.put(dbh.COLUMN_ITENS_LISTA_ID, idLista);
		contentValues.put(dbh.COLUMN_NOME_ITEM, item);
		contentValues.put(dbh.COLUMN_CHECKED_ITEM, 0);
		
	 	long result = db.insert(dbh.TABLE_ITENS , null, contentValues);
		db.close();
		if (result == -1) return false; 
	    else return true; 
	}
	
	/*Pega todos os itens da tabela*/
	public boolean insertLista(Lista lista) { 
		db = dbh.getWritableDatabase();
	    ContentValues cv = new ContentValues(); 
	    cv.put(dbh.COLUMN_NOME_LISTA, lista.getLista());
		cv.put(dbh.COLUMN_DATA, dateFormat.format(lista.getData()));
		cv.put(dbh.COLUMN_LISTAS_SALVAS, lista.isSaved() ? 1 : 0);
		
		long result = db.insert(dbh.TABLE_LISTAS, null, cv);
		db.close();
		
	    if (result == -1) 
			return false;
	    else 
			return true;
	}
	
	/**
	*Pega todos os itens da tabela
	* @param idLista pega todos os item pelo id da lista
	*/
	public ArrayList<Item> getItems(String idLista) {
		db = dbh.getReadableDatabase();
        ArrayList<Item> itemList = new ArrayList<>();
		String selectQuery = "SELECT * FROM " + dbh.TABLE_ITENS + " WHERE "+ dbh.COLUMN_ITENS_LISTA_ID +" ='"+idLista+"'";
		
        Cursor cursor = db.rawQuery(selectQuery, null);
		
        if (cursor.moveToFirst()) {
            do {
                Item i = new Item();
				i.setIdItem(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ITEM_ID)));
				i.setItem(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME_ITEM)));
				i.setStatus(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_CHECKED_ITEM)));
				//i.setIdItem(cursor.getInt(cursor.getColumnIndex("item_id")));
				//i.setItem(cursor.getString(cursor.getColumnIndex("item")));
				//i.setStatus(cursor.getInt(cursor.getColumnIndex("checked_itens")));
                itemList.add(i);
            } while (cursor.moveToNext());
        }
		db.close();
        return itemList;
    }
	
	/*Pega todas as listas criadas*/
    public List<Lista> getListas() {
		db = dbh.getReadableDatabase();
        ArrayList<Lista> listas = new ArrayList<Lista>();
        String query = "SELECT * FROM " + dbh.TABLE_LISTAS;
		
        Cursor cursor = db.rawQuery(query, null);
		//if (cursor != null && cursor.moveToFirst()) {
		while (cursor.moveToNext()) {
			//do {
				Lista lista = new Lista();
				lista.setId(cursor.getInt(cursor.getColumnIndex(dbh.COLUMN_LISTA_ID)));
				lista.setLista(cursor.getString(cursor.getColumnIndex(dbh.COLUMN_NOME_LISTA)));
				
			try {
				lista.setData(dateFormat.parse(cursor.getString(cursor.getColumnIndex(dbh.COLUMN_DATA))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			listas.add(lista);
		}
        cursor.close();
        db.close();
        return listas;
    }
	
	/*Faz o update dos checkboxes*/
	public void updateStatus(int idItem, int status) {
	 	db = dbh.getWritableDatabase();
	 	ContentValues values = new ContentValues();
		values.put(dbh.COLUMN_CHECKED_ITEM, status);
		
        db.update(dbh.TABLE_ITENS, values, "item_id=?",new String[]{String.valueOf(idItem)});
		db.close();
	}
	
	/*Deleta a lista da MainActivity*/
	public void deleteLista(Lista lista) {
		db = dbh.getReadableDatabase();
		db.execSQL("DELETE FROM " + dbh.TABLE_LISTAS + " WHERE " + dbh.COLUMN_LISTA_ID +
				   " LIKE " + lista.getId());	
		db.close();
	}
	
	/*Deleta a lista correspondente a sua finalizacao de compra*/
	public void deletarLista(String idLista) {
		db = dbh.getReadableDatabase();
		db.execSQL("DELETE FROM " + dbh.TABLE_LISTAS + " WHERE " + dbh.COLUMN_LISTA_ID +
				   " LIKE " + idLista);	
		db.close();
	}
	
	/*Deleta todo o historico de compras*/
	public void deletarHistorico() {
		db = dbh.getReadableDatabase();
		db.execSQL("DELETE FROM " + dbh.TABLE_HISTORICO + " WHERE " + dbh.COLUMN_COMPRAS_ID);
		db.close();
	}
	
	/*Metodo que retorna a quantidade de registros na coluna id_lista
	* da tabela itens pelo id da lista
	* @param idLista - id da lista usado para retornar os registros
	*/
	public int getContador(String idLista) {
		db = dbh.getReadableDatabase();
    	
		Cursor cursor = db.rawQuery("SELECT count(*) FROM "+dbh.TABLE_ITENS+" WHERE "+dbh.COLUMN_ITENS_LISTA_ID+" = "+idLista, null);
		cursor.moveToFirst();
        int n = cursor.getInt(0);
        cursor.close();
        return n;
    }
}
