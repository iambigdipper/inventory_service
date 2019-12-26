package com.meru.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.meru.inventory.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory,Integer> {

	
}
