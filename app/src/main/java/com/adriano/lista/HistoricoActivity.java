package com.adriano.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author AdrBender
 */
public class HistoricoActivity extends AppCompatActivity {
	
	DatabaseHelper dbh;
	DbController dbc;
	
	ListView lvHistorico;
	List<Lista> listLista;
	ArrayList<String> list = new ArrayList<>();
	
	Lista lista;
	HistoricoAdapter adapter;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
		
		lvHistorico = (ListView) findViewById(R.id.hist_list_view);
		
		dbc = DbController.getDbInstance(this);
		listLista = dbc.getCompraLists(true);
		
		adapter = new HistoricoAdapter(HistoricoActivity.this, listLista); 
		lvHistorico.setAdapter(adapter);
		
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
				//Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
                //intent.putExtras(bundle);
				//intent.putExtra("id",idLista);
				//intent.putExtra("nomeLista", nomeLista);
				Toast.makeText(HistoricoActivity.this, "Nome: "+li.getLista()+"\n" +"Id: "+li.getId(), Toast.LENGTH_SHORT).show();
                //startActivity(intent);
			}
		});
	}
	
	public void apagarHistorico() {
		dbc.deletarHistorico();
		new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
			.setTitleText("Histórico Apagado!")
			.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
				@Override
				public void onClick(SweetAlertDialog sDialog) {
					sDialog.setConfirmText("OK")
					.setConfirmClickListener(null)
					.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
					finish();
				}
			})
			.show();
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
                apagarHistorico();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
