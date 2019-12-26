package com.meru.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meru.inventory.model.Inventory;
import com.meru.inventory.service.InventoryService;

@RestController
public class InventoryController {

	private final static String SUCCESS_STATUS="success";
	private final static String FAILURE_STATUS="fail";
	boolean isStockUpdated;
	@Autowired
	private InventoryService inventoryService; 

	@RequestMapping(method=RequestMethod.PUT,value="/updatestock/{productid}/{productsordered}")
	public String updateStock(@PathVariable int productid , @PathVariable int productsordered) {
		String status=inventoryService.updateInventory(productid, productsordered);
		return status;
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/addstock")
	public String addStock(@RequestBody Inventory inventory) {
		String status=inventoryService.addToInventory(inventory);
		return status;
	}
	
	@RequestMapping("/getinventorydetails")
	public List<Inventory> getInventoryDetails() {
		return inventoryService.getInventoryDetails();
	}
	
	
}
