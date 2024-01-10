package com.example.EBDEMO.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.EBDEMO.Repository.PlantingDetailRepository;
import com.example.EBDEMO.model.Inventory;
import com.example.EBDEMO.model.PlantingDetail;
import com.example.EBDEMO.service.StageUpdateDto;
import com.example.EBDEMO.service.StageUpdateResponse;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping(path="/plantingDetail")
public class PlantingDetailController {

    @Autowired
    private PlantingDetailRepository plantingDetailRepository;
    
//    @PostMapping("/updatePlantingDetail/{id}")
//    public String updatePlantingDetail(@ModelAttribute PlantingDetail plantingDetail) {
//        plantingDetailRepository.save(plantingDetail);
//        return "redirect:#schedule"; // 重新導向回排程頁面
//    }
    
    @PostMapping("/update/{id}")
    public String updatePlantingDetail(@PathVariable Long id, @RequestParam BigDecimal plantArea, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date plantDate) {
        PlantingDetail plantingDetail = plantingDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("plantingDetail not found with id " + id));
        plantingDetail.setPlantArea(plantArea);
        plantingDetail.setPlantDate(plantDate);
        plantingDetailRepository.save(plantingDetail);
        return "redirect:/#schedule"; // 重定向到显示所有库存的页面
    }
    
    @PostMapping("/updateGrowth/{id}")
    public String updateGrowth(@PathVariable Long id
    		, @RequestParam String firstTenDay
    		, @RequestParam String secondTenDay
    		, @RequestParam String thridTenDay
    		, @RequestParam String fourthTenDay
    		, @RequestParam String fifthTenDay
    		, @RequestParam String aboveFifttyDay) {
        PlantingDetail plantingDetail = plantingDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("plantingDetail not found with id " + id));
        plantingDetail.setFirstTenDay(firstTenDay);
        plantingDetail.setSecondTenDay(secondTenDay);
        plantingDetail.setThridTenDay(thridTenDay);
        plantingDetail.setFourthTenDay(fourthTenDay);
        plantingDetail.setFifthTenDay(fifthTenDay);
        plantingDetail.setAboveFifttyDay(aboveFifttyDay);
        plantingDetailRepository.save(plantingDetail);
        return "redirect:/#schedule"; // 重定向到显示所有库存的页面
    }
    
    
//    @PostMapping("/updateStage/{detailId}")
//    public String updatePlantingStage(
//    	@PathVariable Long detailId,
//        @RequestParam String currentStage,
//        @RequestParam String previousStage,
//        @RequestParam String nextStage) {
//
//        PlantingDetail plantingDetail = plantingDetailRepository.findById(detailId)
//            .orElseThrow(() -> new EntityNotFoundException("PlantingDetail not found with id " + detailId));
//
//        plantingDetail.setCurrentStage(currentStage);
//        plantingDetail.setPreviousStage(previousStage);
//        plantingDetail.setNextStage(nextStage);
//
//        plantingDetailRepository.save(plantingDetail);
//
//        return "redirect:/#schedule"; 
//    }
    
    @PostMapping("/updateStage/{detailId}")
    public ResponseEntity<?> updateStage(@PathVariable Long detailId, @RequestBody StageUpdateDto stageUpdateDto) {
        PlantingDetail plantingDetail = plantingDetailRepository.findById(detailId)
                .orElseThrow(() -> new EntityNotFoundException("PlantingDetail not found with id " + detailId));

        plantingDetail.setPreviousStage(stageUpdateDto.getPreviousStage());
        plantingDetail.setCurrentStage(stageUpdateDto.getCurrentStage());
        plantingDetail.setNextStage(stageUpdateDto.getNextStage());

        plantingDetailRepository.save(plantingDetail);
        StageUpdateResponse response = new StageUpdateResponse();
        response.setSuccess(true);
        response.setPreviousStage(plantingDetail.getPreviousStage());
        response.setCurrentStage(plantingDetail.getCurrentStage());
        response.setNextStage(plantingDetail.getNextStage());
        System.out.print(response);

        // Redirect to the /#schedule URL
        return ResponseEntity.ok(response);
    }


    
    

}
