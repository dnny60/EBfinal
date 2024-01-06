package com.example.EBDEMO.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long CustomerID;
    
    @Column(name = "CName")
    private String CName;
    
    @Column(name = "CPhoneNum")
    private String CPhoneNum;
    
    @Column(name = "CAddressme")
    private String Address;

    @Column(name = "LastPurchaseDate")
    @Temporal(TemporalType.DATE)
    private Date LastPurchaseDate;
    
    @Column(name = "CLV")
    private BigDecimal CLV;
    
    @Column(name = "CAge")
    private Long CAge;
    
    
    //toString
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + CName + '\'' +
                ", address='" + Address + '\'' +
                ", phoneNum='" + CPhoneNum + '\'' +
                ", lastPurchaseDate=" + LastPurchaseDate +
                ", clv=" + CLV +
                ", age=" + CAge +
                // 其他属性的字符串表示...
                '}';
    }
    

    // Getters and Setters
    // ...
    
    public void setAddress(String address) {
		this.Address = address;
	}
    
    public void setcName(String cName) {
		this.CName = cName;
	}
    
    public void setcPhoneNum(String cPhoneNum) {
		this.CPhoneNum = cPhoneNum;
	}
    
    public void setCustomerID(Long customerID) {
		this.CustomerID = customerID;
	}
    
    public void setLastPurchaseDate(Date lastPurchaseDate) {
		this.LastPurchaseDate = lastPurchaseDate;
	}
    
    public void setCLV(BigDecimal cLV) {
		CLV = cLV;
	}
    
    public void setCAge(Long cAge) {
		CAge = cAge;
	}
    
    public String getAddress() {
		return Address;
	}
    public String getcName() {
		return CName;
	}
    public String getcPhoneNum() {
		return CPhoneNum;
	}
    public Long getCustomerID() {
		return CustomerID;
	}
    public Date getLastPurchaseDate() {
		return LastPurchaseDate;
	}
    
    public BigDecimal getCLV() {
		return CLV;
	}
    
    public Long getCAge() {
		return CAge;
	}
    
}
