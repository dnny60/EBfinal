package com.example.EBDEMO.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;



@Entity
@Table(name = "Monitor")
public class Monitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long MonID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TeaID", referencedColumnName="TeaID")
    private Tea tea;

    @Temporal(TemporalType.DATE)
    private Date MonDate;

    private String status;

    private BigDecimal MonAmount;
    
    
    public BigDecimal getMonAmount() {
		return MonAmount;
	}
    public Date getMonDate() {
		return MonDate;
	}
    public Long getMonID() {
		return MonID;
	}
    public String getStatus() {
		return status;
	}
    public Tea getTea() {
		return tea;
	}
    
    public void setMonAmount(BigDecimal monAmount) {
		MonAmount = monAmount;
	}
    
    public void setMonDate(Date monDate) {
		MonDate = monDate;
	}
    public void setMonID(Long monID) {
		MonID = monID;
	}
    public void setStatus(String status) {
		this.status = status;
	}
    public void setTea(Tea tea) {
		this.tea = tea;
	}
    
    

}
