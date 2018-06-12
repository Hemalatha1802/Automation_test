package com.mode.PageObjectRepository.bulkPayment.paymentApprovers;

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

public class PaymentApproverMaker extends DriverClass {

	ExcelLib excel = new ExcelLib();
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath = "//div[@id='maker']//span[text()='Payment']")
	private WebElement payment;

	@FindBy(xpath = "//div[@href='makerbulkpaymentapprovers.jsp']")
	private WebElement payment_approver;

	
	// Code to select Payment Approver Menu under admin
	public void selectingPaymentApproverMenu()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + payment.isDisplayed());
		action.moveToElement(payment).perform();
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(payment_approver)).click();
		Thread.sleep(1000);
	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
		
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='acc_num']")
	private WebElement acc_num;
	
	@FindBy(xpath="//a[@onclick='loadMasterApproverDetail()']")
	private WebElement account_details;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='alias']")
	private WebElement alias_name;
	
	@FindBy(xpath="//a[@onclick='loadApproverName()']")
	private WebElement approver_details;
	
	@FindBy(xpath="//input[@comboname='role_code']/..//span[@class='combo-arrow']")
	private WebElement role_dropdown;
	
	@FindBy(xpath="//div[@value='Approver' and text()='Approver']")
	private WebElement approver_cif;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public WebElement getAcc_num() {
		return acc_num;
	}

	public WebElement getAccount_details() {
		return account_details;
	}

	public WebElement getAlias_name() {
		return alias_name;
	}

	public WebElement getApprover_details() {
		return approver_details;
	}
	
	public WebElement getApprover_cif() {
		return approver_cif;
	}

	public WebElement getRole_dropdown() {
		return role_dropdown;
	}

	public WebElement getSave() {
		return save;
	}


public void registerApprover(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		Thread.sleep(500);
		getAcc_num().sendKeys(data[1][2]);
		getAccount_details().click();
		getAlias_name().sendKeys(data[1][3]);
		getApprover_cif().sendKeys(data[1][4]);
		getApprover_details().click();
		Thread.sleep(500);
		getSave().click();
		
	}
	
}
