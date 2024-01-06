package com.example.EBDEMO.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Inventory")
public class Inventory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long InvID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TeaID", referencedColumnName="TeaID")
    private Tea tea;

    @Temporal(TemporalType.DATE)
    private Date InvDate;

    private BigDecimal InvQuantity;

    private BigDecimal InvAmount;
    
    private BigDecimal InvPrice;
    
    
    public void setInvAmount(BigDecimal invAmount) {
		InvAmount = invAmount;
	}
    public void setInvDate(Date invDate) {
		InvDate = invDate;
	}
    
    public void setInvPrice(BigDecimal invPrice) {
		InvPrice = invPrice;
	}
    public void setInvQuantity(BigDecimal invQuantity) {
		InvQuantity = invQuantity;
	}
    public void setInvID(Long invID) {
		InvID = invID;
	}
    public void setTea(Tea tea) {
		this.tea = tea;
	}
    
    public BigDecimal getInvAmount() {
		return InvAmount;
	}
    public Date getInvDate() {
		return InvDate;
	}
    public BigDecimal getInvPrice() {
		return InvPrice;
	}
    public BigDecimal getInvQuantity() {
		return InvQuantity;
	}
    public Long getInvID() {
		return InvID;
	}
    public Tea getTea() {
		return tea;
	}
    
    

}
