package com.example.EBDEMO.controller;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import com.example.EBDEMO.model.Inventory;
import com.example.EBDEMO.model.Tea;

import jakarta.persistence.EntityNotFoundException;

import com.example.EBDEMO.Repository.TeaRepository;

@Controller 
@RequestMapping(path="/tea")
public class TeaController {
	
	@Autowired
	private TeaRepository teaRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewTea (@RequestParam String TeaName
		      , @RequestParam BigDecimal UnitPrice, @RequestParam Integer TeaSpan) {
		    
		
		// @ResponseBody means the returned String is the response, not a view name
		    // @RequestParam means it is a parameter from the GET or POST request

		    Tea n = new Tea();
		    n.setTeaName(TeaName);
		    n.setUnitPrice(UnitPrice);
		    n.setTeaSpan(TeaSpan);
		    teaRepository.save(n);
		    return "Saved";
		  }
	
	
	@GetMapping(path="/all")
	  public @ResponseBody Iterable<Tea> getAllTea() {
	    // This returns a JSON or XML with the users
	    return teaRepository.findAll();
	  }
	
	// Get an TEA by ID
    @GetMapping("/get/{id}")
    public @ResponseBody Tea getTeaById(@PathVariable("id") String id) {
    	System.out.print(id);
        return teaRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("inventory not found with id " + id));
    }
    
    
 // Update an existing Tea
    @PutMapping("/update/{id}")
    public @ResponseBody Tea updateInventory(@PathVariable Long id
    		, @RequestParam String TeaName
    		, @RequestParam BigDecimal UnitPrice, @RequestParam Integer TeaSpan ) {
    	

        Tea tea = teaRepository.findById(id)
        		.orElseThrow(() -> new EntityNotFoundException("Tea not found with id " + id));
        
        
        // Update the order attributes
        tea.setTeaName(TeaName);
        tea.setTeaSpan(TeaSpan);
        tea.setUnitPrice(UnitPrice);

        return teaRepository.save(tea);
    }
    
    
    
 // Delete an Tea
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteTea(@PathVariable Long id) {
    	
    	Tea tea = teaRepository.findById(id)
        		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));

        
    	
        teaRepository.delete(tea);

        return ResponseEntity.ok().build();
    }

}
