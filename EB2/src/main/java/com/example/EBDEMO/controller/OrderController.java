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
import com.example.EBDEMO.model.Order;
import com.example.EBDEMO.model.Tea;
import com.example.EBDEMO.Repository.CustomerRepository;
import com.example.EBDEMO.Repository.OrderRepository;
import com.example.EBDEMO.Repository.TeaRepository;


@Controller
@RequestMapping(path="/orders")
public class OrderController {
	
	@Autowired
	private  OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TeaRepository teaRepository;
	
	// Get all orders
	@GetMapping(path="/all")
    public @ResponseBody Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get an order by ID
    @GetMapping("/get/{id}")
    public @ResponseBody Order getOrderById(@PathVariable("id") String id) {
    	System.out.print(id);
        return orderRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
    }

    // Create a new order
    @PostMapping(path = "/add")
    public @ResponseBody String createOrder(@RequestParam Long teaid, @RequestParam Long customerid, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date odate, @RequestParam Integer oquantity, @RequestParam BigDecimal oamount ) {
    	Customer customer = customerRepository.findById(customerid)
    		    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    	Tea tea = teaRepository.findById(teaid).orElseThrow(() -> new EntityNotFoundException("Tea not found"));
    	Order order = new Order();
    	order.setCustomer(customer);
		order.setTea(tea);
		order.setODate(odate);
		order.setOAmount(oamount);
		order.setOQuantity(oquantity);
		orderRepository.save(order);
	
		return "Saved";
    }

    // Update an existing order
    @PutMapping("/update/{id}")
    public @ResponseBody Order updateOrder(@PathVariable Long id, @RequestParam Integer oquantity, @RequestParam Long teaid,  @RequestParam BigDecimal oamount ) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        Tea tea = teaRepository.findById(teaid).orElseThrow(() -> new EntityNotFoundException("Tea not found with id " + id));
        // Update the order attributes
        order.setTea(tea);
        order.setOQuantity(oquantity);
        order.setOAmount(oamount);

        return orderRepository.save(order);
    }

    // Delete an order
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        
        order.setTea(null);
        order.setCustomer(null);
        orderRepository.save(order);
        orderRepository.delete(order);

        return ResponseEntity.ok().build();
    }

}
