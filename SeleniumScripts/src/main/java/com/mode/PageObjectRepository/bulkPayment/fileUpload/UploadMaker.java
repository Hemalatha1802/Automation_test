package com.mode.PageObjectRepository.bulkPayment.fileUpload;

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

public class UploadMaker extends DriverClass {

	ExcelLib excel = new ExcelLib();
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath = "//div[@id='maker']//span[text()='Payment']")
	private WebElement payment;

	@FindBy(xpath = "//div[@href='bulkpaymentupload.jsp']")
	private WebElement payment_upload;

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
	
	

	// Code to select File Upload Menu under admin
	public void selectingfileUploadMenu()
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
		wait.until(ExpectedConditions.visibilityOf(payment_upload)).click();
		Thread.sleep(1000);
	}

	

	// File Upload for Bulk Payment

	public void uploadFile() throws AWTException, InterruptedException {

		String filepath = PropertiesClass.paymentfile;

		getBrowse_btn().sendKeys(filepath);
		Thread.sleep(1000);

		getUpload_btn().click();
		Thread.sleep(1000);
	}

	
}
