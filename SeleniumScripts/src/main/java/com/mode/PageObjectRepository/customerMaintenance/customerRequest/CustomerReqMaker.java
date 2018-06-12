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
import com.mode.commonLib.ExcelLib;

public class CustomerReqMaker extends DriverClass{

	
	ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Customer Maintenance']")
	private WebElement cust_maintenance;
	
	@FindBy(xpath="//div[@href='makercustrequest.jsp']")
	private WebElement cust_request;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getCust_maintenance() {
		return cust_maintenance;
	}

	public WebElement getCust_request() {
		return cust_request;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingCustReq() throws InterruptedException{
		
				
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		
		Reporter.log("Element is displayed: " + cust_maintenance.isDisplayed());
		action.moveToElement(cust_maintenance).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(cust_request)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@numberboxname='mobile']")
	private WebElement fetch_cif;
	
	
	@FindBy(xpath="//a[@onclick='loadCIFByMobile()']")
	private WebElement load_cif;
	
	@FindBy(xpath="//a[@onclick='loadCIFCustomerName()']")
	private WebElement load_custName;
	
	@FindBy(xpath="//input[@comboname='request_type']/..//span[@class='combo-arrow']")
	private WebElement request_dropdown;
	
	@FindBy(xpath="//div[@value='Unblock CIF' and text()='Unblock Customer Completely M-PIN']")
	private WebElement req_dropdown_value;
	
	@FindBy(xpath="//input[@name='comments' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement cmts;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getFetch_cif() {
		return fetch_cif;
	}

	public WebElement getLoad_cif() {
		return load_cif;
	}

	public WebElement getLoad_custName() {
		return load_custName;
	}

	public WebElement getRequest_dropdown() {
		return request_dropdown;
	}

	public WebElement getReq_dropdown_value() {
		return req_dropdown_value;
	}

	public WebElement getCmts() {
		return cmts;
	}

	public WebElement getSave() {
		return save;
	}
	
	public void registerCustReq(String sheet_name, String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		new Actions(driver).moveToElement(fetch_cif).build().perform();
		getFetch_cif().click();
		String[][] data = excel.getdata(sheet_name);

		getFetch_cif().sendKeys(data[1][2]);

		load_cif.isDisplayed();
		if (load_cif.isEnabled()) {
			new Actions(driver).moveToElement(load_cif).build().perform();
			getLoad_cif().click();
		}
		Thread.sleep(1000);
		load_custName.isDisplayed();
		if (load_custName.isEnabled()) {
			new Actions(driver).moveToElement(load_custName).build().perform();
			getLoad_custName().click();
		}
		
		
		getRequest_dropdown().click();
		getReq_dropdown_value().click();
		getCmts().sendKeys(data[1][3]);
		getSave().click();
		
	}
	
	
}
