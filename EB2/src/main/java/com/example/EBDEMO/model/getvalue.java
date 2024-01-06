package com.example.EBDEMO.model;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getvalue {
	public static JSONObject json_w;
	public static JSONObject json_r;
	public static double dailyHighTemperature;
	public static double dailyLowTemperature;
	public static double  rainfallNow;
	public static String url_w;
	public static String url_r;
	public static String nt_w;
	public static String nt_r;
	public static String nt_u;
	
	public getvalue(String url_w,String url_r,String nt) throws JSONException, IOException {
		this.nt_u =nt;
		
		this.url_w = url_w;
		getWeather(url_w);
		this.url_r = url_r;
		getRain(url_r);
		
//		if(nt_w == nt_u){
//		
//		}
		
	}
	
	public static void getRain(String url) throws JSONException, IOException {
		readapi r = new readapi(url);
		json_r = r.getJson();
		//Extract the "records" object 
		JSONObject records =json_r.getJSONObject("records");
		//Extract the "station" array
		JSONObject station = records.getJSONArray("Station").getJSONObject(0);
		//Extract the "RainfallElement" object
		JSONObject rainfallElement = station.getJSONObject("RainfallElement");
		//Extract the  "Now" object
		JSONObject now = rainfallElement.getJSONObject("Now");
		//Extract the "Precipitation" value under  "Now"
		rainfallNow = now.getDouble("Precipitation");
		System.out.println("Precipitation now:" + rainfallNow);
		
	}
	public double getRainNow() {
		return this.rainfallNow;
	}
	
	public static void getWeather(String url) throws JSONException, IOException {
		readapi w = new readapi(url);
		json_w= w.getJson();
		JSONObject records = json_w.getJSONObject("records");
		JSONArray stationArray = records.getJSONArray("Station");
		for (int i = 0; i < stationArray.length(); i++) {
            JSONObject station = stationArray.getJSONObject(i);
            JSONObject weatherElement = station.getJSONObject("WeatherElement");
            JSONObject dailyExtreme = weatherElement.getJSONObject("DailyExtreme");

            // Extract the "DailyHigh" and "DailyLow" objects
            JSONObject dailyHigh = dailyExtreme.getJSONObject("DailyHigh");
            JSONObject dailyLow = dailyExtreme.getJSONObject("DailyLow");

            // Extract air temperature values from "DailyHigh"
            dailyHighTemperature = dailyHigh.getJSONObject("TemperatureInfo").getDouble("AirTemperature");
            System.out.println("Daily High Air Temperature: " + dailyHighTemperature);

            // Extract air temperature values from "DailyLow"
            dailyLowTemperature = dailyLow.getJSONObject("TemperatureInfo").getDouble("AirTemperature");
            System.out.println("Daily Low Air Temperature: " + dailyLowTemperature);
        }
		
	}
	
	public double getHighTemp() {
		return this.dailyHighTemperature;
	}
	public double getLowTemp() {
		return this.dailyLowTemperature;
	}
}
