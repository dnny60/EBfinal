package com.example.EBDEMO.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RFM {
	public RFM() {
		List<Custom> customers = new ArrayList<Custom>();
		 
		//read in the data
		 customers.add(new Custom(0, "John Doe", 79, "Tainan", "12345678", "2023/12/23", 1115280));
	     customers.add(new Custom(1, "Jane Smith", 94, "New Taipei City", "23456789", "2023/11/22", 947292));
	     customers.add(new Custom(2, "Alice Johnson", 72, "Taoyuan", "87654321", "2023/12/12", 60034));
	     customers.add(new Custom(3, "Bob Anderson", 35, "Taipei", "23456789", "2023/11/01", 803294));
	     customers.add(new Custom(4, "Emily Brown", 28, "Kaoshiung", "45678901", "2023/10/15", 103930));
	     customers.add(new Custom(5, "David Chen", 45, "Taichung", "67890123", "2023/11/30", 433070));
	     customers.add(new Custom(6, "Grace Wang", 60, "Hsinchu", "78901234", "2023/10/22", 550283));
	     customers.add(new Custom(7, "Michael Liu", 43, "Yilan", "89012345", "2023/10/20", 493848));
	     customers.add(new Custom(8, "Sarah Lin", 50, "Keelung", "90123456", "2023/11/10", 393848));
	     customers.add(new Custom(9, "Kevin Wu", 32, "Chiayi", "12345670", "2023/12/01", 343920));
	     
//	     for (Custom customer : customers) {
//	            int recency = customer.calculateRecency();
//	            int clv = customer.getCLV();
//
//	            // Use the RFM scores for further analysis or segmentation
//	            System.out.println("Customer ID: " + customer.customerId + ", Recency: " + recency + " days, CLV: " + clv);
//	     }
	     
	  // Perform RFM analysis
	        for (Custom customer : customers) {
	            int recencyScore = calculateRecencyScore(customer.recency);
	            int frequencyScore = calculateFrequencyScore(customer.customerId);
	            int monetaryScore = calculateMonetaryScore(customer.clv);

	            // Use the RFM scores for further analysis or segmentation
	            System.out.println("Customer ID: " + customer.customerId +
	                    ", Recency Score: " + recencyScore +
	                    ", Frequency Score: " + frequencyScore +
	                    ", Monetary Score: " + monetaryScore);
	        }
	     
	}
	 private static int calculateRecencyScore(java.util.Date recency) {
	        // Calculate the number of days since the last purchase
	        long daysSinceLastPurchase = (new Date(0).getTime() - recency.getTime()) / (24 * 60 * 60 * 1000);

	        // Define recency scoring rules
	        if (daysSinceLastPurchase <= 30) {
	            return 5;  // High recency score
	        } else if (daysSinceLastPurchase <= 90) {
	            return 3;  // Medium recency score
	        } else {
	            return 1;  // Low recency score
	        }
	    }
	 
	 private static int calculateFrequencyScore(int custID) {
	        // Example: You may assign scores based on the total number of purchases
	        // For simplicity, we use a basic rule here
	        if (custID % 3 == 0) {
	            return 5;  // High frequency score
	        } else if (custID % 3 == 1) {
	            return 3;  // Medium frequency score
	        } else {
	            return 1;  // Low frequency score
	        }
	    }
	 private static int calculateMonetaryScore(int clv) {
	        // Example: You may assign scores based on different CLV ranges
	        // For simplicity, we use a basic rule here
	        if (clv >= 1000000) {
	            return 5;  // High monetary score
	        } else if (clv >= 500000) {
	            return 3;  // Medium monetary score
	        } else {
	            return 1;  // Low monetary score
	        }
	    }
	
     
}