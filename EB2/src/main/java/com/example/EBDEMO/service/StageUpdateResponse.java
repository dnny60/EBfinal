package com.example.EBDEMO.service;

public class StageUpdateResponse {
    private boolean success;
    private String previousStage;
    private String currentStage;
    private String nextStage;

    // 构造函数、getters 和 setters

    public StageUpdateResponse() {}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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
}
