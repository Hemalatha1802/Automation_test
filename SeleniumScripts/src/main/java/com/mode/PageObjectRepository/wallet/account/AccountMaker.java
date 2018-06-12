package com.mode.PageObjectRepository.wallet.account;

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

public class AccountMaker extends DriverClass {
	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath="//div[@id='maker']//span[text()='Wallet']")
	private WebElement wallet;
	
	@FindBy(xpath = "//div[@href='makercbaccount.jsp']")
	private WebElement account_menu;

	public WebElement getMaker_menu() {
		return maker_menu;
	}

	
	// Code to select Account Menu under Wallet
	public void selectingAccountmenu()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + wallet.isDisplayed());
		action.moveToElement(wallet).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(account_menu)).click();
		Thread.sleep(1000);

		
	}
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "//input[@name='acc_num' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement acc_no;

	@FindBy(xpath = "//input[@name='cust_id' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement cif;

	@FindBy(xpath = "//input[@comboname='currency']/..//span[@class='combo-arrow']")
	private WebElement currency_dropdown;
	
	@FindBy(xpath = "//div[@value='USD' and text()='US Dollars']")
	private WebElement currency_value;
	
	@FindBy(xpath = "//input[@comboname='acctype']/..//span[@class='combo-arrow']")
	private WebElement acctype_dropdown;
	
	@FindBy(xpath = "//div[@value='Wallet' and text()='Wallet']")
	private WebElement acctype_value;

	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getSave() {
		return save;
	}

	
	public WebElement getCif() {
		return cif;
	}


	public WebElement getAdd_btn() {
		return add_btn;
	}
	
	public WebElement getAcc_no() {
		return acc_no;
	}


	public WebElement getCurrency_dropdown() {
		return currency_dropdown;
	}


	public WebElement getCurrency_value() {
		return currency_value;
	}


	public WebElement getAcctype_dropdown() {
		return acctype_dropdown;
	}


	public WebElement getAcctype_value() {
		return acctype_value;
	}
	
	//Code to register Wallet Account
	public void registerAccount(String sheet_name, String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		getAdd_btn().click();
		
		String[][] data = excel.getdata(sheet_name);
		
		getAcc_no().sendKeys(data[1][3]);
		getCif().sendKeys(data[1][2]);
		getCurrency_dropdown().click();
		getCurrency_value().click();
		getAcctype_dropdown().click();
		getAcctype_value().click();
		getSave().click();
		Thread.sleep(500);

	}


	

}
