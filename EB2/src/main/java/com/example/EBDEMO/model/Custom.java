package com.example.EBDEMO.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Custom {

	public int customerId;
	public String custName;
	public int age;
	public String address;
	public String phone;
	public Date recency;
	public int clv;
	
	public Custom(int customerId, String custName,int age, String address, String phone, String recency, int clv){
		this.customerId = customerId;
		this.custName = custName;
		this.age = age;
		this.address =address;
		this.phone =  phone;
		try {
            this.recency = new SimpleDateFormat("yyyy/MM/dd").parse(recency);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		this.clv = clv;
	} 
	public int getID() {
		return this.customerId;
	}
	public int getAge() {
		return this.age;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhone() {
		return this.phone;
	}
	public Date getLastDate() {
		return this.recency;
	}
	public int getCLV() {
		return this.clv;
	}
	public int calculateRecency() {
        Date currentDate = new Date(0);
        long diffInMillies = Math.abs(currentDate.getTime() - recency.getTime());
        return (int) (diffInMillies / (24 * 60 * 60 * 1000));
    }
	
}
