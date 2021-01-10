package com.adriano.lista.model;

import java.util.Calendar;
import java.util.Date;
import java.util.*;

public class Lista {
	
	Integer id;
	String lista;
	Date data;
	Boolean isSaved;
	 
	//List<Lista> listas;
	Double valor;

	public Lista() {
		this.data = Calendar.getInstance().getTime();
		this.isSaved = false;
    }
/*
	public Lista(Integer id, String lista, Double valor, Date data, Boolean isSaved) {
		this.setId(id);
		this.setLista(lista);
		this.setValor(valor);
		this.setData(data);
		this.setIsSaved(isSaved);
    }*/
	
    public Lista(String lista, Double valor, Date data, boolean isSaved) {
		this.setLista(lista);
		this.setValor(valor);
		this.setData(data);
		this.setIsSaved(isSaved);
    }
	/*
	public Lista(Double valor, boolean isSaved, List<Lista> listas) {
		this.setValor(valor);
		this.isSaved = isSaved;
		this.listas = listas;
    }*/
	
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
	/*
	public List<Lista> getListas() {
        return listas;
    }
	public void setListas(List<Lista> listas) {
        this.listas = listas;
    }*/
	
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
