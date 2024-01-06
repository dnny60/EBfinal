package com.example.EBDEMO.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.EBDEMO.Repository.CustomerRepository; //import CustomerRepository
import com.example.EBDEMO.model.Customer; //import Customer

public class Test {
	
	private CustomerRepository customerRepository; //instantiate
	
	  public Iterable<Customer> getData() {
	    
		
		    Iterable<Customer> customers = customerRepository.findAll(); //從資料庫拿所有Customer資料，然後儲存到customers中。

		    for (Customer customer : customers) {
		        System.out.println(customer.getAddress()); //拿customer的所有Address，getAddress。
		        System.out.println(customer.getCAge()); //如何要Age的話就getcAge()
		        System.out.println(customer.getAddress().toString()); //如果要print到console看的話要加toString();
		    }
//	    return customerRepository.findAll();
		    return customers;
	  }
	  
	  

}
