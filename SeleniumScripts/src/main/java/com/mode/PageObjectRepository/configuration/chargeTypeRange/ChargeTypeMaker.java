package com.mode.PageObjectRepository.configuration.chargeTypeRange;

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

public class ChargeTypeMaker extends DriverClass{

	
ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Configuration']")
	private WebElement config;
	
	@FindBy(xpath="//div[@href='makerchargetyperange.jsp']")
	private WebElement charge_range;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getConfig() {
		return config;
	}

	public WebElement getCharge_range() {
		return charge_range;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingChargeType() throws InterruptedException{
		
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
			
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + config.isDisplayed());
		action.moveToElement(config).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(charge_range)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@comboname='charge_type_id']/..//span[@class='combo-arrow']")
	private WebElement category;
	
	@FindBy(xpath="//div[@value='13' and text()='GOLD']")
	private WebElement category_value;
	
	@FindBy(xpath="//input[@comboname='service_id']/..//span[@class='combo-arrow']")
	private WebElement service;
	
	@FindBy(xpath="//div[@value='25' and text()='Airtime Topup']")
	private WebElement service_value;
	
	@FindBy(xpath="//input[@comboname='currency_code']/..//span[@class='combo-arrow']")
	private WebElement currency;
	
	@FindBy(xpath="//div[@value='USD' and text()='US Dollars']")
	private WebElement currency_value;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='range_from']")
	private WebElement min_amt;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='range_to']")
	private WebElement max_amt;
	
	@FindBy(xpath="//input[@comboname='charge_type_code']/..//span[@class='combo-arrow']")
	private WebElement charge_type;
	
	@FindBy(xpath="//div[@value='Flat' and text()='Flat']")
	private WebElement charge_type_value;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='charge_value']")
	private WebElement charge_value1;
	
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='charge_value2']")
	private WebElement charge_value2;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getService() {
		return service;
	}

	public WebElement getCurrency() {
		return currency;
	}

	public WebElement getMin_amt() {
		return min_amt;
	}

	public WebElement getMax_amt() {
		return max_amt;
	}

	public WebElement getCharge_type() {
		return charge_type;
	}

	public WebElement getCharge_value1() {
		return charge_value1;
	}
	
	public WebElement getCategory_value() {
		return category_value;
	}

	public WebElement getService_value() {
		return service_value;
	}

	public WebElement getCurrency_value() {
		return currency_value;
	}

	public WebElement getCharge_type_value() {
		return charge_type_value;
	}


	public WebElement getCharge_value2() {
		return charge_value2;
	}

	public WebElement getCategory() {
		return category;
	}

	
	public WebElement getSave() {
		return save;
	}
	
	public void registerChargeType(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		
		String[][] data = excel.getdata(sheet_name);

		Thread.sleep(500);
		

		/*Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + category.isDisplayed());
		action.moveToElement(category).click().build().perform();*/
		
		
		getService().click();
		getService_value().click();
		getCategory().click();
		getCategory_value().click();
		getCurrency().click();
		getCurrency_value().click();
		getMin_amt().sendKeys(data[1][3]);
		getMax_amt().sendKeys(data[1][4]);
		getCharge_type().click();
		getCharge_type_value().click();
		getCharge_value1().sendKeys(data[1][5]);
		getCharge_value2().sendKeys(data[1][6]);
		Thread.sleep(500);
		getSave().click();
		
	}
}
