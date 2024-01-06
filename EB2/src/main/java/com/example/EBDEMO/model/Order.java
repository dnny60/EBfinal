package com.example.EBDEMO.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.example.EBDEMO.model.Customer;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TeaID", referencedColumnName="TeaID")
    private Tea tea;

    @Temporal(TemporalType.DATE)
    private Date ODate;

    private Integer OQuantity;

    private BigDecimal OAmount;

    // Getters and Setters

    // ...
    
    public Customer getCustomer() {
		return customer;
	}
    
    public BigDecimal getOAmount() {
		return OAmount;
	}
    
    public Date getODate() {
		return ODate;
	}
    
    public Integer getOQuantity() {
		return OQuantity;
	}
    
    public Long getOrderID() {
		return OrderID;
	}
    
    public Tea getTea() {
		return tea;
	}
    
    public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    public void setOAmount(BigDecimal oAmount) {
		OAmount = oAmount;
	}
    
    public void setODate(Date oDate) {
		ODate = oDate;
	}
    
    public void setOQuantity(Integer oQuantity) {
		OQuantity = oQuantity;
	}
    
    public void setOrderID(Long orderID) {
		OrderID = orderID;
	}
    
    public void setTea(Tea tea) {
		this.tea = tea;
	}
}
