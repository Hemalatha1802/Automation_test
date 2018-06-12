package com.mode.PageObjectRepository.configuration.interestTypeRange;

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

public class InterestTypeMaker extends DriverClass{

	
ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Configuration']")
	private WebElement config;
	
	@FindBy(xpath="//div[@href='makerinterestrates.jsp']")
	private WebElement interest_range;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getConfig() {
		return config;
	}

	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingInterestType() throws InterruptedException{
		
		((JavascriptExecutor) driver).executeScript("scroll(0,200)");
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + config.isDisplayed());
		action.moveToElement(config).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(interest_range)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@comboname='interest_type_id']/..//span[@class='combo-arrow']")
	private WebElement category;
	
	@FindBy(xpath="//div[@value='13' and text()='GOLD']")
	private WebElement category_value;
	
	@FindBy(xpath="//input[@comboname='service_id']/..//span[@class='combo-arrow']")
	private WebElement service;
	
	@FindBy(xpath="//div[@value='25' and text()='Airtime Topup']")
	private WebElement service_value;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='range_from']")
	private WebElement min_amt;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='range_to']")
	private WebElement max_amt;
	
	@FindBy(xpath="//input[@comboname='interest_type_code']/..//span[@class='combo-arrow']")
	private WebElement interest_type;
	
	@FindBy(xpath="//div[@value='Flat' and text()='Flat']")
	private WebElement interest_type_value;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='interest_value']")
	private WebElement interest_value1;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='interest_value2']")
	private WebElement interest_value2;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='interest_value3']")
	private WebElement interest_value3;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getService() {
		return service;
	}

	public WebElement getMin_amt() {
		return min_amt;
	}

	public WebElement getMax_amt() {
		return max_amt;
	}

	public WebElement getCategory_value() {
		return category_value;
	}

	public WebElement getService_value() {
		return service_value;
	}

	
	public WebElement getInterest_range() {
		return interest_range;
	}

	public WebElement getInterest_type() {
		return interest_type;
	}

	public WebElement getInterest_type_value() {
		return interest_type_value;
	}

	public WebElement getInterest_value1() {
		return interest_value1;
	}

	public WebElement getInterest_value2() {
		return interest_value2;
	}

	public WebElement getInterest_value3() {
		return interest_value3;
	}

	public WebElement getCategory() {
		return category;
	}

	
	public WebElement getSave() {
		return save;
	}
	
	public void registerInterestType(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		
		String[][] data = excel.getdata(sheet_name);

		Thread.sleep(500);
		
		getService().click();
		getService_value().click();
		getCategory().click();
		getCategory_value().click();
		getMin_amt().sendKeys(data[1][3]);
		getMax_amt().sendKeys(data[1][4]);
		getInterest_type().click();
		getInterest_type_value().click();
		getInterest_value1().sendKeys(data[1][5]);
		getInterest_value2().sendKeys(data[1][6]);
		getInterest_value3().sendKeys(data[1][7]);
		Thread.sleep(500);
		getSave().click();
		
	}
}
