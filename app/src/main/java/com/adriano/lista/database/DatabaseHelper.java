package com.adriano.lista.database ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by AdrBender
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "listas_db";
	
	protected static final String TABLE_LISTAS = "listas";
	protected static final String TABLE_ITENS = "itens";
	protected static final String TABLE_HISTORICO = "compras";
   
	protected static final String COLUMN_LISTA_ID = "lista_id";
    public static final String COLUMN_NOME_LISTA = "lista";
	public static final String COLUMN_DATA = "data_hora";
	public static final String COLUMN_LISTAS_SALVAS = "listas_salvas";
	//public static final String COLUMN_TOTAL_ITENS = "total_itens";
	public static final String COLUMN_VALOR_TOTAL = "valor_total";
	
    protected static final String COLUMN_ITEM_ID = "item_id";
	protected static final String COLUMN_NOME_ITEM = "item";
	protected static final String COLUMN_ITENS_LISTA_ID = "id_lista";
	protected static final String COLUMN_CHECKED_ITEM = "checked_itens";
	
	public static final String COLUMN_COMPRAS_ID = "compras_id";
	
	//@SuppressLint("SimpleDateFormat")
    //protected static SimpleDateFormat dateFormat;
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
    @Override
    public void onCreate(SQLiteDatabase db){
		String tabela_de_listas = 
			"CREATE TABLE IF NOT EXISTS " + TABLE_LISTAS + "("
			+ COLUMN_LISTA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_DATA + " DATE NOT NULL, "
			+ COLUMN_LISTAS_SALVAS + " , "
			+ COLUMN_NOME_LISTA + " TEXT NOT NULL, "
			+ COLUMN_VALOR_TOTAL + " DECIMAL NOT NULL"
			+")";
			
		String tabela_de_produtos = 
			"CREATE TABLE IF NOT EXISTS " + TABLE_ITENS + "("
			+ COLUMN_ITEM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_NOME_ITEM + " TEXT, "
			+ COLUMN_ITENS_LISTA_ID + " INTEGER, "
			+ COLUMN_CHECKED_ITEM + " INTEGER, "
			//+ COLUMN_TOTAL_ITENS + " INTEGER, "
			+"FOREIGN KEY ("+COLUMN_ITENS_LISTA_ID+") REFERENCES "+TABLE_LISTAS+"("+COLUMN_LISTA_ID+")"
			+")";
			
		String tabela_de_compras = 
			"CREATE TABLE IF NOT EXISTS " + TABLE_HISTORICO + "("
			+ COLUMN_COMPRAS_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_LISTA_ID + " INTEGER NOT NULL, "
			+ COLUMN_NOME_LISTA + " TEXT NOT NULL, "
			+ COLUMN_DATA + " DATE NOT NULL, "
			+ COLUMN_LISTAS_SALVAS + " , "
			+ COLUMN_VALOR_TOTAL + " DECIMAL NOT NULL"
			+")";
		
        db.execSQL(tabela_de_listas);
		db.execSQL(tabela_de_produtos);
		db.execSQL(tabela_de_compras);
    }
	
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTAS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITENS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORICO);
        onCreate(db);
    }
}
