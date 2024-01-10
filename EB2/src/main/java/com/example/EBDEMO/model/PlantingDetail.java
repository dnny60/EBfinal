package com.example.EBDEMO.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "PlantingDetail")
public class PlantingDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PlantingDetailID;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TeaID", referencedColumnName="TeaID")
    private Tea tea;
	
	@Temporal(TemporalType.DATE)
    private Date PlantDate;
	
	private BigDecimal PlantArea;
	
	
	private String firstTenDay;
	
	private String secondTenDay;
	
	private String thridTenDay;
	
	private String fourthTenDay;
	
	private String fifthTenDay;
	
	private String aboveFifttyDay;
	
	private String previousStage;
	
	private String currentStage;
	
	private String nextStage;
	

	public String getPreviousStage() {
		return previousStage;
	}

	public void setPreviousStage(String previousStage) {
		this.previousStage = previousStage;
	}

	public String getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}

	public String getNextStage() {
		return nextStage;
	}

	public void setNextStage(String nextStage) {
		this.nextStage = nextStage;
	}

	public Long getPlantingDetailID() {
		return PlantingDetailID;
	}

	public void setPlantingDetailID(Long plantingDetailID) {
		PlantingDetailID = plantingDetailID;
	}

	public Tea getTea() {
		return tea;
	}

	public void setTea(Tea tea) {
		this.tea = tea;
	}

	public Date getPlantDate() {
		return PlantDate;
	}

	public void setPlantDate(Date plantDate) {
		PlantDate = plantDate;
	}

	public BigDecimal getPlantArea() {
		return PlantArea;
	}

	public void setPlantArea(BigDecimal plantArea) {
		PlantArea = plantArea;
	}

	public String getFirstTenDay() {
		return firstTenDay;
	}

	public void setFirstTenDay(String firstTenDay) {
		this.firstTenDay = firstTenDay;
	}

	public String getSecondTenDay() {
		return secondTenDay;
	}

	public void setSecondTenDay(String secondTenDay) {
		this.secondTenDay = secondTenDay;
	}

	public String getThridTenDay() {
		return thridTenDay;
	}

	public void setThridTenDay(String thridTenDay) {
		this.thridTenDay = thridTenDay;
	}

	public String getFourthTenDay() {
		return fourthTenDay;
	}

	public void setFourthTenDay(String fourthTenDay) {
		this.fourthTenDay = fourthTenDay;
	}

	public String getFifthTenDay() {
		return fifthTenDay;
	}

	public void setFifthTenDay(String fifthTenDay) {
		this.fifthTenDay = fifthTenDay;
	}

	public String getAboveFifttyDay() {
		return aboveFifttyDay;
	}

	public void setAboveFifttyDay(String aboveFifttyDay) {
		this.aboveFifttyDay = aboveFifttyDay;
	}
	
	
	
	
	

}
