package com.mode.PageObjectRepository.bulkSMS;

import java.awt.AWTException;
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
import com.mode.commonLib.PropertiesClass;

public class SMSUpload extends DriverClass {

	ExcelLib excel = new ExcelLib();
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath = "//div[@id='globaladmin']//span[text()='Bulk SMS']")
	private WebElement bulk_sms;

	@FindBy(xpath = "//div[@href='bulksmsupload.jsp']")
	private WebElement sms_upload;

	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	// Code to select SMS Upload Menu under admin
	public void selectingSMSUploadMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + bulk_sms.isDisplayed());
		action.moveToElement(bulk_sms).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(sms_upload)).click();
		Thread.sleep(1000);
	}

	@FindBy(xpath = "//input[@name='datafile']")
	private WebElement browse_btn;

	@FindBy(xpath = "//input[@value='Upload']")
	private WebElement upload_btn;

	public WebElement getBrowse_btn() {
		return browse_btn;
	}

	public WebElement getUpload_btn() {
		return upload_btn;
	}

	// File Upload for Bulk SMS

	public void uploadFile() throws AWTException, InterruptedException {

		String filepath = PropertiesClass.smsfile;

		getBrowse_btn().sendKeys(filepath);
		Thread.sleep(1000);

		getUpload_btn().click();
		Thread.sleep(1000);
	}

	@FindBy(xpath = "//div[@href='smstocustgrp.jsp']")
	private WebElement customer_group;

	@FindBy(xpath = "//input[@comboname='customerType']/..//span[@class='combo-arrow']")
	private WebElement customer_group_dropdown;

	@FindBy(xpath = "//div[@value='10']")
	private WebElement customer_dropdown_value;

	@FindBy(xpath = "//textarea[@class='easyui-validatebox validatebox-text' and @name='mesgcontent']")
	private WebElement message;

	@FindBy(xpath = "//a[@onclick='submitForm()']//span[text()='Send']")
	private WebElement send_btn;

	public WebElement getSend_btn() {
		return send_btn;
	}

	public WebElement getCustomer_group_dropdown() {
		return customer_group_dropdown;
	}

	public WebElement getCustomer_dropdown_value() {
		return customer_dropdown_value;
	}

	public WebElement getMessage() {
		return message;
	}

	// Code to send SMS to Specific Customer Group 
	public void specificCustomerGroup(String sheet_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + bulk_sms.isDisplayed());
		action.moveToElement(bulk_sms).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customer_group)).click();
		Thread.sleep(1000);

		String[][] data = excel.getdata(sheet_name);
		getCustomer_group_dropdown().click();
		getCustomer_dropdown_value().click();
		getMessage().sendKeys(data[1][2]);
		getSend_btn().click();

	}

	@FindBy(xpath = "//div[@href='smstousergrp.jsp']")
	private WebElement user_group;

	@FindBy(xpath = "//div[@value='4']")
	private WebElement user_dropdown_value;

	public WebElement getUser_dropdown_value() {
		return user_dropdown_value;
	}

	// Code to send SMS to Specific User Group 
	public void specificUserGroup(String sheet_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + bulk_sms.isDisplayed());
		action.moveToElement(bulk_sms).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(user_group)).click();
		Thread.sleep(1000);

		String[][] data = excel.getdata(sheet_name);
		getCustomer_group_dropdown().click();
		getUser_dropdown_value().click();
		getMessage().sendKeys(data[1][3]);
		getSend_btn().click();

	}

	@FindBy(xpath = "//div[@href='smstocaappusergrp.jsp']")
	private WebElement caAppuser_group;

	// Code to send SMS to CA App User Group 
	public void cAAppUserGroup(String sheet_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + bulk_sms.isDisplayed());
		action.moveToElement(bulk_sms).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(caAppuser_group)).click();
		Thread.sleep(1000);

		String[][] data = excel.getdata(sheet_name);
		getMessage().sendKeys(data[1][4]);
		getSend_btn().click();

	}

	@FindBy(xpath = "//div[@href='smstolist.jsp']")
	private WebElement specific_list;

	@FindBy(xpath = "//input[@class='easyui-validatebox validatebox-text' and @name='customerType']")
	private WebElement mobile_list;

	public WebElement getMobile_list() {
		return mobile_list;
	}

	// Code to Send SMS to Specific List 
	public void specificList(String sheet_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + bulk_sms.isDisplayed());
		action.moveToElement(bulk_sms).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(specific_list)).click();
		Thread.sleep(1000);

		String[][] data = excel.getdata(sheet_name);
		getMobile_list().sendKeys(data[1][5]);
		getMessage().sendKeys(data[1][6]);
		getSend_btn().click();

	}
}
