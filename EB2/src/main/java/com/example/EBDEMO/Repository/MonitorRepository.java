package com.example.EBDEMO.Repository;


import org.springframework.data.repository.CrudRepository;


import com.example.EBDEMO.model.Monitor;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MonitorRepository extends CrudRepository<Monitor, Long> {
	

}