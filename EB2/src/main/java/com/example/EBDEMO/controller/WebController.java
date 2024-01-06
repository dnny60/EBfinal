package com.example.EBDEMO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.EBDEMO.model.Inventory;
import com.example.EBDEMO.service.InventoryService;

@Controller
public class WebController {
	
	@Autowired
    private InventoryService inventoryService;
	
	@GetMapping("/")
    public String index(Model model) {
		
		List<Inventory> inventories = inventoryService.getAllInventory();
	    model.addAttribute("inventories", inventories);
        return "index"; //
    }
}
