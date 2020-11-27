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
			Toast.makeText(this, "id da lista esta null", Toast.LENGTH_LONG).show(); 
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

        expandableListTitle.add("Graos");
		expandableListTitle.add("Verduras e Legumes");
        expandableListTitle.add("Frutas");
        expandableListTitle.add("Sucos");
		expandableListTitle.add("Molhos");
		expandableListTitle.add("Caldos");
		expandableListTitle.add("Outros condimentos");

        List<String> graos = new ArrayList<String>();
        graos.add("Alpiste");
        graos.add("Amendoim");
        graos.add("Arroz");
        graos.add("Aspargo");
        graos.add("Abóbora Moranga");
        graos.add("Abobrinha");
        graos.add("Abricó");
		/*
		 Alho
		 Alho em pó
		 Alho Porro
		 Alho, folha
		 Almecega
		 Almeirão
		 
		 Amaranto
		 Amêndoa
		 Amêndoa Doce
		 Amêndoa Tropical
		 
		 Amendoim Amarelo, cru
		 Amendoim Preto, cru
		 Amendoim Roxo, cru
		 Amendoim, caramelizado
		 Amendoim, coberto com chococate
		 Amendoim, cozido
		 Amendoim, creme de
		 Amendoim, torrado com sal
		 Amido de Arroz
		 Amido de Milho (cozido)
		 Amido de Milho (cru)
		 Ananás
		 Ançarinha
		 Angu de Milho
		 Araça
		 Araruta, tubérculo
		 Arobon
		 Arroz Agulha Brunido, cru
		 Arroz Agulha Integral, cru
		 Arroz Banhado de Iguapé, cru
		 Arroz Branco, cozido
		 Arroz Branco, média, cru
		 Arroz Carolina, cru
		 Arroz Carreteiro Cozinha e Sabor TIO JOÃO
		 Arroz Carreteiro MAGGI
		 Arroz Cozinha Italiana TIO JOÃO
		 Arroz Cozinha Japonesa TIO JOÃO
		 Arroz Cozinha Tailandesa TIO JOÃO
		 Arroz de Maio Brunido, cru
		 Arroz de Maio Integral, cru
		 Arroz do Japão, cru
		 Arroz Integral, cozido
		 Arroz Integral, cru
		 Arroz Miúdo do Peru, farinha
		 Arroz Miúdo do Peru, grão, cru
		 Arroz Moçambique Brunido, cru
		 Arroz Moçambique Integral, cru
		 Arroz Pardo, cru
		 Arroz polido, cozido
		 Arroz Polido, cru
		 Arroz Preto de Pindamonhangaba, cru
		 Arroz Selvagem TIO JOÃO
		 Arroz Selvagem, cru
		 Arroz Vitaminado TIO JOÃO
		 Arroz, bolinho frito
		 Arroz, cozido
		 Arroz, farelo de
		 Arroz, risoto, à la grega
		 Aspargo cozido
		 Aspargo em conserva
		 Aveia de preparo instantaneo
		 Aveia, flocos cozidos
		 Aveia, flocos crus
		 Aveia, grão cru
		*/
		List<String> verd_leg = new ArrayList<String>();
        verd_leg.add("Abiu");
        verd_leg.add("Abóbora Chila");
        verd_leg.add("Abóbora d' Água");
        verd_leg.add("Abóbora Menina");
        verd_leg.add("Abóbora Moranga");
        verd_leg.add("Abobrinha");
        verd_leg.add("Abricó");
		verd_leg.add("Açafrão");
		verd_leg.add("Acelga");
		verd_leg.add("Agrião");
		verd_leg.add("Aipim ou Mandioca");
		verd_leg.add("Aipo");
/*
		 
		 Alcachofra, coração
		 Alcachofra, inteira
		 Alcachofra-de-Jerusalém
		 Alcaparra
		 Alface
		 Alface Francesa
		 Alface Romana
		 Alfafa
		 Alfafa de sementes espinhosas
		 Alfavaca em pó
		 Algaroba, farinha de
		 Algaroba, vagem
		 Algaroba, vagem e semente
		 Algas Marinhas
		 Azedinha
		 Azedinha da Horta
		 Azedinha, crespa
		 Azeitona, madura, conserva
		 Azeitona, nacional, preta
		 Azeitona, preta, parte comestivel
		 Azeitona, preta, todo fruto
		 Azeitona, verde, em conserva
		 Azeitona, verde, parte comestivel
		 Azeitona, verde, todo fruto
		 Batata Aipo Amarela, cabeça
		 Batata Aipo Amarela, raízes
		 Batata Doce, amarela, assada
		 Batata Doce, amarela, crua
		 Batata Doce, amarela, frita
		 Batata Doce, branca, cozida
		 Batata Doce, branca, crua
		 Batata Doce, desidratada
		 Batata Doce, folhas de
		 Batata Doce, roxa, crua
		 Batata Inglesa, amido
		 Batata Inglesa, cozida
		 Batata Inglesa, crua
		 Batata Inglesa, desidratada
		 Batata Inglesa, fécula
		 Batata Inglesa, frita
		 Batata Japonesa
		 Beijupirá
		 Beldroega
		 Benincasa (abóbora branca)
		 Berinjela em conserva
		 Berinjela, cozida, picada
		 Berinjela, crua
		 Bertalha
		 Besugo
		 Beterraba, cozida
		 Beterraba, crua
		 Beterraba, em conserva
		 Beterraba, folhas
		 Beterraba, purê
		 Bicuda
		 Biquara
		 Brócolis
		 Brócolis, flores cozidas
		 Brócolis, flores cruas
		 Brócolis, folhas
		 Broto de Abóbora
		 Broto de Bambu
		 Brotos de Cebola
		 Brotos de Chuchú
		 Brotos de Feijão, crus
		 Café, em pó
		 Café, solúvel
		 Caieté, amêndoa
		 Camafeu
		 Cambucá
		 Canjica, de milho
		 Cará
		 Cara Branco
		 Cará Caratinga
		 Cará Caratinga Brava
		 Cará de Angola
		 Cará Guiné
		 Cará Mandioca
		 Cará Mimoso
		 Cará Moela
		 Cará ou Inhame cozido
		 Cará Pé de Anta
		 Cará Roxo
		 Cará Sapateiro
		 Carne Vegetal (de soja)
		 Cebola
		 Cebola, cozida
		 Cebola, crua
		 Cebola, desidratada
		 Cebolinha
		 Cebolinha Branca, bulbu, cru
		 Cebolinha Branca, bulbu, em conserva de vinagre
		 Cebolinha, bulbo e folhas
		 Cebolinha, folhas
		 Cebolinha, talos
		 Cenoura Amarela, cozida
		 Cenoura Amarela, crua
		 Cenoura Amarela, desidratada
		 Cenoura, cozida, inteira
		 Cenoura, cozida, picada
		 Cenoura, crua, inteira
		 Cenoura, crua, ralada
		 Cenoura, em conserva
		 Centeio em Grão
		 Cevada, em grão
		 Cevada, perlada
		 Cevada, torrada
		 Chicória
		 Chuchú
		 Chuchu, branco
		 Chuchú, maduro
		 Chuchú, muito verde
		 Chuchú, verde
		 Cobió-de-Pará
		 Coentro
		 Cogumelo, bolletus edulis
		 Cogumelo, bolletus sp.
		 Cogumelo, em conserva
		 Cogumelo, fresco (média)
		 Cominho
		 Cominho, em pó
		 Condessa
		 Consomé
		 Copas
		 Couve Chinesa
		 Couve de Bruxelas
		 Couve de Bruxelas, brotos, cozidos
		 Couve de Bruxelas, brotos, crus
		 Couve Flor
		 Couve Flor, cozida, só a inflorescência
		 Couve Flor, crua, só a inflorescência
		 Couve Gigante
		 Couve Manteiga
		 Couve Nabo, folhas
		 Couve Nabo, tubérculo
		 Couve Rapa
		 Couve Tronchuda
		 Cruz de Malta
		 Cumandatiá, sementes secas
		 Cumandatiá, sementes verdes
		 Cumandatiá, vagem verde
		 Curry
		 Daiquiri, enlatada
		 Daiquiri, preparado de receita
		 Dendê, amêndoa
		 Dendê, polpa
		 Dente de Leão
		 Eledon, em pó
		 Eledon, reconstituído
		 Ervilha
		 Ervilhaca
		 Escorcioneira
		 Espiga de Milho, cozida
		 Espinafre
		 Fava, grão seco
		 Fava, grão verde
		 Feijão
		 Gengibre, em pó
		 Gengibre, rizoma
		 Gergelim, sementes
		 Grão de Bico verde, dessecado, cru
		 Grão de Bico verde, não dessecado
		 Grão de Bico, em conserva
		 Grão-de-Bico, cozido
		 Inhame Branco, cru
		 Inhame, raiz sem casca
		 Inhame, talos
		 Jiló
		 Legumes (média)
		 Lentilha d' Água
		 Lentilha, cozida
		 Lentilha, seca, cozida
		 Lentilha, seca, crua
		 Lírio Chinês, bulho fresco
		 Lírio Chinês, bulho seco
		 Mandioca, cozida
		 Mandioca, frita
		 Maxixe
		 Milhete, grão inteiro
		 Milho Doce, cozido
		 Milho Doce, cru
		 Milho Doce, envasado
		 Milho Doce, envasado, só o milho
		 Milho Duro
		 Milho Mole
		 Milho Seco, amarelo
		 Milho Verde, cru
		 Milho Verde, em conserva, enlatado
		 Milho, amarelo, cru
		 Milho, branco, cru
		 Milho, cozido após congelado
		 Mostarda Crespa, folha cozida
		 Mostarda Crespa, folha crua
		 Nabiça, folhas
		 Nabo, bulbo, cozido
		 Nabo, bulbo, cru
		 Nabo, bulbo, desidratado
		 Nabo, folhas de
		 Palmito, cru
		 Palmito, em conserva
		 Pastinaga, raiz
		 Pepino do Mato
		 Pepino, com casca, picles
		 Pepino, cru, com casca
		 Pepino, cru, sem casca
		 Pepino, em salmoura
		 Picles
		 Pimentão Doce, cru
		 Pimentão Doce, envasado
		 Pimentão, maduro, cru
		 Pimentão, sementes
		 Pimentão, verde, cozido
		 Pimentão, verde, cru
		 Pimentão, verde, dessecado
		 Pistacho, amêndoa
		 Purê de Batata
		 Purê de Cenoura
		 Purê de Lentilha, em conserva
		 Pûre de Tomate, enlatado
		 Quiabo, cozido
		 Quiabo, cru
		 Quínoa, grão
		 Rabada, crua
		 Rabanete
		 Rábano, bulbo
		 Rábano, folhas
		 Repolho Chinês
		 Repolho, cozido
		 Repolho, cru
		 Repolho, desidratado
		 Rinchão, folhas
		 Risoto à Grega Cozinha Fácil TIO JOÃO
		 Risoto à Parmegiana Cozinha & Sabor TIO JOÃO
		 Risoto com Brócolis Cozinha Fácil TIO JOÃO
		 Risoto com Champignon e Funghi MAGGI
		 Risoto com Curry Cozinha Fácil TIO JOÃO
		 Risoto com Funghi Cozinha Fácil TIO JOÃO
		 Risoto Milanês Cozinha e Sabor TIO JOÃO
		 Ruibarbo, talos
		 Salsa
		 Samambaia das Taperas, pecíolo
		 Sapota
		 Sapota, branca
		 Sapoti
		 Sapucaia
		 Sapucaia, castanha
		 Semente de Abóbora
		 Sementes de Girassol
		 Sementes de Glicéria
		 Sementes de Sumauma
		 Sêmola de Milho
		 Sêmola de Trigo, crua
		 Sêmola de Trigo, fervida
		 Semolina de Trigo
		 Soja, cozida
		 Soja, crua
		 Tomate, cozido
		 Tomate, em flocos
		 Tomate, envasado
		 Tomate, francês
		 Tomate, imaturo
		 Tomate, inteiro, em conserva
		 Tomate, maduro
		 Tomate, morango
		 Toranja
		 Trigo Duro, flocos
		 Trigo Duro, grão inteiro
		 Trigo Sarraceno
		 Trigo, cozido
		 Vagem de Ervilha, verde
		 Vagem Metro ou Corda
		 Vagem, comum, crua
		 Vagem, comum, em conserva
		 Vagem, cozida, picada
		 Vegetais, em conserva, média
		 Vegetais, misturados, congelados, cozidos
		 Aveia Nestle Integral
		 Batata doce roxa cozida
		  QUAKER
		 
		 Bouquet Floretes de Legumes DAUCY
		 Batata Noisettes MCCAIN
		 Batata Palito MCCAIN
		 Batata Palito PERDIGÃO
		 Batata Palito Pré-Frita C.A.C
		 Batata Palito VERT´S
		 Batata Parisiense BONDUELLE
		 Batata Rissolée MCCAIN
		 Bouquet de Legumes com Brocolis DAUCY
		 Bouquet de Legumes com Ervilhas DAUCY
		 Bouquet Legumes com Tomate DAUCY
		 Brócolis BONDUELLE
		 Brócolis PRATIGEL
		 Cenoura Baby BONDUELLE
		 Champignon BONDUELLE
		 Couve de Bruxelas BONDUELLE
		 Couve Flor BONDUELLE
		 Couve Flor PRATIGEL
		 Couve Manteiga PRATIGEL
		 Ervilha BONDUELLE
		 Ervilha PRATIGEL
		 Espinafre PRATIGEL
		 Favas BONDUELLE
		 Feijão Congelado BROTO LEGAL
		 Fundo de Alcachofra BONDUELLE
		 Legumes à Moda Bretã BONDUELLE
		 Legumes para Minestrone BONDUELLE
		 Mandioca Palito PRATIGEL
		 Mandioca Tolete PRATIGEL
		 Milho Verde BONDUELLE
		 Salada Primavera PRATIGEL
		 Salada Russa BONDUELLE
		 Salada Sombrero BONDUELLE
		 Salada Verão PRATIGEL
		 Seleta de Legumes PRATIGEL
		 Soja Congelada BROTO LEGAL
		 Vagem Fina 
*/
        List<String> frutas = new ArrayList<String>();
        frutas.add("Abacaxi");
        frutas.add("Açaí");
        frutas.add("Acerola");
        frutas.add("Ameixa");
		frutas.add("Amora");
		frutas.add("Avelã");
		frutas.add("Banana");
		frutas.add("Buriti");
		frutas.add("Butiá");
		frutas.add("Cajá");
		frutas.add("Cajú");
		frutas.add("Camboatá");
		frutas.add("Canopi");
		frutas.add("Caqui");
		frutas.add("Carambola");
		frutas.add("Carurú");
		/*
		 
		 Cereja
		
		 Côco Babaçu, amêndoa
		 Côco Catolé
		 Côco de Catarro, polpa
		 Côco de Macaúba
		 Côco de Tucum
		 Côco Ralado, fresco
		 Côco Ralado, seco
		 Côco, carne
		 Composta de Maçã
		 Cuieira, fruto, polpa
		 Cumari, amêndoa
		 Cupuaçu
		 Damasco
		 Damasco, dessecado
		 Damasco, em conserva
		 Damasco, fresco
		 Figo
		 Figo da Índia, amarelo
		 Figo da Índia, vermelho
		 Figo, cozido
		 Figo, dessecado
		 Figo, em calda
		 Figo, maduro
		 Figo, verde
		 Framboesa
		 Framboesa, em calda
		 Framboesa, em conserva, em água, enlatada
		 Fruta de Conde, Ata ou Pinha
		 Fruta Pão, cozida
		 Fruta Pão, crua
		 Frutas Cristalizadas, caseiro
		 Frutas Cristalizadas, industrializadas
		 Frutas em Coquetel
		 Goiaba
		 Gravatá
		 Graviola
		 Groselha da Índia
		 Groselha, branca
		 Groselha, preta
		 Grumixama
		 Guabiroba
		 Jabuticaba
		 Jaca
		 Jaca, caroço
		 Jaca, polpa
		 Jamelão
		 Jatobá
		 Jenipapo
		 Jenipapo, desidratado
		 Kiwi
		 Laranja
		 Laranja China
		 Laranja da Bahia
		 Laranja da Terra
		 Laranja Pêra
		 Laranja Seleta
		 Laranja Seleta Itaboraí
		 Laranja, compota de
		 Laranjinha Japonesa
		 Lima
		 Lima de Pérsia
		 Limão
		 Limão Caiena
		 Limão de Vez
		 Maçã
		 Maçã, branca, crua
		 Maçã, dessecada
		 Maçã, em conserva, enlatada
		 Maçã, vermelha, crua
		 Mamão
		 Mamão, maduro
		 Mamão, verde
		 Manga
		 Manga Espada
		 Manga Rosa
		 Manga, em calda
		 Maracujá Melão
		 Maracujá Vermelho
		 Maracujá, comum, polpa
		 Marmelo
		 Melancia
		 Melão
		 Morango
		 Morango, envasado em água
		 Morango, envasado em xarope
		 Néspera
		 Passa de Maçã
		 Passas
		 Passas, com sementes
		 Pêra
		 Pêra, dessecada
		 Pêra, enlatada, com açucar
		 Pêra, enlatada, em água
		 Pescada (peixe)
		 Pêssego
		 Pêssego, amarelo
		 Pêssego, branco
		 Pêssego, em calda, enlatado
		 Pêssego, em conserva, enlatado
		 Pinhão, cozido
		 Pinhão, cru
		 Pitanga
		 Polpa de Figo da Barbária
		 Polpa de Guajiru
		 Polpa de Ingá
		 Purê de Maça, açucarado
		 Sapota, branca
		 Romã
		 Salada de Frutas, caseira
		 Sapota
		 Sapoti
		 Sapucaia
		 Sapucaia, castanha
		 Siriguela ou Ciriguela
		 Suco de Cajú
		 Suco de Limão Verde
		 Suco de Maçã
		 Tâmara, ao natural
		 Tâmara, dessecada
		 Tamarindo, polpa
		 Tangerina
		 Taperebá
		 Toranja
		 Urucum, dessecado
		 Urucum, polpa
		 Uva, branca, nacional, polpa
		 Uva, tipo americano
		 Uva, tipo europeu
		 Uvaia ou Uvalha
		 Vitamina de Frutas, com leite
		 Vitamina de Frutas, sem leite
		 Banana
		 Abacate
		*/

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
		/*
		 */
		List<String> frios = new ArrayList<String>();
		frios.add("Extrato de Tomate");
        frios.add("Molho de Tomate");
        frios.add("Molho à Bolonhesa");
        frios.add("Molho Barbecue");
        frios.add("Molho Bordelaise");
		/*
		 Apresuntado 
		 Blanquet de Peru
		 Copa 
		 Lingüiça 
		 Lombo Condimentado
		 Mini Chester Lanche PERDIGÃO
		 Mortadela 
		 Pasta de Enchova (peixe)
		 Peito de Peru
		 Presunto 
		 Ricota
		 Rosbife
		 Roulé de Peru
		 Salame 
		 Salsicha 
		 Salsichão
		*/
		
		List<String> molhos = new ArrayList<String>();
		molhos.add("Extrato de Tomate");
        molhos.add("Molho de Tomate");
        molhos.add("Molho à Bolonhesa");
        molhos.add("Molho Barbecue");
        molhos.add("Molho Bordelaise");
		/*

		 Molho de Atum
		 Molho de Gergelim
		 Molho de Pimenta
		 Molho de Pimentão Vermelho
		 Molho de Soja
		 Molho de Tomate
		 Molho de Tomate à Bolonhesa POMAROLA
		 Molho de Tomate Básico TARANTELLA
		 Molho de Tomate Basilico BARILLA
		 Molho de Tomate Clássico KNORR
		 Molho de Tomate com Ervas Finas JUREMA
		 Molho de Tomate com Manjericão KNORR
		 Molho de Tomate com Salsa e Cebolinha POMAROLA
		 Molho de Tomate Costela POMAROLA
		 Molho de Tomate Napolitana BARILLA
		 Molho de Tomate para Pizza POMAROLA
		 Molho de Tomate Peneirado com Manjericão POMAROLA
		 Molho de Tomate Peneirado Pomarola CICA
		 Molho de Tomate Peneirado POMAROLA Costela
		 Molho de Tomate Picante POMAROLA
		 Molho de Tomate Refogado POMAROLA
		 Molho de Tomate Refogado PREDILECTA
		 Molho de Tomate Refogado Salsaretti ETTI
		 Molho de Tomate Tarantella ARISCO
		 Molho de Tomate Temperado com Caldo de Galinha POMAROLA
		 Molho de Tomate Tradicional JUREMA
		 Molho de Tomate, em lata
		 Molho Inglês
		 Molho Mostarda do McDonald's
		 Molho Teriyaki
		 Mollho de Tucupi
		 Polpa de Tomate Pomodoro CICA
		 Polpa de Tomate TOMATINO
		 Polpa de Tomate Tomato ARISCO
		 Shoyo (molho)
		 Tomate Pelado Italiano RAIOLA
		 */
		List<String> caldos = new ArrayList<String>();
		caldos.add("Caldo de Carne");
        caldos.add("Caldo de Bacon");
        caldos.add("Caldo de Cebola e Alho");
        caldos.add("Caldo de Costela");
        caldos.add("Caldo de Galinha");
		caldos.add("Caldo de Legumes e Verduras");
		caldos.add("Caldo de Picanha");
		caldos.add("Caldo para Arroz Branco");
		caldos.add("Caldo de Galinha");
		caldos.add("Caldo de Galinha");
		caldos.add("Caldo de Galinha");
		caldos.add("Caldo de Galinha");
		caldos.add("Caldo de Galinha");
		caldos.add("Caldo de Galinha");
		caldos.add("Caldo de Galinha");
		/*
		 
		 */
		List<String> condimentos = new ArrayList<String>();
		condimentos.add("Erva Doce");
        condimentos.add("Gengibre");
        condimentos.add("Hortelã");
        condimentos.add("Orégano");
        condimentos.add("Pimenta");
		condimentos.add("Sal");
		condimentos.add("Salsa");
		condimentos.add("Vinagre");
		condimentos.add("Vinagreira ou Caruru Azedo");
		 
		
        expandableList.put(expandableListTitle.get(0), graos);
		expandableList.put(expandableListTitle.get(1), verd_leg);
        expandableList.put(expandableListTitle.get(2), frutas);
        expandableList.put(expandableListTitle.get(3), sucos);
		expandableList.put(expandableListTitle.get(4), molhos);
		expandableList.put(expandableListTitle.get(5), caldos);
		expandableList.put(expandableListTitle.get(6), condimentos);
	}
	
	public void addItemLista(String p, String idLista) {
		DatabaseHelper mDatabase = new DatabaseHelper(this);
		mDatabase.insertItem(p, idLista);
	}
}
