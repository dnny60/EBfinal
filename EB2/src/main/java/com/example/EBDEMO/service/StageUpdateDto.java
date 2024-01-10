package com.example.EBDEMO.service;

public class StageUpdateDto {
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

    // ... 加入 getter 和 setter 方法 ...
    
    
    
}