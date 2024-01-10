package com.example.EBDEMO.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EBDEMO.model.GrowthDetail;
import com.example.EBDEMO.Repository.GrowthDetailRepository;

@Service
public class GrowthDetailService {

    @Autowired
    private GrowthDetailRepository growthDetailRepository;

    public List<GrowthDetail> getAllGrowthDetails() {
        return (List<GrowthDetail>) growthDetailRepository.findAll();
    }

    // 其他必要的业务逻辑
}