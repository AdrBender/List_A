package com.adriano.lista.model;

/**
 * @author AdrBender
 */
public class Item {
	private String item;
	private int idItem;
	private int status = 0;
	
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
}
