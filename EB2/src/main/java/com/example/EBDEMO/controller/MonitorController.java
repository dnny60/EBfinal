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
import com.example.EBDEMO.model.Monitor;
import com.example.EBDEMO.model.Order;
import com.example.EBDEMO.model.Tea;
import com.example.EBDEMO.Repository.InventoryRepository;
import com.example.EBDEMO.Repository.MonitorRepository;
import com.example.EBDEMO.Repository.TeaRepository;

@Controller
@RequestMapping(path="/monitor")
public class MonitorController {
	
	@Autowired
	private MonitorRepository monitorRepository;
	
	@Autowired
	private TeaRepository teaRepository;
	
	
	// Get all monitor
		@GetMapping(path="/all")
	    public @ResponseBody Iterable<Monitor> getAllMonitor() {
	        return monitorRepository.findAll();
	    }
	
		
	// Get an Monitor by ID
    @GetMapping("/get/{id}")
    public @ResponseBody Monitor getMonitorById(@PathVariable("id") String id) {
    	System.out.print(id);
        return monitorRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("inventory not found with id " + id));
    }
    
 // Create a new Monitor
    @PostMapping(path = "/add")
    public @ResponseBody String createMonitor(@RequestParam Long teaid
    		, @RequestParam String status
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date MonDate
    		, @RequestParam BigDecimal MonAmount ) {
    	
    	
    	Tea tea = teaRepository.findById(teaid)
    			.orElseThrow(() -> new EntityNotFoundException("Tea not found"));
    	
    	Monitor monitor = new Monitor();
    	monitor.setMonAmount(MonAmount);
    	monitor.setMonDate(MonDate);
    	monitor.setStatus(status);
    	monitor.setTea(tea);
    	
		monitorRepository.save(monitor);
	
		return "Saved";
    }
    
    
 // Update an existing Monitor
    @PutMapping("/update/{id}")
    public @ResponseBody Monitor updateMonitor(@PathVariable Long id
    		,@RequestParam Long teaid
    		, @RequestParam String status
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date MonDate
    		, @RequestParam BigDecimal MonAmount ) {
    	
        Monitor monitor = monitorRepository.findById(id)
        		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));
        
        Tea tea = teaRepository.findById(teaid)
        		.orElseThrow(() -> new EntityNotFoundException("Tea not found with id " + teaid));
        
        
        // Update the order attributes
        monitor.setMonAmount(MonAmount);
        monitor.setMonDate(MonDate);
        monitor.setStatus(status);
        monitor.setTea(tea);
        

        return monitorRepository.save(monitor);
    }

    // Delete an Monitor
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteMonitor(@PathVariable Long id) {
    	
    	Monitor monitor = monitorRepository.findById(id)
        		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));

        
    	monitor.setTea(null);
    	monitorRepository.save(monitor);
        monitorRepository.delete(monitor);

        return ResponseEntity.ok().build();
    }
	
	

}
