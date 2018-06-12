package com.mode.commonLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverClass {

	public static WebDriver driver;
	
	
	
	
	
	//To Launch browser
	public  void launchBrowser() {
		
		
		if (PropertiesClass.browser.equalsIgnoreCase("chrome")) {
					
			/* options.addArguments("--start-maximized");
		    options.addArguments("--disable-web-security");
		    options.addArguments("--no-proxy-server");
		    Map<String, Object> prefs = new HashMap<String, Object>();
		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);
		    options.setExperimentalOption("prefs", prefs);
			 */
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions"); 
			
			System.setProperty("webdriver.chrome.driver", "\\Selenium\\Framework\\SeleniumScripts\\Resources\\chromedriver.exe");
			driver = new ChromeDriver(options);
			} 
		else if (PropertiesClass.browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "\\SeleniumScripts\\Resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} 
		
		
		else {
			driver = new FirefoxDriver();
		}

	}
	
	public void maximizeBrowser(){
		
		driver.manage().window().maximize();
	}
	public  void getUrl()
	{
		driver.get(PropertiesClass.url);
	}
	
	public void closeBrowser(){
		driver.close();
	}
	
	public void quitBrowser(){
		driver.quit();
	}
}
