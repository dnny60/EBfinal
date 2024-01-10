package com.example.EBDEMO.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "GrowthDetail")
public class GrowthDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long GrowthDetailID;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TeaID", referencedColumnName="TeaID")
    private Tea tea;
	
	private String firstTenDay;
	
	private String secondTenDay;
	
	private String thridTenDay;
	
	private String fourthTenDay;
	
	private String fifthTenDay;
	
	private String aboveFifttyDay;

	public Long getGrowthDetailID() {
		return GrowthDetailID;
	}

	public void setGrowthDetailID(Long growthDetailID) {
		GrowthDetailID = growthDetailID;
	}

	public Tea getTea() {
		return tea;
	}

	public void setTea(Tea tea) {
		this.tea = tea;
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
