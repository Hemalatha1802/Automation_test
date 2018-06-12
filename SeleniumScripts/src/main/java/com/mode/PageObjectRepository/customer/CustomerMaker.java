package com.mode.PageObjectRepository.customer;

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

	@FindBy(xpath = "//div[@href='makercustomer.jsp']")
	private WebElement customer_menu;

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "//input[@name='val_acc' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement accNo;

	@FindBy(xpath = "//span[text()='Search']")
	private WebElement search_btn;

	@FindBy(xpath = "//input[@name='cust_pref_name' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement pref_name;

	@FindBy(xpath = "(//div[input[@class='easyui-combobox combobox-f combo-f' and @comboname='cust_cat']]/span/span/span[@class='combo-arrow'])[1]")
	private WebElement cust_dropdown;

	@FindBy(xpath = "html/body/div[67]/div/div[1]")
	private WebElement general;

	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;

	@FindBy(xpath = "//div[text()='Customer ']")
	private WebElement customer_selection_msg;

	public WebElement getCustomer_selection_msg() {
		return customer_selection_msg;
	}

	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getSave() {
		return save;
	}

	private WebElement getAccNo() {

		return accNo;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public WebElement getSearch_btn() {
		return search_btn;
	}

	public WebElement getPref_name() {
		return pref_name;
	}

	public WebElement getCust_dropdown() {
		return cust_dropdown;
	}

	public WebElement getGeneral() {
		return general;
	}

	// Code to select Customer Menu under Maker
	public void selectingCustomermenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(customer_menu)).click();
		Thread.sleep(1000);

		boolean b = false;
		try {
//			Assert.assertEquals("Select Customer Menu Test Case Pass", getCustomer_selection_msg(), "Customer ");
			b = getCustomer_selection_msg().isDisplayed();
			Reporter.log("Customer menu selected: " + b);

		} catch (Exception e) {

			e.printStackTrace();
		}

		String[][] data = excel.getdata("TestReports");
		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Maker " + module_name) || data[i][1].contains(module_name + " Maker ")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else  {
					excel.setData("TestReports", "Fail", i, 2);
				} 
			}

		}

	}

	@FindBy(xpath = "//div[contains(text(),'Data Addition Successful')]")
	private WebElement addition_success_msg;

	public WebElement getAddition_success_msg() {
		return addition_success_msg;
	}

	//Code to register Customer
	public void registerCustomer(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		getAdd_btn().click();
		new Actions(driver).moveToElement(accNo).build().perform();
		getAccNo().click();
		String[][] data = excel.getdata("CustomerRegistration");

		getAccNo().sendKeys(data[1][2]);

		search_btn.isDisplayed();
		if (search_btn.isEnabled()) {
			new Actions(driver).moveToElement(search_btn).build().perform();
			getSearch_btn().click();
		}

		getPref_name().sendKeys(data[1][3]);

		getCust_dropdown().click();
		getGeneral().click();
		getSave().click();
		Thread.sleep(500);

		boolean b = false;
		try {
			b = getAddition_success_msg().isDisplayed();
			Reporter.log("Customer created successfully: " + b);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[][] data1 = excel.getdata("TestReports");
		for (int i = 1; i < data1.length; i++) {
			if (data1[i][1].contains("Registration " + module_name)
					|| data1[i][1].contains(module_name + " Registration")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else  {
					excel.setData("TestReports", "Fail", i, 2);
				} 
			}

		}
		Thread.sleep(1000);

	}

}
