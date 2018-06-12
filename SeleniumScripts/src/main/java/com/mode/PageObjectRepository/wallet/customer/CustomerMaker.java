package com.mode.PageObjectRepository.wallet.customer;

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

public class CustomerMaker extends DriverClass {
	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath="//div[@id='maker']//span[text()='Wallet']")
	private WebElement wallet;
	
	@FindBy(xpath = "//div[@href='makercbcustomer.jsp']")
	private WebElement customer_menu;

	public WebElement getMaker_menu() {
		return maker_menu;
	}

	
	// Code to select Customer Menu under Wallet
	public void selectingCustomermenu()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + wallet.isDisplayed());
		action.moveToElement(wallet).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customer_menu)).click();
		Thread.sleep(1000);

		
	}
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "//input[@numberboxname='cif' and @class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid']")
	private WebElement cif;

	@FindBy(xpath = "//input[@name='cname' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement name;

	@FindBy(xpath = "//input[@numberboxname='mobile' and @class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid']")
	private WebElement mobile;
	
	@FindBy(xpath = "//input[@name='email' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement email;

	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getSave() {
		return save;
	}

	
	public WebElement getCif() {
		return cif;
	}


	public WebElement getName() {
		return name;
	}


	public WebElement getMobile() {
		return mobile;
	}


	public WebElement getEmail() {
		return email;
	}


	public WebElement getAdd_btn() {
		return add_btn;
	}
	
	//Code to register Wallet Customer
	public void registerCustomer(String sheet_name, String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		getAdd_btn().click();
		
		String[][] data = excel.getdata(sheet_name);

		getCif().sendKeys(data[1][2]);
		getName().sendKeys(data[1][3]);
		getMobile().sendKeys(data[1][4]);
		getEmail().sendKeys(data[1][5]);
		getSave().click();
		Thread.sleep(500);

	}

}
