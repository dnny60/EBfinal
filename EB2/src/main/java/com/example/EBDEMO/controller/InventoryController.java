package com.example.EBDEMO.controller;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import com.example.EBDEMO.model.Customer;
import com.example.EBDEMO.model.Inventory;
import com.example.EBDEMO.model.Order;
import com.example.EBDEMO.model.Tea;
import com.example.EBDEMO.Repository.InventoryRepository;
import com.example.EBDEMO.Repository.TeaRepository;

@Controller
@RequestMapping(path="/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private TeaRepository teaRepository;
	
	
	// Get all Inventories
		@GetMapping(path="/all")
	    public @ResponseBody Iterable<Inventory> getAllInventories() {
	        return inventoryRepository.findAll();
	    }
	
		
	// Get an Inventory by ID
    @GetMapping("/get/{id}")
    public @ResponseBody Inventory getInventoruById(@PathVariable("id") String id) {
    	System.out.print(id);
        return inventoryRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("inventory not found with id " + id));
    }
    
 // Create a new order
    @PostMapping(path = "/add")
    public @ResponseBody String createInventory(@RequestParam Long teaid
    		, @RequestParam BigDecimal InvQuantity
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date InvDate
    		, @RequestParam BigDecimal InvAmount, @RequestParam BigDecimal InvPrice ) {
    	
    	
    	Tea tea = teaRepository.findById(teaid)
    			.orElseThrow(() -> new EntityNotFoundException("Tea not found"));
    	
    	Inventory inventory = new Inventory();
    	inventory.setTea(tea);
    	inventory.setInvAmount(InvAmount);
    	inventory.setInvPrice(InvPrice);
    	inventory.setInvQuantity(InvQuantity);
    	inventory.setInvDate(InvDate);
		inventoryRepository.save(inventory);
	
		return "Saved";
    }
    
    
 // Update an existing inventory
    @PutMapping("/update/{id}")
    public @ResponseBody Inventory updateInventory(@PathVariable Long id
    		,@RequestParam Long teaid
    		, @RequestParam BigDecimal InvQuantity
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date InvDate
    		, @RequestParam BigDecimal InvAmount, @RequestParam BigDecimal InvPrice ) {
    	
        Inventory inventory = inventoryRepository.findById(id)
        		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));
        
        Tea tea = teaRepository.findById(teaid)
        		.orElseThrow(() -> new EntityNotFoundException("Tea not found with id " + teaid));
        
        
        // Update the order attributes
        inventory.setInvAmount(InvAmount);
        inventory.setInvPrice(InvPrice);
        inventory.setInvQuantity(InvQuantity);
        inventory.setTea(tea);
        inventory.setInvDate(InvDate);

        return inventoryRepository.save(inventory);
    }

    // Delete an Inventory
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteInventory(@PathVariable Long id) {
    	
    	Inventory inventory = inventoryRepository.findById(id)
        		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));

        
    	inventory.setTea(null);
    	inventoryRepository.save(inventory);
        inventoryRepository.delete(inventory);

        return ResponseEntity.ok().build();
    }
	
	

}
