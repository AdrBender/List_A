package com.adriano.lista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.View;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.adriano.lista.model.Item;
import com.adriano.lista.model.Lista;
import com.adriano.lista.database.DatabaseHelper;
import com.adriano.lista.adapter.ItemAdapter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.adriano.lista.database.DbController;

/**
 * @author AdrBender
 */
public class ListaItensActivity extends AppCompatActivity {
	private static final String TAG = "com.adriano.lista.ListaItensActivity";

	Lista l;
	Intent intent;
	String nomeLista, idLista;
	int quantidade;

	DbController dbc;
	ListView lvItens;
	ItemAdapter adapter;
	ArrayList<String> data;
	List<Item> listItems = new ArrayList<Item>();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);
		
		dbc = DbController.getDbInstance(this);
		lvItens = (ListView) findViewById(R.id.itens_list_view);

		intent = getIntent();
		if( intent == null ) {
			Toast.makeText(this, "Erro!", Toast.LENGTH_LONG).show(); 
		}else{
			idLista = intent.getStringExtra("id");
			nomeLista = intent.getStringExtra("nomeLista");
			getSupportActionBar().setTitle(nomeLista);
		}
		carregarListaItens();
		
		quantidade = dbc.dbInstance.getContador(idLista);
		if(quantidade > 0) {
			Toast.makeText(ListaItensActivity.this, "Total de itens: "+quantidade, Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(ListaItensActivity.this, "Sem itens", Toast.LENGTH_SHORT).show();
		}
		
		//AdView mAdView = (AdView)findViewById(R.id.ad_view);
		//AdRequest adRequest = new AdRequest.Builder().build();
		//mAdView.loadAd(adRequest);
			
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
	
	public void carregarListaItens() {
		listItems = dbc.getItems(idLista);
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
        carregarListaItens();
        super.onResume();
    }
	
	private void dialogValorTotal() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		final View v = inflater.inflate(R.layout.valor_total_dialog, null);
		dialogBuilder.setTitle("Digite o valor da compra");
		dialogBuilder.setView(v);
		
		final EditText edt_valor_total = v.findViewById(R.id.edt_total_valor);
		dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int p) {
					double txt_valor_total = 0.d;
					txt_valor_total = Double.parseDouble(edt_valor_total.getText().toString());
					
					boolean result =  dbc.addCompra(idLista, nomeLista, txt_valor_total, Calendar.getInstance().getTime(), quantidade, true);
					if(result == true) {
					 	Toast.makeText(ListaItensActivity.this,"Lista Finalizada!",Toast.LENGTH_LONG).show();
						dbc.deletarLista(idLista);
	                }else {
	                    Toast.makeText(ListaItensActivity.this,"Erro ao finalizar!",Toast.LENGTH_LONG).show(); 
					}
					finish();
				} 
			});
		dialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}   
			}); 
		dialogBuilder.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        	case R.id.menu_save:
				dialogValorTotal();
            	return true;
        	default:
            	return super.onOptionsItemSelected(item);
    	}
	}
}
