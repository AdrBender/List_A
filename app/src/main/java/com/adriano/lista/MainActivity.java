package com.adriano.lista;

import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.AdapterView;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adriano.lista.database.DatabaseHelper;
import com.adriano.lista.adapter.ListaAdapter;
import com.adriano.lista.model.Lista;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
	private static final String TAG = "com.adriano.lista.ListaItensActivity";
	
	DatabaseHelper dh;
	ListView lvLista;
	ListaAdapter adapter;
	ArrayList<Lista> listLists;
	
	AlertDialog.Builder build;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		lvLista = (ListView) findViewById(R.id.list_view);
		dh = new DatabaseHelper(this);
		listLists = (ArrayList<Lista>) dh.getListas();

		adapter = new ListaAdapter(MainActivity.this, listLists); 
		lvLista.setAdapter(adapter);
		/*
		//Logica para exibir uma mensagem quando a lista estiver vazia
		if(listLists.size() > 0){
            lvLista.setVisibility(View.VISIBLE);
            lvLista.setAdapter(adapter);
			adapter.notifyDataSetChanged();
        }else {
            lvLista.setVisibility(View.GONE);
			TextView txtListaVazia = (TextView)findViewById(R.id.empty_list);
			txtListaVazia.setText(getResources().getString(R.string.empty_list));
        }*/
		
		lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
			@Override 
			public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
				Log.i(TAG, "List Click: " +position);
				
				final Lista li = listLists.get(position);
				String idLista = String.valueOf(li.getId());
				String nomeLista = li.getLista();
				
				//Bundle bundle = new Bundle();
					//bundle.putInt("id", idLista);
					//bundle.putString("nomeLista", nomeLista);
					Intent intent = new Intent(getApplicationContext(), ListaItensActivity.class);
                	//intent.putExtras(bundle);
					intent.putExtra("id",idLista);
					intent.putExtra("nomeLista", nomeLista);
					//Toast.makeText(MainActivity.this, "Id da Lista: "+idLista, Toast.LENGTH_SHORT).show();
                	startActivity(intent);
			}
		}); 
		lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
											   int position, long id) {
				Lista lista = (Lista)parent.getItemAtPosition(position); 	 
				dh.deleteLista(lista);

				listLists.remove(lista);
				adapter.notifyDataSetChanged();
				Toast.makeText(MainActivity.this, "Lista "+lista.getLista()+" removida.", Toast.LENGTH_LONG).show(); 
					
				return true;
			}
		});
		
		FloatingActionButton mainfab = (FloatingActionButton)findViewById(R.id.mainfab);
        mainfab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showDialog();
			}
		});
	}
		
	public void showDialog() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		final View v = inflater.inflate(R.layout.lista_dialog, null);
		dialogBuilder.setTitle("Crie uma nova lista");
		dialogBuilder.setView(v);

		final EditText edt_nome_lista = v.findViewById(R.id.edt_lista_nome);
		
		dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int p) {
					
					String txt_nome_lista = edt_nome_lista.getText().toString();
					
					if (TextUtils.isEmpty(txt_nome_lista)) {
						Toast.makeText(MainActivity.this, "Digite algo!", Toast.LENGTH_SHORT).show();
						return;
					} else {
						Lista lista = new Lista(txt_nome_lista);
						boolean result = dh.insertLista(lista);
						
						if(result == true) {
							listLists.add(lista);
							adapter.notifyDataSetChanged();
							Toast.makeText(MainActivity.this,"Lista "+lista.getLista()+" criada! :)",Toast.LENGTH_LONG).show();
							atualizarLista();
							/*
							//caso queira chamar a activity para inserir itens apos criar uma lista
							String id = lista.getId();
							Bundle bundle = new Bundle();
							bundle.putString("id", id);
							bundle.putString("nomeLista", txt_nome_lista);
							Intent i = new Intent(getApplicationContext(), ProdutosActivity.class);
							i.putExtras(bundle);
							startActivity(i);*/
	                	}else {
	                    	Toast.makeText(MainActivity.this,"Os dados nao puderam ser inseridos.",Toast.LENGTH_LONG).show(); 
						}
					}
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
	
	private void atualizarLista(){
        try{
            dh = new DatabaseHelper(this.getApplicationContext()); 
			listLists = (ArrayList<Lista>) dh.getListas();
            adapter = new ListaAdapter(getApplicationContext(), listLists);
            lvLista.setAdapter(adapter);
        }catch(Exception e){
			Toast.makeText(MainActivity.this,"Erro ao atualizar lista!",Toast.LENGTH_LONG).show(); 
        }
    }
}
