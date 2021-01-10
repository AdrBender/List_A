package com.adriano.lista.model;

public class Item {
	private String item;
	private int idItem;
	private int status = 0;
	
	//private int quantidade;
	
	public Item(){}
	
	Item(String item, int status) {
		this.setItem(item);
		this.setStatus(status);
	}
	
	public void setItem(String item) {
		this.item = item;
	}

	public String getItem()	{
		return item;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getIdItem() {
		return idItem;
	}
	 
	public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
	
	/*
	 public String getTotalItens() {
	 return total_itens;
	 }
	 public void setTotalItens(String total_itens) {
	 this.total_itens = total_itens;
	 }

	 public String getValorTotal() {
	 return valor_total;
	 }
	 public void setValorTotal(String valor_total) {
	 this.valor_total = valor_total;
	 }*/
}
