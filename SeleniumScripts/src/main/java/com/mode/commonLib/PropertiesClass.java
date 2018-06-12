package com.mode.commonLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClass {

	public static String url="";
	public static String filepath="";
	public static String browser="";
	public static String smsfile="";
	public static String emailfile="";
	public static String paymentfile="";
	
	
	// to get the properties from properties file
	public static  void prop() throws IOException {

		File file = new File("\\Selenium\\Framework\\SeleniumScripts\\TestData\\data.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		fileInput.close();

		
		 url = properties.getProperty("url");
		 filepath = properties.getProperty("filepath");
		 browser = properties.getProperty("browser");
		 smsfile=properties.getProperty("smsfile");
		 emailfile=properties.getProperty("emailfile");
		 paymentfile=properties.getProperty("paymentfile");
	}
	
	public static void main(String[] args) {
		
		try {
			PropertiesClass.prop();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		
		
	}
	
	

}
