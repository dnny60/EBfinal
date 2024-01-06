package com.example.EBDEMO.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Tea")
public class Tea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TeaID;
    
    private String TeaName;
    
    private BigDecimal UnitPrice;
    
    private Integer TeaSpan;

    // Getters and Setters
    
    public Long getTeaID() {
        return TeaID;
    }

    public void setTeaID(Long TeaID) {
        this.TeaID = TeaID;
    }

    public String getTeaName() {
        return TeaName;
    }

    public void setTeaName(String TeaName) {
        this.TeaName = TeaName;
    }

    public BigDecimal getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(BigDecimal UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public Integer getTeaSpan() {
        return TeaSpan;
    }

    public void setTeaSpan(Integer TeaSpan) {
        this.TeaSpan = TeaSpan;
    }

    // toString, hashCode, equals, etc.
}
