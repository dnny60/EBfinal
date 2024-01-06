package com.example.EBDEMO.model;

import java.io.IOException;
import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SimpleLinearRegression;
import java.util.ArrayList;
import weka.*;

import java.time.format.DateTimeFormatter; 
import java.time.LocalDateTime;  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
	private static String lastExecutionDate = "";//store the last execution date
	private static String url_w="https://opendata.cwb.gov.tw/api/v1/rest/datastore/O-A0001-001?Authorization=CWA-AC8D00CA-0BC7-4602-A20E-6923D961AD59&format=JSON&StationId=C0M770&StationName=%E5%98%89%E7%BE%A9%E6%A2%85%E5%B1%B1";
	private static String url_r="https://opendata.cwb.gov.tw/api/v1/rest/datastore/O-A0002-001?Authorization=CWA-AC8D00CA-0BC7-4602-A20E-6923D961AD59&format=JSON&StationId=C0M770&StationName=%E5%98%89%E7%BE%A9%E6%A2%85%E5%B1%B1";
	public static double dailyHighTemperature;
	public static double dailyLowTemperature;
	public static double  rainfallNow;
	public static getvalue v;
	public static MSalesPred msp;
	private static String nt;
	
	public static Connection conn;
	public static Statement stat;
	public static ArrayList<String> re = new ArrayList<String>();
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub	
		
		//get current time in the format "yyyy/MM/dd"
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		 LocalDateTime now = LocalDateTime.now();
		 nt = dtf.format(now);
//		 System.out.print(nt);
		 
		try {
        	String connectionURL = "jdbc:mysql://database-1.ctndd1pdlrpx.ap-northeast-1.rds.amazonaws.com/EBdatabase"; 
        	Connection connection=null;
 	       
//        	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            // Create a connection object
        	connection = DriverManager.getConnection(connectionURL, "admin", "adminadmin");
        	if(!connection.isClosed()) {
        		System.out.println("Connected to the database!");
        		conn=connection;
        		stat=conn.createStatement();
 	    	 
 	    	 String query;
 	    	 query ="SELECT * FROM customers;";
 	    	 boolean success;
 	    	 success  = stat.execute(query);
 	    	 if(success) {
 	    		 ResultSet result = stat.getResultSet();
 	    		 re=showResultSet(result);
 	    		 System.out.print(re);
 	    		 result.close();
 	    		 connection.close();
 	    	 }
 	      }
        } catch (SQLException e) {
          // Handle the connection error
          System.out.println("Connection failed! Error: " + e.getMessage());
      
      }
		
//**	//make the function below just can run one time per day
//		 if (shouldRunToday()) {
//			v = new getvalue(url_w,url_r,nt);
//			dailyHighTemperature = v.getHighTemp();
//			dailyLowTemperature = v.getLowTemp();
//			rainfallNow = v.getRainNow(); 
//		//青心烏龍生長條件:
//		 	//當氣溫高於35度則須注意，高於40度則死亡;當溫度低於10度則須注意，低於5度則死亡
//		 	//當雨量高於20則須注意，高於30則死亡	
//				//for 青心烏龍
//				if (dailyHighTemperature > 40 || dailyLowTemperature < 5 || rainfallNow>30) {
//					//planet die
//				}else if (40>=dailyHighTemperature && dailyHighTemperature>35 || 10 > dailyLowTemperature &&dailyLowTemperature >= 5) {
//					//be aware
//				}else if(rainfallNow>20 && 30>=rainfallNow){
//					//be aware
//				}else {
//					//planet still growth
//					//add prediction if still have time
//				}
//		//文山包種生長條件:
// 			//當氣溫高於30度則須注意，高於35度則死亡;當溫度低於10度則須注意，低於5度則死亡
// 			//當雨量高於30則須注意，高於50則死亡
//				//for 文山包種
//				if (dailyHighTemperature > 35 || dailyLowTemperature < 5 || rainfallNow>50) {
//					//planet die
//				}else if (35>=dailyHighTemperature && dailyHighTemperature>30 || 10 > dailyLowTemperature &&dailyLowTemperature >= 5) {
//					//be aware
//				}else if(rainfallNow>30 && 50>=rainfallNow){
//					//be aware
//				}else {
//					//planet still growth
//				//add prediction if still have time
//				}
//		//東方美人茶生長條件:
//			//當氣溫高於30度則須注意，高於35度則死亡;當溫度低於15度則須注意，低於10度則死亡
//			//當雨量高於30則須注意，高於50則死亡
//			//for 文山包種
//				if (dailyHighTemperature > 35 || dailyLowTemperature < 10 || rainfallNow>50) {
//					//planet die
//				}else if (35>=dailyHighTemperature && dailyHighTemperature>30 || 15 > dailyLowTemperature &&dailyLowTemperature >= 10) {
//					//be aware
//				}else if(rainfallNow>30 && 50>=rainfallNow){
//					//be aware
//				}else {
//					//planet still growth
//					//add prediction if still have time
//				}
//			 updateLastExecutionDate();
//		 }else {
//			 System.out.println("Function already ran today.");
//		 }
			 
//**
		// Input data
	    ArrayList<Double> month = new ArrayList<Double>();  
	    ArrayList<Double> sales = new ArrayList<Double>();
	    
	    for(int i = 1; i < 13;i++) {
	    	month.add((double)i);
	    }
	    //data is the data receive from the database
//	    for(int j = 0;j<data.size();j++) {
//	    	sales.add((double)data.get(j));
//	    }
	    sales.add((double)41000);
	    sales.add((double)54600);
	    sales.add((double)39200);
	    sales.add((double)40030);
	    sales.add((double)47090);
	    sales.add((double)50200);
	    sales.add((double)60300);
	    sales.add((double)70020);
	    sales.add((double)64030);
	    sales.add((double)50020);
	    sales.add((double)60200);
	    sales.add((double)60030);
	    
	    // use the history month sales data to predict the future sales
	    msp=new MSalesPred(month,sales);
	    msp.getPred();
	  

//		v = new getvalue(url_w,url_r,nt);
//		dailyHighTemperature = v.getHighTemp();
//		dailyLowTemperature = v.getLowTemp();
//		rainfallNow = v.getRainNow();
//		System.out.println("rain:"+rainfallNow);
//		
//		//for 青心烏龍
//		if (dailyHighTemperature > 40) {
//			//planet die
//		}
//		else if (dailyLowTemperature < 5) {
//			//planet die
//		}
//		else {
//			//planet still growth
//			//add prediction if still have time
//		}
	}
	
	
	
	//check and ensure function run one time per day
	private static boolean shouldRunToday() {
		String currentDate = getCurrentDate();
		return !currentDate.equals(lastExecutionDate);
	}
	private static void updateLastExecutionDate() {
        lastExecutionDate = getCurrentDate();
    }
	private static String getCurrentDate() {
		 LocalDateTime now = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		 return now.format(formatter);
	}	 
	
	public static ArrayList showResultSet(ResultSet result)throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> cid =new ArrayList<String>();
		ResultSetMetaData metadata = result.getMetaData();
		int columnCount = metadata.getColumnCount();
		for(int i = 1;i<=columnCount;i++) {
			System.out.printf("%15s",metadata.getColumnLabel(i));
		}
		System.out.println();
		
		while(result.next()) {
			for(int j = 1;j<=columnCount;j++) {
				cid.add(result.getString(j));
				System.out.printf("%15s",result.getString(j));
			}
			System.out.println();
		}
		return cid;
	}
}
	


