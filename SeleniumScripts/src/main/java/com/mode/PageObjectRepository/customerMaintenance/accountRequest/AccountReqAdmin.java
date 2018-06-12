package com.mode.PageObjectRepository.customerMaintenance.accountRequest;

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

public class AccountReqAdmin extends DriverClass {

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath="//div[@id='admin']//span[text()='Customer Maintenance']")
	private WebElement cust_maintenance;
	
	@FindBy(xpath = "//div[@href='adminaccrequest.jsp']")
	private WebElement accReq_menu;

	
	public WebElement getCust_maintenance() {
		return cust_maintenance;
	}
	
	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	public WebElement getAccReq_menu() {
		return accReq_menu;
	}

	// Code to select Account Request Menu under admin
	public void selectingCustomerMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + cust_maintenance.isDisplayed());
		action.moveToElement(cust_maintenance).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(accReq_menu)).click();
		Thread.sleep(1000);
	}
}
