package com.example.EBDEMO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EBDEMO.Repository.TeaRepository;

import com.example.EBDEMO.model.Tea;


@Service
public class TeaService {

    @Autowired
    private TeaRepository teaRepository;
    
    public List<Tea> getAllTea() {
        return (List<Tea>) teaRepository.findAll();
    }
    // 与 InventoryService 类似的方法
}