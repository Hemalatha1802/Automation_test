package com.mode.PageObjectRepository.globalConfiguration;

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

public class Currency extends DriverClass {

	ExcelLib excel = new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath = "//div[@id='globaladmin']//span[text()='Global Configuration']")
	private WebElement global_config;

	@FindBy(xpath = "//div[@href='managecurrency.jsp']")
	private WebElement currency;

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='currency_code']")
	private WebElement currency_code;

	@FindBy(xpath = "//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='currency_name']")
	private WebElement currency_name;

	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;

	public WebElement getCurrency_code() {
		return currency_code;
	}

	public WebElement getCurrency_name() {
		return currency_name;
	}

	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public WebElement getSave() {
		return save;
	}

	// Code to register Currency
	public void createCurrency(String sheet_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + global_config.isDisplayed());
		action.moveToElement(global_config).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(currency)).click();
		Thread.sleep(1000);

		String[][] data = excel.getdata(sheet_name);
		getAdd_btn().click();
		getCurrency_code().sendKeys(data[1][2]);
		getCurrency_name().sendKeys(data[1][3]);

		getSave().click();

		Thread.sleep(1000);
	}

}
