package com.mode.PageObjectRepository.customerMaintenance.customerRequest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.mode.commonLib.DriverClass;

public class CustomerReqChecker extends DriverClass{

	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfchecker l-btn-icon-left']")
	private WebElement checker_menu;
	
	@FindBy(xpath="//div[@id='checker']//span[text()='Customer Maintenance']")
	private WebElement cust_maintenance;

	@FindBy(xpath = "//div[@href='checkercustrequest.jsp']")
	private WebElement customerreq_menu;
	
	
	public WebElement getChecker_menu() {
		return checker_menu;
	}

	public WebElement getCustomerreq_menu() {
		return customerreq_menu;
	}
	
	public WebElement getCust_maintenance() {
		return cust_maintenance;
	}

	// Code to select Customer Request Menu under Checker
	public void selectingCustomerMenu(String module_name) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + checker_menu.isDisplayed());
		action.moveToElement(checker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + cust_maintenance.isDisplayed());
		action.moveToElement(cust_maintenance).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customerreq_menu)).click();
		Thread.sleep(1000);
		
			
		

	}
	
	

}
