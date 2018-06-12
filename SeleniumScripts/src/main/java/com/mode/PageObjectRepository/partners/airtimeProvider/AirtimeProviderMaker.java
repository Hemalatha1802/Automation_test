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
import com.mode.commonLib.ExcelLib;

public class AirtimeProviderMaker extends DriverClass{

	
ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Partners']")
	private WebElement partner;
	
	@FindBy(xpath="//div[@href='makeratpprovider.jsp']")
	private WebElement airtime_provider;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	
	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingAirtimeProvider() throws InterruptedException{
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + partner.isDisplayed());
		action.moveToElement(partner).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(airtime_provider)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='atp_name']")
	private WebElement provider_name;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='atp_acno']")
	private WebElement provider_acc;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	
	public WebElement getProvider_name() {
		return provider_name;
	}



	public WebElement getProvider_acc() {
		return provider_acc;
	}



	public WebElement getSave() {
		return save;
	}
	
	public void registerProvider(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		Thread.sleep(500);
		
		getProvider_name().sendKeys(data[1][2]);
		getProvider_acc().sendKeys(data[1][3]);
		Thread.sleep(500);
		getSave().click();
		
	}
}
