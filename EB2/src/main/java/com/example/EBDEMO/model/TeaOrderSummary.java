package com.example.EBDEMO.model;

public class TeaOrderSummary {
    private Long teaID;
    private Integer totalQuantity;
	public TeaOrderSummary(Long key, Integer value) {
		// TODO Auto-generated constructor stub
		this.teaID = key;
		this.totalQuantity = value;
	}
	public Long getTeaID() {
		return teaID;
	}
	public void setTeaID(Long teaID) {
		this.teaID = teaID;
	}
	public Integer getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

    // 構造函數、Getter 和 Setter 略
    
}
