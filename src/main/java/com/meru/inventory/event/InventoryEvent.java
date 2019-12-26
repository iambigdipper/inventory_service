package com.meru.inventory.event;

import java.security.Timestamp;
import java.time.Instant;

public class InventoryEvent {
	
	private String eventid;
	private int productid;
	private int stock;
	private Instant eventtime;
	
	
	public InventoryEvent() {
		super();
		
	}
	public InventoryEvent(String eventid, int customerid,int stock ,Instant eventtime) {
		super();
		this.eventid = eventid;
		this.productid = customerid;
		this.stock=stock;
		this.eventtime = eventtime;
	}

	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public Instant getEventtime() {
		return eventtime;
	}
	public void setEventtime(Instant eventtime) {
		this.eventtime = eventtime;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
