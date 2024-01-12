package com.example.EBDEMO.service;

import java.util.List;

public class SalesDataResponse {
    private List<Double> months;
    private List<Double> sales;
    private List<Double> predictions;
	public List<Double> getMonths() {
		return months;
	}
	public void setMonths(List<Double> months) {
		this.months = months;
	}
	public List<Double> getSales() {
		return sales;
	}
	public void setSales(List<Double> sales) {
		this.sales = sales;
	}
	public List<Double> getPredictions() {
		return predictions;
	}
	public void setPredictions(List<Double> predictions) {
		this.predictions = predictions;
	}

    // 构造函数、getter 和 setter 略
    
    
}
