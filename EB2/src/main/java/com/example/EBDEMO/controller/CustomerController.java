package com.example.EBDEMO.controller;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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


import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.EBDEMO.Repository.CustomerRepository;
import com.example.EBDEMO.model.Customer;
import com.example.EBDEMO.model.Tea;
import com.example.EBDEMO.service.CustomerService;

import jakarta.persistence.EntityNotFoundException;

@Controller // This means that this class is a Controller
@RequestMapping(path="/customer") // This means URL's start with /demo (after Application path)
public class CustomerController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private CustomerRepository customerRepository;
  
  @Autowired
  private CustomerService customerService;
  
  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);


  @PostMapping(path="/add") // Map ONLY POST Requests
  public  String addNewUser (@RequestParam String CName
      , @RequestParam String CPhoneNum
      , @RequestParam String Address
      , @RequestParam Long CAge) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Customer n = new Customer();
    n.setcName(CName);
    n.setAddress(Address);
    n.setcPhoneNum(CPhoneNum);
    n.setCAge(CAge);
    customerRepository.save(n);
    return "redirect:/#cust-info";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Customer> getAllUsers() {
    // This returns a JSON or XML with the users
	// This returns a JSON or XML with the users
	    Iterable<Customer> customers = customerRepository.findAll();
	    
	    System.out.print(customers);
	    // 打印所有客户信息到控制台
	    for (Customer customer : customers) {
	        System.out.println(customer);
	    }
//    return customerRepository.findAll();
	    return customers;
  }
  
//Get an Customer by ID
  @GetMapping("/get/{id}")
  public @ResponseBody Customer getTeaById(@PathVariable("id") String id) {
  	System.out.print(id);
      return customerRepository.findById(Long.parseLong(id))
              .orElseThrow(() -> new EntityNotFoundException("inventory not found with id " + id));
  }
  
  
// Update an existing Customer
  @PutMapping("/update/{id}")
  public @ResponseBody Customer updateInventory(@PathVariable Long id
  		, @RequestParam String CName
  		, @RequestParam String CPhoneNum
  		, @RequestParam String CAddressme
  		, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date LastPurchaseDate
  		, @RequestParam BigDecimal CLV
  		, @RequestParam Long CAge) {
  	

      Customer customer = customerRepository.findById(id)
      		.orElseThrow(() -> new EntityNotFoundException("Tea not found with id " + id));
      
      
      // Update the order attributes
      customer.setAddress(CAddressme);
      customer.setcName(CName);
      customer.setcPhoneNum(CPhoneNum);
      customer.setLastPurchaseDate(LastPurchaseDate);
      customer.setCLV(CLV);
      customer.setCAge(CAge);

      return customerRepository.save(customer);
  }
  
  
  
// Delete an Customer
  @DeleteMapping("/delete/{id}")
  public @ResponseBody ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
  	
  	Customer customer = customerRepository.findById(id)
      		.orElseThrow((() -> new EntityNotFoundException("Inventory not found with id " + id)));

      customerRepository.delete(customer);

      return ResponseEntity.ok().build();
  }
  

}