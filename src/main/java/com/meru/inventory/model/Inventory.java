package com.meru.inventory.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Inventory {

	public Inventory() {
	}
	
	public Inventory(int productid,int stock) {
		this.productid=productid;
		this.stock=stock;
	}
	
	@Id
	private int productid;
	private int stock;
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
