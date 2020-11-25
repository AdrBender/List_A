package com.adriano.lista.model;

public class Lista {

	 Integer id;
     String lista;

	public Lista() {
    }

    public Lista(String lista) {
		//this.setId(id);
        this.setLista(lista);
    }
	
    public Integer getId() {
        return id;
    }
	public void setId(Integer id) {
        this.id = id;
    }

    public String getLista() {
        return lista;
    }
    public void setLista(String lista) {
        this.lista = lista;
    }
}
