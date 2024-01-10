package com.example.EBDEMO.service;

import java.time.LocalDate;
import java.util.Date;

import com.example.EBDEMO.model.PlantingDetail;

public class PlantingDetailDTO {
    private PlantingDetail plantingDetail;
    private Date estimatedHarvestDate;
	public PlantingDetail getPlantingDetail() {
		return plantingDetail;
	}
	public void setPlantingDetail(PlantingDetail plantingDetail) {
		this.plantingDetail = plantingDetail;
	}
	public Date getEstimatedHarvestDate() {
		return estimatedHarvestDate;
	}
	public void setEstimatedHarvestDate(Date estimatedHarvestDate) {
		this.estimatedHarvestDate = estimatedHarvestDate;
	}

    // 构造函数、getters 和 setters
    
    
    
}
