package com.mode.PageObjectRepository.partners.airtimeProvider;

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

public class AirtimeProviderChecker extends DriverClass{

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfchecker l-btn-icon-left']")
	private WebElement checker_menu;
	
	@FindBy(xpath="//div[@id='checker']//span[text()='Partners']")
	private WebElement partner;

	@FindBy(xpath = "//div[@href='checkeratpprovider.jsp']")
	private WebElement airtime_provider;
	
	
	public WebElement getChecker_menu() {
		return checker_menu;
	}
	
	

	// Code to select Airtime Provider Menu under Checker
	public void selectingProviderMenu(String module_name) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + checker_menu.isDisplayed());
		action.moveToElement(checker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + partner.isDisplayed());
		action.moveToElement(partner).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(airtime_provider)).click();
		Thread.sleep(1000);
		
			
		

	}
}
