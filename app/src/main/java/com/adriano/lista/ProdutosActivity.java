package com.adriano.lista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import android.view.View;
import android.widget.Toast;
import android.widget.ExpandableListView;

import com.adriano.lista.adapter.ExpandableListViewAdapter;
import com.adriano.lista.model.Lista;
import com.adriano.lista.database.DatabaseHelper;

public class ProdutosActivity extends AppCompatActivity {
	
	ExpandableListView expandableListView;
    ExpandableListViewAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableList;
	
	Lista l;
	String idLista;
	Bundle b;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
		
		getSupportActionBar().setTitle("Listas de Produtos");
		
		b = getIntent().getExtras();
		if( b != null ) {
			idLista = b.getString("id");
			Toast.makeText(this, "Lista id: "+idLista, Toast.LENGTH_LONG).show(); 
		}else{
			Toast.makeText(this, "id da lista está null", Toast.LENGTH_LONG).show(); 
		}
		
		expandableListView = (ExpandableListView) findViewById(R.id.exp_list_view);
		listData();
		
		expandableListAdapter = new ExpandableListViewAdapter(this, expandableListTitle, expandableList);
		expandableListView.setAdapter(expandableListAdapter);
	/*
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
				@Override
				public void onGroupExpand(int groupPosition) {
					Toast.makeText(getApplicationContext(),
								   expandableListTitle.get(groupPosition) + " List Expanded.",
								   Toast.LENGTH_SHORT).show();
				}
			});

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
				@Override
				public void onGroupCollapse(int groupPosition) {
					Toast.makeText(getApplicationContext(),
								   expandableListTitle.get(groupPosition) + " List Collapsed.",
								   Toast.LENGTH_SHORT).show();

				}
			});*/

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
											int groupPosition, int childPosition, long id) {
					Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
						+ ": "
						+ expandableList.get(
							expandableListTitle.get(groupPosition)).get(
							childPosition), Toast.LENGTH_SHORT
					).show();
					
					String p = expandableList.get(
						expandableListTitle.get(groupPosition)).get(
						childPosition);
					addItemLista(p, idLista);
					
					return false;
				}
			});
		}
		
	private void listData() {
        expandableListTitle = new ArrayList<String>();
        expandableList = new HashMap<String, List<String>>();

		expandableListTitle.add("Bebidas");
		expandableListTitle.add("Caldos, Molhos e Condimentos");
		expandableListTitle.add("Carnes e Frios");
		expandableListTitle.add("Enlatados e Conservas");
        expandableListTitle.add("Frutas");
		expandableListTitle.add("Grãos e Cereais");
        expandableListTitle.add("Higiene Pessoal");
		expandableListTitle.add("Limpeza");
		expandableListTitle.add("Sucos");
		expandableListTitle.add("Verduras e Legumes");
		
		
//Lista de Bebidas
		List<String> bebidas = new ArrayList<String>();
		bebidas.add("Achocolatado");
		bebidas.add("Água de Côco");
		bebidas.add("Água Mineral");
		bebidas.add("Cachaça");
		bebidas.add("Café");
		bebidas.add("Cappuccino");
		bebidas.add("Cerveja");
		bebidas.add("Chá");
		bebidas.add("Energético");
		bebidas.add("Iogurte");
		bebidas.add("Leite");
		bebidas.add("Pó para Refresco");
		bebidas.add("Polpa");
		bebidas.add("Suco Concentrado");
		bebidas.add("Vinhos");
		bebidas.add("Vodca");
		
		
//Lista de Caldos, Molhos e Condimentos
		List<String> cald_molh_cond = new ArrayList<String>();
		cald_molh_cond.add("Caldo para Arroz Branco");
		cald_molh_cond.add("Caldo de Carne");
        cald_molh_cond.add("Caldo de Bacon");
        cald_molh_cond.add("Caldo de Cebola e Alho");
        cald_molh_cond.add("Caldo de Costela");
		cald_molh_cond.add("Caldo de Churrasco");
        cald_molh_cond.add("Caldo de Galinha");
		cald_molh_cond.add("Caldo de Legumes e Verduras");
		cald_molh_cond.add("Caldo de Picanha");
        cald_molh_cond.add("Molho de Atum");
		cald_molh_cond.add("Molho Barbecue");
		cald_molh_cond.add("Molho Bolonhesa");
        cald_molh_cond.add("Molho Bordelaise");
		cald_molh_cond.add("Molho Inglês");
		cald_molh_cond.add("Molho de Atum");
		cald_molh_cond.add("Molho de Pimenta");
		cald_molh_cond.add("Molho Shoyo");
		cald_molh_cond.add("Molho de Soja");
		cald_molh_cond.add("Molho Teriyaki");
		cald_molh_cond.add("Molho de Tomate");
		cald_molh_cond.add("Molho de Tucupi");
		cald_molh_cond.add("Alho");
		cald_molh_cond.add("Alho em pó");
		cald_molh_cond.add("Alho Porró");
		cald_molh_cond.add("Almeirão");
		cald_molh_cond.add("Erva Doce");
        cald_molh_cond.add("Gengibre");
        cald_molh_cond.add("Hortelã");
		cald_molh_cond.add("Óleo de Dendê");
		cald_molh_cond.add("Óleo de Amêndoa");
        cald_molh_cond.add("Orégano");
		cald_molh_cond.add("Salsa");
		cald_molh_cond.add("Salsa");
        cald_molh_cond.add("Pimenta");
		cald_molh_cond.add("Sal");
		cald_molh_cond.add("Salsa");
		cald_molh_cond.add("Vinagre");
		cald_molh_cond.add("Vinagreira ou Caruru Azedo");
		
		
//Lista de Carnes e Frios
		List<String> carnes_frios = new ArrayList<String>();
		carnes_frios.add("Apresuntado");
        carnes_frios.add("Blanquet de Peru");
        carnes_frios.add("Chester");
		carnes_frios.add("Copa");
        carnes_frios.add("Mortadela");
		carnes_frios.add("Pasta de Enchova");
		carnes_frios.add("Peito de Frango");
		carnes_frios.add("Peito de Peru");
		carnes_frios.add("Presunto");
		carnes_frios.add("Ricota");
		carnes_frios.add("Rosbife");
		carnes_frios.add("Salame");
		carnes_frios.add("Salaminho");
		carnes_frios.add("Salsicha");
		carnes_frios.add("Salsichão");
		carnes_frios.add("Bacon");
		carnes_frios.add("Carne de Hambúrguer");
		carnes_frios.add("Empanados de Frango");
		carnes_frios.add("Tender");

		 
//Lista de Enlatados e Conservas
		List<String> enla_conse = new ArrayList<String>();
		enla_conse.add("Alcaparras");
		enla_conse.add("Aspargos Brancos");
        enla_conse.add("Atum");
		enla_conse.add("Azeitona Verde/Preta Fatiada");
		enla_conse.add("Azeitona Verde/Preta Recheada");
		enla_conse.add("Azeitona Verde/Preta sem Caroco");
		enla_conse.add("Bacalhau");
		enla_conse.add("Carne em Lata");
		enla_conse.add("Ervilha");
		enla_conse.add("Filé de Anchova em Óleo Comestível");
		enla_conse.add("Grão de Bico em conserva");
		enla_conse.add("Milho Verde");
		enla_conse.add("Ovos de Codorna em Conserva");
		enla_conse.add("Pepino Cornichon");
		enla_conse.add("Palmito Inteiro de Açaí");
		enla_conse.add("Pepino em Condimento");
		enla_conse.add("Picles");
		enla_conse.add("Salmão");
		enla_conse.add("Seleta de Legumes");
		enla_conse.add("Tomate Seco");


//Lista de Frutas
        List<String> frutas = new ArrayList<String>();
        frutas.add("Abacaxi");
		frutas.add("Abacate");
        frutas.add("Açaí");
        frutas.add("Acerola");
        frutas.add("Ameixa");
		frutas.add("Amêndoa");
		frutas.add("Amora");
		frutas.add("Avelã");
		frutas.add("Banana");
		frutas.add("Buriti");
		frutas.add("Butiá");
		frutas.add("Cereja");
		frutas.add("Cajá");
		frutas.add("Cajú");
		frutas.add("Camboatá");
		frutas.add("Canopi");
		frutas.add("Caqui");
		frutas.add("Carambola");
		frutas.add("Carurú");
		frutas.add("Côco Babaçu");
		frutas.add("Cuieira");
		frutas.add("Cumari");
		frutas.add("Cupuaçu");
		frutas.add("Damasco");
		frutas.add("Figo");
		frutas.add("Framboesa");
		frutas.add("Fruta de Conde");
		frutas.add("Goiaba");
		frutas.add("Gravatá");
		frutas.add("Graviola");
		frutas.add("Groselha");
		frutas.add("Guabiroba");
		frutas.add("Jabuticaba");
		frutas.add("Jaca");
		frutas.add("Jamelão");
		frutas.add("Jatobá");
		frutas.add("Jenipapo");
		frutas.add("Kiwi");
		frutas.add("Laranja");
		frutas.add("Lima");
		frutas.add("Limão");
		frutas.add("Maçã");
		frutas.add("Mamão");
		frutas.add("Manga");
		frutas.add("Maracujá");
		frutas.add("Melancia");
		frutas.add("Melão");
		frutas.add("Morango");
		frutas.add("Nozes de Macadâmia");
		frutas.add("Passas");
		frutas.add("Pêra");
		frutas.add("Pêssego");
		frutas.add("Pinhão");
		frutas.add("Pistache");
		frutas.add("Pitanga");
		frutas.add("Sapota");
		frutas.add("Sapoti");
		frutas.add("Sapucaia");
		frutas.add("Siriguela");
		frutas.add("Tâmara");
		frutas.add("Tangerina");
		frutas.add("Toranja");
		frutas.add("Uva");


//Lista de Graos
        List<String> graos_cereais = new ArrayList<String>();
        graos_cereais.add("Alpiste");
		graos_cereais.add("Amaranto");
        graos_cereais.add("Amendoim");
        graos_cereais.add("Arroz");
        graos_cereais.add("Aspargo");
        graos_cereais.add("Aveia");
		graos_cereais.add("Centeio");
		graos_cereais.add("Cevada");
		graos_cereais.add("Ervilha");
		graos_cereais.add("Fava");
		graos_cereais.add("Feijão");
		graos_cereais.add("Grão de Bico");
		graos_cereais.add("Grão de Milho(Polenta)");
		graos_cereais.add("Lentilha");
		graos_cereais.add("Milho");
		graos_cereais.add("Painço");
		graos_cereais.add("Quinoa");
		graos_cereais.add("Soja");
		graos_cereais.add("Trigo");
		graos_cereais.add("Painço");


//Lista de Higiene Pessoal
		List<String> hig_pes = new ArrayList<String>();
        hig_pes.add("Absorvente");
		hig_pes.add("Condicionador");
		hig_pes.add("Creme Dental");
		hig_pes.add("Creme/Espuma de Barbear");
		hig_pes.add("Creme para Pentear");
		hig_pes.add("Desodorante");
		hig_pes.add("Enxaguante Bucal");
		hig_pes.add("Fio Dental");
		hig_pes.add("Gel Antisséptico");
		hig_pes.add("Loção Hidratante");
		hig_pes.add("Loção Pós Barba");
		hig_pes.add("Hidratante Corporal");
		hig_pes.add("Repelente");
		hig_pes.add("Sabonete Esfoliante");
		hig_pes.add("Sabonete Hidratante");
		hig_pes.add("Sabonete Íntimo");
		hig_pes.add("Sabonete Liquido");
		hig_pes.add("Shampoo");
		hig_pes.add("Talco");


//Lista de Produtos de Limpeza
		List<String> limpeza = new ArrayList<String>();
        limpeza.add("Água Sanitária");
		limpeza.add("Álcool");
		limpeza.add("Cloro");
		limpeza.add("Desengordurante");
		limpeza.add("Desinfetante");
		limpeza.add("Detergente");
		limpeza.add("Esponjas");
		limpeza.add("Luvas");
		limpeza.add("Papéis Higiênicos");
		limpeza.add("Papéis Toalhas");
		limpeza.add("Sabão em barra");
		limpeza.add("Sabão em pó");
		limpeza.add("Saco para Lixo");


//Lista de sucos
        List<String> sucos = new ArrayList<String>();
        sucos.add("Suco de Abacaxi");
        sucos.add("Suco de Acerola");
        sucos.add("Suco de Ameixa");
        sucos.add("Suco de Cenoura");
        sucos.add("Suco de Cereja");
		sucos.add("Suco de Framboesa");
		sucos.add("Suco de Frutas");
		sucos.add("Suco de Groselha");
		sucos.add("Suco de Laranja");
		sucos.add("Suco de Lima");
		sucos.add("Suco de Maracujá");
		sucos.add("Suco de Pêssego");
		sucos.add("Suco de Romã");
		sucos.add("Suco de Tangerina");
		sucos.add("Suco de Tomate");
		sucos.add("Suco de Toranja");


//Lista de Verduras e Legumes
		List<String> verd_leg = new ArrayList<String>();
        verd_leg.add("Abóbora");
        verd_leg.add("Abobrinha");
		verd_leg.add("Açafrão");
		verd_leg.add("Acelga");
		verd_leg.add("Agrião");
		verd_leg.add("Alcachofra");
		verd_leg.add("Alcaparra");
		verd_leg.add("Alface");
		verd_leg.add("Alfafa");
		verd_leg.add("Azeitona");
		verd_leg.add("Batata");
		verd_leg.add("Berinjela");
		verd_leg.add("Beterraba");
		verd_leg.add("Brócolis");
		verd_leg.add("Café em pó");
		verd_leg.add("Café solúvel");
		verd_leg.add("Cebola");
		verd_leg.add("Cebolinha");
		verd_leg.add("Cenoura");
		verd_leg.add("Champignon");
		verd_leg.add("Chuchú");
		verd_leg.add("Coentro");
		verd_leg.add("Cogumelo");
		verd_leg.add("Cominho");
		verd_leg.add("Condessa");
		verd_leg.add("Couve de Bruxelas");
		verd_leg.add("Couve Flor");
		verd_leg.add("Couve Nabo");
		verd_leg.add("Espinafre");
		verd_leg.add("Gengibre");
		verd_leg.add("Gergelim");
		verd_leg.add("Grão de Bico");
		verd_leg.add("Inhame");
		verd_leg.add("Jiló");
		verd_leg.add("Lentilha");
		verd_leg.add("Lírio");
		verd_leg.add("Mandioca, Aipim");
		verd_leg.add("Maxixe");
		verd_leg.add("Milho");
		verd_leg.add("Nabo");
		verd_leg.add("Palmito");
		verd_leg.add("Pepino");
		verd_leg.add("Picles");
		verd_leg.add("Pimentão");
		verd_leg.add("Quiabo");
		verd_leg.add("Rabanete");
		verd_leg.add("Repolho");
		verd_leg.add("Salsa");áãâéêúíõôóçÁÓÚÉÍ
		verd_leg.add("Soja");
		verd_leg.add("Trigo");
		verd_leg.add("Vagem");
		
		expandableList.put(expandableListTitle.get(0), bebidas);
		expandableList.put(expandableListTitle.get(1), cald_molh_cond);
        expandableList.put(expandableListTitle.get(2), carnes_frios);
		expandableList.put(expandableListTitle.get(3), enla_conse);
		expandableList.put(expandableListTitle.get(4), frutas);
		expandableList.put(expandableListTitle.get(5), graos_cereais);
		expandableList.put(expandableListTitle.get(6), hig_pes);
		expandableList.put(expandableListTitle.get(7), limpeza);
        expandableList.put(expandableListTitle.get(8), sucos);
		expandableList.put(expandableListTitle.get(9), verd_leg);
	}
	
	public void addItemLista(String p, String idLista) {
		DatabaseHelper mDatabase = new DatabaseHelper(this);
		mDatabase.insertItem(p, idLista);
	}
}
