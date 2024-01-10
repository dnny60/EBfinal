package com.example.EBDEMO.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.EBDEMO.model.Customer;
import com.example.EBDEMO.model.GrowthDetail;
import com.example.EBDEMO.model.Inventory;
import com.example.EBDEMO.model.Order;
import com.example.EBDEMO.model.PlantingDetail;
import com.example.EBDEMO.service.GrowthDetailService;
import com.example.EBDEMO.service.InventoryService;
import com.example.EBDEMO.service.PlantingDetailDTO;
import com.example.EBDEMO.service.SortedOrdersService;
import com.example.EBDEMO.service.PlantingDetailService;

import com.example.EBDEMO.Repository.CustomerRepository;
import com.example.EBDEMO.service.CustomerService;


@Controller
public class WebController {
	
	@Autowired
    private InventoryService inventoryService;
	
	@Autowired
	private PlantingDetailService plantingDetailService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SortedOrdersService sortedOrdersService;
	
	@Autowired GrowthDetailService growthDetailService;
	
//	@GetMapping("/")
//    public String index(Model model) {
//		
//		List<Inventory> inventories = inventoryService.getAllInventory();
//	    model.addAttribute("inventories", inventories);
//        return "index"; //
//    }
	
	@GetMapping("/")
	public String index(Model model) {
	    // 獲取庫存數據
	    List<Inventory> inventories = inventoryService.getAllInventory();
	    model.addAttribute("inventories", inventories);

	    // 獲取種植詳細數據
	    List<PlantingDetail> plantingDetails = plantingDetailService.getAllPlantingDetails();
	    List<PlantingDetailDTO> plantingDetailDTOs = plantingDetails.stream().map(detail -> {
	        LocalDate plantDateLocal = convertToLocalDateViaUtilDate(detail.getPlantDate());
	        LocalDate estimatedHarvestDateLocal = plantDateLocal.plusDays(50);
	        Date estimatedHarvestDate = java.sql.Date.valueOf(estimatedHarvestDateLocal);
	        PlantingDetailDTO dto = new PlantingDetailDTO();
	        dto.setPlantingDetail(detail);
	        dto.setEstimatedHarvestDate(estimatedHarvestDate); // 现在是 java.util.Date 类型
	        return dto;
	    }).collect(Collectors.toList());
	    model.addAttribute("plantingDetailDTOs", plantingDetailDTOs);
	    
	    //獲取訂單資料
	    List<Order> sortedOrders = sortedOrdersService.getAllSortedOrder();
	    for (Order order : sortedOrders) {
	    	System.out.print(order.getCustomer().getcName());
	        System.out.println(order);
	    }
	    model.addAttribute("orders", sortedOrders);
	    
	    //獲取客戶資料
	    List<Customer> customerAnalysis = customerService.getAllCustomers();
	    for (Customer customer : customerAnalysis) {
	    	System.out.print(customer);
	    }
	    model.addAttribute("customerAnalysis", customerAnalysis);
	    
	    //獲取生長數據
//	    List<GrowthDetail> growthDetails = growthDetailService.getAllGrowthDetails();
//	    model.addAttribute("growthDetails", growthDetails);
	    
	    
	    return "index"; // 返回對應的 Thymeleaf 模板名稱
	}
	
	private LocalDate convertToLocalDateViaSqlDate(java.sql.Date sqlDate) {
	    return sqlDate.toLocalDate();
	}
	
	private LocalDate convertToLocalDateViaUtilDate(java.util.Date utilDate) {
	    return new java.sql.Date(utilDate.getTime()).toLocalDate();
	}

}
