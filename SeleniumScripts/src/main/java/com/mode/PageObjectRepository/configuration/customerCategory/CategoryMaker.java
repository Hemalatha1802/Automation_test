package com.mode.PageObjectRepository.configuration.customerCategory;

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

public class CategoryMaker extends DriverClass{

	
ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Configuration']")
	private WebElement config;
	
	@FindBy(xpath="//div[@href='makercustomercat.jsp']")
	private WebElement cust_cat;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getConfig() {
		return config;
	}

	public WebElement getCust_cat() {
		return cust_cat;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingCustCategory() throws InterruptedException{
		
			
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		
		Reporter.log("Element is displayed: " + config.isDisplayed());
		action.moveToElement(config).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(cust_cat)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='category']")
	private WebElement category;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='per_day_limit']")
	private WebElement perDay_limit;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='per_tran_limit']")
	private WebElement perTran_limit;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='min_tran_limit']")
	private WebElement minTran_limit;
	
	public WebElement getCategory() {
		return category;
	}

	public WebElement getPerDay_limit() {
		return perDay_limit;
	}

	public WebElement getPerTran_limit() {
		return perTran_limit;
	}

	public WebElement getMinTran_limit() {
		return minTran_limit;
	}


	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	
	

	public WebElement getSave() {
		return save;
	}
	
	public void registerCustCategory(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		
		String[][] data = excel.getdata(sheet_name);

		Thread.sleep(500);
				
		getCategory().sendKeys(data[1][2]);
		getPerDay_limit().sendKeys(data[1][3]);
		getPerTran_limit().sendKeys(data[1][4]);
		getMinTran_limit().sendKeys(data[1][5]);
		Thread.sleep(500);
		getSave().click();
		
	}
}
