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
import android.widget.TextView;
import android.widget.EditText;
import android.widget.AdapterView;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adriano.lista.database.DatabaseHelper;
import com.adriano.lista.database.DbController;
import com.adriano.lista.adapter.ListaAdapter;
import com.adriano.lista.model.Lista;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import android.view.MenuItem;
import android.view.Menu;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.initialization.InitializationStatus;

/**
 * @author AdrBender
 */
public class MainActivity extends AppCompatActivity {
	private static final String TAG = "com.adriano.lista.ListaItensActivity";
	
	TextView txtListaVazia;
	
	Context context;
	DbController dbc;
	
	ListView lvLista;
	ListaAdapter adapter;
	ArrayList<Lista> listLists;
	
	AlertDialog.Builder build;
	//LoadToast lt;
	//AdView mAdView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		//lt = new LoadToast(this);
		txtListaVazia = (TextView)findViewById(R.id.empty_text);
/*
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
				Toast.makeText(MainActivity.this, "AdMob init.", Toast.LENGTH_LONG).show();
            }
        });*/

        AdView adView = (AdView)findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();   
        adView.loadAd(adRequest);
    
		lvLista = (ListView) findViewById(R.id.list_view);
		dbc = DbController.getDbInstance(this);
		
		listLists = (ArrayList<Lista>) dbc.getListas();
		carregarListas();
		
		lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() { 
			@Override 
			public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
				Log.i(TAG, "List Click: " +position);
				
				final Lista li = listLists.get(position);
				String idLista = String.valueOf(li.getId());
				String nomeLista = li.getLista();
				
				Toast.makeText(getApplicationContext(), "Lista: "+nomeLista+" / "+"Id: "+idLista, Toast.LENGTH_SHORT).show();
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
				dialogRemoverLista(lista);
				return true;
			}

			private void dialogRemoverLista(final Lista lista) {
				
				new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Deseja excluir esta lista?")
                        //.setContentText("Won't be able to recover this file!")
                        .setConfirmText("Sim")
						.setCancelText("Não")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
							//dbc.deleteLista(lista);
							dbc.getDbInstance(context).deleteLista(lista);
							listLists.remove(lista);
							adapter.notifyDataSetChanged();

							// reuse previous dialog instance
                            sDialog.setTitleText("Deletada!")
                                    //.setContentText("Esta lista foi deletada!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(null)
									//.dismissWithAnimation();
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
									
							if(listLists.size() == 0){
								lvLista.setVisibility(View.INVISIBLE);
								txtListaVazia.setVisibility(View.VISIBLE);
							}
                        }
				})
				.show();

//-------------------------------------------------------------------------------------------------------------
				/*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				alertDialogBuilder.setMessage("Deseja excluir esta lista?");
				alertDialogBuilder.setPositiveButton("Sim", 
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							dbc.deleteLista(lista);
							listLists.remove(lista);
							adapter.notifyDataSetChanged();
							
							Toast.makeText(MainActivity.this, "Lista "+lista.getLista()+" removida.", Toast.LENGTH_LONG).show(); 

							if(listLists.size() == 0){
								lvLista.setVisibility(View.INVISIBLE);
								txtListaVazia.setVisibility(View.VISIBLE);
							}
						}
					});
				alertDialogBuilder.setNegativeButton("Não",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						}
					});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();*/
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
	
	public void carregarListas() {
		listLists = (ArrayList<Lista>) dbc.getListas();
		
		if(listLists.size() > 0){
			lvLista.setVisibility(View.VISIBLE);
		 	txtListaVazia.setVisibility(View.INVISIBLE);

		 	adapter = new ListaAdapter(MainActivity.this, listLists); 
            lvLista.setAdapter(adapter);
			adapter.notifyDataSetChanged();

        }else {
            lvLista.setVisibility(View.INVISIBLE);
			txtListaVazia.setVisibility(View.VISIBLE);
        }
	}

	@Override
    protected void onResume() {
        carregarListas();
		/*for (Lista l : listLists) {
			StringBuffer sb = new StringBuffer();
			sb.append("Id "+l.getId()+"\n");
			sb.append("Lista: "+l.getLista()+"\n");
			sb.append("isSaved: "+l.getIsSaved()+"\n");
			sb.append("Valor: "+l.getValor());
			Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show(); 
		}*/
        super.onResume();
    }

	public void showDialog() {
		//final LoadToast lt = new LoadToast(this);
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
					
					if (txt_nome_lista.isEmpty()) {
						//Toast.makeText(MainActivity.this, "Digite algo!", Toast.LENGTH_SHORT).show();
						edt_nome_lista.setError("Opss, Digite algo.");
						return;
					//if (edt_nome_lista.getText().toString().isEmpty()) {
						//edt_nome_lista.setError("Opss, Digite algo.");
					} else {
						Lista lista = new Lista(txt_nome_lista, 0.00, Calendar.getInstance().getTime(), false);
						boolean result = dbc.insertLista(lista);
						
						if(lvLista.getVisibility() == View.INVISIBLE) {
							txtListaVazia.setVisibility(View.INVISIBLE);
							lvLista.setVisibility(View.VISIBLE);
							
							if(result == true) {
								//lt.success();
								adicionarLista(lista);
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
	                    		Toast.makeText(MainActivity.this,"Os dados não puderam ser inseridos!",Toast.LENGTH_LONG).show(); 
							}
						}else if(lvLista.getVisibility() == View.VISIBLE) {
							adicionarLista(lista);
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
	
	private void adicionarLista(Lista lista) {
		listLists.add(lista);
		//adapter.notifyDataSetChanged();
		//lt.success();
		Toast.makeText(MainActivity.this,"Lista "+lista.getLista()+" criada! :)",Toast.LENGTH_SHORT).show();
		atualizarLista();
	}
	private void atualizarLista() {
        try{
			listLists = (ArrayList<Lista>) dbc.getListas();
            adapter = new ListaAdapter(this, listLists);
            lvLista.setAdapter(adapter);
        }catch(Exception e){
			Toast.makeText(MainActivity.this,"Erro ao atualizar lista!",Toast.LENGTH_LONG).show(); 
        }
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	 	getMenuInflater().inflate(R.menu.menu_main, menu);
	 	return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        	case R.id.menu_historico:
				Intent intent = new Intent(this, HistoricoActivity.class);
				startActivity(intent);
            	Toast.makeText(this, "Historico", Toast.LENGTH_SHORT).show();
            	return true;
        	default:
            	return super.onOptionsItemSelected(item);
    	}
	}
}
