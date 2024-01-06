package com.example.EBDEMO.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;



@Entity
@Table(name = "Schedule")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long SchID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TeaID", referencedColumnName="TeaID")
    private Tea tea;

    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String status;
    
    
    
    
    public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    public void setSchID(Long schID) {
		SchID = schID;
	}
    
    public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
    
    public void setStatus(String status) {
		this.status = status;
	}
    
    public void setTea(Tea tea) {
		this.tea = tea;
	}
    
    public Date getEndDate() {
		return endDate;
	}
    
    public Long getSchID() {
		return SchID;
	}
    public Date getStartDate() {
		return startDate;
	}
    public String getStatus() {
		return status;
	}
    public Tea getTea() {
		return tea;
	}
    
    
    
    

}
