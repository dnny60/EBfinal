package com.example.EBDEMO.Repository;


import org.springframework.data.repository.CrudRepository;

import com.example.EBDEMO.model.Customer;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}