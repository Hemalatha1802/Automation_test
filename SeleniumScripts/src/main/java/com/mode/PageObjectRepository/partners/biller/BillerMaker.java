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
import com.mode.commonLib.ExcelLib;

public class BillerMaker extends DriverClass{

	
ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Partners']")
	private WebElement partner;
	
	@FindBy(xpath="//div[@href='makerbiller.jsp']")
	private WebElement biller;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	
	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingBiller() throws InterruptedException{
		
				
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + partner.isDisplayed());
		action.moveToElement(partner).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(biller)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='biller_name']")
	private WebElement biller_name;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='biller_acno']")
	private WebElement biller_acc;
	
	@FindBy(xpath="//input[@comboname='biller_type']/..//span[@class='combo-arrow']")
	private WebElement service_dropdown;
	
	@FindBy(xpath="//div[@value='Regular' and text()='Regular']")
	private WebElement service_value;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getBiller_name() {
		return biller_name;
	}



	public WebElement getBiller_acc() {
		return biller_acc;
	}



	public WebElement getService_dropdown() {
		return service_dropdown;
	}
		
			
	public WebElement getService_value() {
		return service_value;
	}
		
		
	public WebElement getSave() {
		return save;
	}
	
	public void registerBiller(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		Thread.sleep(500);
		
		getBiller_name().sendKeys(data[1][2]);
		getBiller_acc().sendKeys(data[1][3]);
		getService_dropdown().click();
		getService_value().click();
		Thread.sleep(500);
		getSave().click();
		
	}
}
