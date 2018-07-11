package com.mode.commonLib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesClass {

	public static String url="";
	public static String filepath="";
	public static String browser="";
	public static String smsfile="";
	public static String emailfile="";
	public static String paymentfile="";
	
	private Properties prop = null;
	// to get the properties from properties file
	public  void prop() throws IOException {

		InputStream is = null;
		try {
			this.prop = new Properties();
			is = this.getClass().getResourceAsStream("/data.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		 url = prop.getProperty("url");
		 filepath = prop.getProperty("filepath");
		 browser = prop.getProperty("browser");
		 smsfile=prop.getProperty("smsfile");
		 emailfile=prop.getProperty("emailfile");
		 paymentfile=prop.getProperty("paymentfile");
	}
	
	public static void main(String[] args) {
		
		try {
			PropertiesClass pro=new PropertiesClass();
			pro.prop();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		
		
	}
	
	

}
