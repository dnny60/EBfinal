package com.example.EBDEMO.Repository;



import org.springframework.data.repository.CrudRepository;

import com.example.EBDEMO.model.Tea;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TeaRepository extends CrudRepository<Tea, Long> {

}