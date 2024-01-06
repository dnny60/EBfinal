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

    
    private BigDecimal TotalInvValue;
    
    private BigDecimal InvretailPrice;
    
    
    
    public void setInvDate(Date invDate) {
		InvDate = invDate;
	}
    
    public void setInvQuantity(BigDecimal invQuantity) {
        this.InvQuantity = invQuantity;
        calculateTotalInvValue();
    }

    public void setInvretailPrice(BigDecimal invretailPrice) {
        this.InvretailPrice = invretailPrice;
        calculateTotalInvValue();
    }

    public void setInvID(Long invID) {
		InvID = invID;
	}
    public void setTea(Tea tea) {
		this.tea = tea;
	}
    

    public Date getInvDate() {
		return InvDate;
	}
    public BigDecimal getTotalInvValue() {
		return TotalInvValue;
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
    
    public BigDecimal getInvretailPrice() {
		return InvretailPrice;
	}
    
    
    public void calculateTotalInvValue() {
        if (this.InvQuantity != null && this.InvretailPrice != null) {
            this.TotalInvValue = this.InvQuantity.multiply(this.InvretailPrice);
        }
    }


}
