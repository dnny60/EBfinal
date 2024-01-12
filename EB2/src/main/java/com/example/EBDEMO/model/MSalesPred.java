package com.example.EBDEMO.model;

import java.io.IOException;
import java.text.DecimalFormat;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SimpleLinearRegression;
import java.util.ArrayList;
import weka.*;

public class MSalesPred {
	private static ArrayList<Double> month;
	private static ArrayList<Double> sales;
	private static ArrayList<Double> pred = new ArrayList<Double>();
	
	
	
	public MSalesPred(ArrayList<Double> month, ArrayList<Double> sales) throws Exception {
		
		this.month = month;
		this.sales = sales;
		Predict();
	}
	
	
	public ArrayList<Double> getMonth() {
		return this.month;
	}
	public ArrayList<Double> getsales() {
		return this.sales;
	}
	public ArrayList<Double> getPred() {
		return this.pred;
	}
	public void Predict() throws Exception {
		this.pred.clear();
		this.pred.add((double) 0);
		// Create input attribute 
	    Attribute inputAttr = new Attribute("month"); 
	    // Create output attribute
	    Attribute outputAttr = new Attribute("sales");
	    
	    // Create Instances object
        ArrayList<Attribute> attributeList = new ArrayList<>();
        attributeList.add(inputAttr);
        attributeList.add(outputAttr);
        
        Instances data = new Instances("SalesData", attributeList, 2);
        // Set class index to 1 for sales
	    data.setClassIndex(1);
	    
	    // Add data instances
	    for(int i=0; i<month.size(); i++){
	      DenseInstance inst = new DenseInstance(2);
	      inst.setValue(0, month.get(i));
	      inst.setValue(1, sales.get(i));
	      data.add(inst);
	    }
	    
	    // Build regression model
	    SimpleLinearRegression model = new SimpleLinearRegression();
	    model.buildClassifier(data);
	    
////    // Make predictions:
////    double[] predictions = new double[3];
////    predictions[0] = model.classifyInstance(new DenseInstance(1, new double[] {13}));
////    predictions[1] = model.classifyInstance(new DenseInstance(1, new double[] {14}));
////    predictions[2] = model.classifyInstance(new DenseInstance(1, new double[] {15}));
	    
	    // Make predictions:
	    System.out.println("Monthly sales predictions:");
	    DecimalFormat df = new DecimalFormat("0");
	    
	    for (int inputValue = 2; inputValue <= (month.size()+3); inputValue++) {
	      double prediction = model.classifyInstance(new DenseInstance(2, new double[] {inputValue}));
	     
	      String formattedPrediction = df.format(prediction);
	      System.out.println("For input value " + inputValue + ": " + formattedPrediction);
	      this.pred.add(prediction);
	    }
	}
    
    
    
    
    
    
   
}
