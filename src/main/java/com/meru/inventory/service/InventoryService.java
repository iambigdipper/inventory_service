package com.meru.inventory.service;

import java.security.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.meru.inventory.event.InventoryEvent;
import com.meru.inventory.model.Inventory;
import com.meru.inventory.repository.InventoryRepository;

@Service
public class InventoryService {
	
private final static String SUCCESS_STATUS="success";
private final static String FAILURE_STATUS="fail";
	
 @Autowired
 InventoryRepository inventoryRepository;
 @Autowired
 JmsTemplate jmsTemplate;
 @Autowired
 private Queue queue;
 boolean isStockUpdated;	
 
 public List<Inventory> getInventoryDetails() {
	 List <Inventory> inventoryList=new ArrayList<Inventory>();
	 Iterator <Inventory> inventoryIterator =inventoryRepository.findAll().iterator();
	 while(inventoryIterator.hasNext()) {
		 inventoryList.add(inventoryIterator.next());
		}
		return inventoryList;
 }
 
 public String updateInventory(int productid,int productsOrdered) {
	 Inventory inventory=getInventoryDetailsForProduct(productid);
	 int stock=inventory.getStock();
	 int stockUpdated=stock-productsOrdered;
	 inventory.setStock(stockUpdated);
	 Inventory inventoryUpdated=inventoryRepository.save(inventory);
	 if(inventoryUpdated.getStock()==stockUpdated) {
		 isStockUpdated=true;
		 try {
			EventStockUpdated(inventory);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return FAILURE_STATUS;
		}
		 return SUCCESS_STATUS;
	 }
	 else return FAILURE_STATUS;
 }
 
 
 public String addToInventory(Inventory inventory) {
	 Inventory inventoryAdded=inventoryRepository.save(inventory);
	 if(inventoryAdded!=null) {
		 isStockUpdated=true;
			/*
			 * try { //EventStockUpdated(inventoryAdded); } catch (JMSException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); return FAILURE_STATUS; }
			 */
		 return SUCCESS_STATUS;
	 }
	 return FAILURE_STATUS;
 }
 
 public Inventory getInventoryDetailsForProduct(int productid) {
	 Inventory inventory =inventoryRepository.findById(productid).get();
	 return inventory;
 }
 
 private void EventStockUpdated(Inventory inventory) throws JMSException
	{
	    Date date=new Date();
	    Instant instant = date.toInstant();
		System.out.println(" Stock Updated Event at "+instant);
		InventoryEvent inventoryEvent=new InventoryEvent("E"+inventory.getProductid(),inventory.getProductid(), inventory.getStock(),instant);
		Gson gson=new Gson();
		String  eventStr=gson.toJson(inventoryEvent);
		System.out.println("Intiate Event to queue "+queue.getQueueName());
		jmsTemplate.convertAndSend(queue, eventStr);
	
	}


}
