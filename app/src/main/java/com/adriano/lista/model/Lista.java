package com.adriano.lista.model;

import java.util.Calendar;
import java.util.Date;
import java.util.*;

/**
 * @author AdrBender
 */
public class Lista {
	
	Integer id;
	String lista;
	Date data;
	Boolean isSaved;
	int quantidade=0;
	Double valor;

	public Lista() {
		this.data = Calendar.getInstance().getTime();
		this.isSaved = false;
    }
	
	public Lista(String lista, Date data, boolean isSaved) {
		this.setLista(lista);
		this.setValor(valor);
		this.setData(data);
		this.setIsSaved(isSaved);
    }
	
    public Lista(String lista, Double valor, Date data, boolean isSaved) {
		this.setLista(lista);
		this.setValor(valor);
		this.setData(data);
		this.setIsSaved(isSaved);
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
	
	public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
	
	public boolean isSaved() {
        return isSaved;
    }
	
	public Boolean getIsSaved() {
		return isSaved;
	}

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }
	
	public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
