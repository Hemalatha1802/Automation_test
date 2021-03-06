package com.mode.PageObjectRepository.partners.biller;

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


public class BillerAdmin extends DriverClass{

	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath="//div[@id='admin']//span[text()='Partners']")
	private WebElement partner;
	
	@FindBy(xpath = "//div[@href='adminbiller.jsp']")
	private WebElement biller;

	
	
	
	// Code to select Biller Menu under admin
	public void selectingBillerMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + partner.isDisplayed());
		action.moveToElement(partner).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(biller)).click();
		Thread.sleep(2000);
	}
}
