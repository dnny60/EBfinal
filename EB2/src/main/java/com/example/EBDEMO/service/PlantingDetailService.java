package com.example.EBDEMO.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EBDEMO.model.PlantingDetail;
import com.example.EBDEMO.Repository.PlantingDetailRepository;

@Service
public class PlantingDetailService {

    @Autowired
    private PlantingDetailRepository plantingDetailRepository;

    public List<PlantingDetail> getAllPlantingDetails() {
        return (List<PlantingDetail>) plantingDetailRepository.findAll();
    }

    // 其他必要的业务逻辑
}