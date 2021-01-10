package com.adriano.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.content.Intent;

import com.adriano.lista.adapter.HistoricoAdapter;
import com.adriano.lista.database.DatabaseHelper;
import com.adriano.lista.database.DbController;
import com.adriano.lista.model.Lista;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AdrBender
 */
public class HistoricoActivity extends AppCompatActivity {
	
	DatabaseHelper dbh;
	ListView lvHistorico;
	
	DbController dbc;
	
	HistoricoAdapter adapter;
	List<Lista> listLista;
	ArrayList<String> list = new ArrayList<>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
		
		lvHistorico = (ListView) findViewById(R.id.hist_list_view);
		
		dbc = DbController.getDbInstance(this);
		listLista = dbc.getCompraLists(true);
		
		adapter = new HistoricoAdapter(HistoricoActivity.this, listLista); 
		lvHistorico.setAdapter(adapter);
		/*
		for (Lista l : listLista) {
			StringBuffer sb = new StringBuffer();
			sb.append("Id "+l.getId()+"\n");
			sb.append("Lista: "+l.getLista()+"\n");
			sb.append("isSaved: "+l.getIsSaved()+"\n");
			sb.append("Valor: "+l.getValor());
			Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show(); 
		}*/
/*
        for(Lista l : listLista) {
            String r  = "Nome : "+l.getLista() 
						+ " - Data : " + l.getData() 
						+ " - Hora : " + l.getHora();
            list.add(r);
        }
		 */
		
		lvHistorico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override 
			public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
				//Log.i(TAG, "List Click: " +position);
				
				Lista li = (Lista)parent.getItemAtPosition(position);
				//String idLista = String.valueOf(li.getId());
				
				//Toast.makeText(getApplicationContext(), "Id da Lista: "+idLista, Toast.LENGTH_SHORT).show();
				
				//Bundle bundle = new Bundle();
				//bundle.putInt("id", idLista);
				//bundle.putString("nomeLista", nomeLista);
				//-Intent intent = new Intent(getApplicationContext(), ListaItensActivity.class);
                //intent.putExtras(bundle);
				//intent.putExtra("id",idLista);
				//intent.putExtra("nomeLista", nomeLista);
				Toast.makeText(HistoricoActivity.this, "Nome: "+li.getLista()+"\n" +"Id: "+li.getId(), Toast.LENGTH_SHORT).show();
                //startActivity(intent);
			}
		});
	}
	
	@Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	 	getMenuInflater().inflate(R.menu.menu_hist, menu);
	 	return super.onCreateOptionsMenu(menu);
	 }
	 
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.delete_hist:
                Toast.makeText(HistoricoActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;
            /*case R.id.action_settings:
                Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about_us:
                Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }
}
