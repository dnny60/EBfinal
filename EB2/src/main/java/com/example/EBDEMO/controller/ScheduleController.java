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
import com.example.EBDEMO.model.Schedule;
import com.example.EBDEMO.model.Tea;
import com.example.EBDEMO.Repository.InventoryRepository;
import com.example.EBDEMO.Repository.MonitorRepository;
import com.example.EBDEMO.Repository.ScheduleRepository;
import com.example.EBDEMO.Repository.TeaRepository;

@Controller
@RequestMapping(path="/schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private TeaRepository teaRepository;
	
	
	// Get all Schedule
		@GetMapping(path="/all")
	    public @ResponseBody Iterable<Schedule> getAllMonitor() {
	        return scheduleRepository.findAll();
	    }
	
		
	// Get an Schedule by ID
    @GetMapping("/get/{id}")
    public @ResponseBody Schedule getMonitorById(@PathVariable("id") String id) {
    	System.out.print(id);
        return scheduleRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("inventory not found with id " + id));
    }
    
 // Create a new Schedule
    @PostMapping(path = "/add")
    public @ResponseBody String createSchedule(@RequestParam Long teaid
    		, @RequestParam String status
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate ) {
    	
    	
    	Tea tea = teaRepository.findById(teaid)
    			.orElseThrow(() -> new EntityNotFoundException("Tea not found"));
    	
    	Schedule schedule = new Schedule();
    	schedule.setEndDate(endDate);
    	schedule.setStartDate(startDate);
    	schedule.setStatus(status);
    	schedule.setTea(tea);
    	
    	scheduleRepository.save(schedule);
	
		return "Saved";
    }
    
    
 // Update an existing Schedule
    @PutMapping("/update/{id}")
    public @ResponseBody Schedule updateSchedule(@PathVariable Long id
    		,@RequestParam Long teaid
    		, @RequestParam String status
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate
    		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate ) {
    	
        Schedule schedule = scheduleRepository.findById(id)
        		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));
        
        Tea tea = teaRepository.findById(teaid)
        		.orElseThrow(() -> new EntityNotFoundException("Tea not found with id " + teaid));
        
        
        // Update the order attributes
        schedule.setEndDate(endDate);
        schedule.setStartDate(startDate);
        schedule.setStatus(status);
    	schedule.setTea(tea);
        

        return scheduleRepository.save(schedule);
    }

    // Delete an Schedule
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
    	
    	Schedule schedule = scheduleRepository.findById(id)
        		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));

        
    	schedule.setTea(null);
    	scheduleRepository.save(schedule);
        scheduleRepository.delete(schedule);

        return ResponseEntity.ok().build();
    }
	
	

}