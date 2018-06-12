package com.mode.PageObjectRepository.configuration.servicePermissions;

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
import com.mode.commonLib.ExcelLib;

public class ServicePermissionsMaker extends DriverClass{

	
ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Configuration']")
	private WebElement config;
	
	@FindBy(xpath="//div[@href='makerservicepermissions.jsp']")
	private WebElement service_permission;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getConfig() {
		return config;
	}

	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingServicePermission() throws InterruptedException{
		
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		
		Reporter.log("Element is displayed: " + config.isDisplayed());
		action.moveToElement(config).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(service_permission)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@comboname='charge_type_id']/..//span[@class='combo-arrow']")
	private WebElement category;
	
	@FindBy(xpath="//div[@value='13' and text()='GOLD']")
	private WebElement category_value;
	
	@FindBy(xpath="//input[@comboname='service_id']/..//span[@class='combo-arrow']")
	private WebElement service;
	
	@FindBy(xpath="//div[@value='25' and text()='Airtime Topup']")
	private WebElement service_value;
	
	
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getService() {
		return service;
	}

	
	public WebElement getCategory_value() {
		return category_value;
	}

	public WebElement getService_value() {
		return service_value;
	}

		
	public WebElement getCategory() {
		return category;
	}

	
	public WebElement getSave() {
		return save;
	}
	
	public void registerServicePermission(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		
		Thread.sleep(500);
		
		getService().click();
		getService_value().click();
		getCategory().click();
		getCategory_value().click();
		Thread.sleep(500);
		getSave().click();
		
	}
}
