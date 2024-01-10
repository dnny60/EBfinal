package com.example.EBDEMO.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.EBDEMO.model.Tea;
import com.example.EBDEMO.model.GrowthDetail;
import com.example.EBDEMO.Repository.GrowthDetailRepository;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping(path="/growthDetail")
public class GrowthDetailControlloer {

    @Autowired
    private GrowthDetailRepository growthDetailRepository;
    
//    @PostMapping("/updatePlantingDetail/{id}")
//    public String updatePlantingDetail(@ModelAttribute PlantingDetail plantingDetail) {
//        plantingDetailRepository.save(plantingDetail);
//        return "redirect:#schedule"; // 重新導向回排程頁面
//    }
    
    @PostMapping("/update/{id}")
    public String updatePlantingDetail(@PathVariable Long id
    		, @RequestParam String firstTenDay
    		, @RequestParam String secondTenDay
    		, @RequestParam String thridTenDay
    		, @RequestParam String fourthTenDay
    		, @RequestParam String fifthTenDay
    		, @RequestParam String aboveFifttyDay
    		 ) {
        GrowthDetail growthDetail = growthDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("growthDetail not found with id " + id));
        growthDetail.setFirstTenDay(firstTenDay);
        growthDetail.setSecondTenDay(secondTenDay);
        growthDetail.setThridTenDay(thridTenDay);
        growthDetail.setFourthTenDay(fourthTenDay);
        growthDetail.setFifthTenDay(fifthTenDay);
        growthDetail.setAboveFifttyDay(aboveFifttyDay);
        growthDetailRepository.save(growthDetail);
        return "redirect:/#schedule"; // 重定向到显示所有库存的页面
    }

}
