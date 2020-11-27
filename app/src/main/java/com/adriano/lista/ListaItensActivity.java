package com.adriano.lista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.util.Log;

import com.adriano.lista.model.Item;
import com.adriano.lista.model.Lista;
import com.adriano.lista.database.DatabaseHelper;
import com.adriano.lista.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaItensActivity extends AppCompatActivity {
	private static final String TAG = "com.adriano.lista.ListaItensActivity";

	Lista l;
	Intent intent;
	String nomeLista, idLista;

	DatabaseHelper mDatabase;
	ListView lvItens;
	ItemAdapter adapter;
	ArrayList<String> data;
	List<Item> listItems = new ArrayList<Item>();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);
		
		mDatabase = new DatabaseHelper(this);
		lvItens = (ListView) findViewById(R.id.itens_list_view);

		intent = getIntent();
		if( intent == null ) {
			Toast.makeText(this, "Erro!", Toast.LENGTH_LONG).show(); 
		}else{
			idLista = intent.getStringExtra("id");
			nomeLista = intent.getStringExtra("nomeLista");
			getSupportActionBar().setTitle(nomeLista);
		}
		carregarLista();
			
		FloatingActionButton itemfab = (FloatingActionButton)findViewById(R.id.item_fab);
        itemfab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//bundle = new Bundle();
                //bundle.putString("id", idLista);
				intent = new Intent(ListaItensActivity.this, ProdutosActivity.class);
                //intent.putExtras(bundle);
				intent.putExtra("id", idLista);
				//Toast.makeText(getApplicationContext(), "Id da Lista: "+idLista, Toast.LENGTH_SHORT).show();
                startActivity(intent);
			}
		});
	}
	
	public void carregarLista() {
		listItems = mDatabase.getItems(idLista);
		lvItens.setAdapter(new ItemAdapter(this, listItems));
        
		lvItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
				Log.i(TAG, "Item Click: " +position);
			}
		});
	}

	@Override
    protected void onResume() {
        carregarLista();
		
		// Exibe os nomes e id`s dos itens da lista
		for (Item i : listItems) {
			StringBuffer sb = new StringBuffer();
			sb.append("Id "+i.getIdItem()+"\n");
			sb.append("Item "+i.getItem());
			Toast.makeText(ListaItensActivity.this, sb.toString(), Toast.LENGTH_SHORT).show(); 
		}
        super.onResume();
    }
}
