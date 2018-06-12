package com.mode.PageObjectRepository.wallet.deposit;

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

public class WalletDepositMaker extends DriverClass {
	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath="//div[@id='maker']//span[text()='Wallet']")
	private WebElement wallet;
	
	@FindBy(xpath = "//div[@href='makerdeposit.jsp']")
	private WebElement deposit_menu;

	public WebElement getMaker_menu() {
		return maker_menu;
	}

	
	// Code to select Deposit Menu under Wallet
	public void selectingDepositmenu()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + wallet.isDisplayed());
		action.moveToElement(wallet).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(deposit_menu)).click();
		Thread.sleep(1000);

		
	}
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "//input[@name='acc_num' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement acc_no;

	@FindBy(xpath = "//a[@onclick='loadCustomerName()']")
	private WebElement load_custName;
	
	@FindBy(xpath = "//input[@name='remarks' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement remarks;

	@FindBy(xpath = "//input[@numberboxname='amount' and @class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid']")
	private WebElement amount;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;

	public WebElement getDeposit_menu() {
		return deposit_menu;
	}


	public WebElement getLoad_custName() {
		return load_custName;
	}


	public WebElement getRemarks() {
		return remarks;
	}


	public WebElement getAmount() {
		return amount;
	}

		
	public WebElement getSave() {
		return save;
	}

	
	public WebElement getAdd_btn() {
		return add_btn;
	}
	
	public WebElement getAcc_no() {
		return acc_no;
	}


		
	//Code to Deposit Wallet Amount
	public void DepositWalletAmount(String sheet_name, String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		getAdd_btn().click();
		
		String[][] data = excel.getdata(sheet_name);
		
		getAcc_no().sendKeys(data[1][2]);
		
		load_custName.isDisplayed();
		if (load_custName.isEnabled()) {
			new Actions(driver).moveToElement(load_custName).build().perform();
			getLoad_custName().click();
		Thread.sleep(500);	
		getRemarks().sendKeys(data[1][3]);
		getAmount().sendKeys(data[1][4]);
		getSave().click();
		Thread.sleep(500);

	}

	}
	

}
