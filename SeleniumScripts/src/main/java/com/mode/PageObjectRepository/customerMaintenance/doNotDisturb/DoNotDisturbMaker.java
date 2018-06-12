package com.mode.PageObjectRepository.customerMaintenance.doNotDisturb;

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

public class DoNotDisturbMaker extends DriverClass{

	
	ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Customer Maintenance']")
	private WebElement cust_maintenance;
	
	@FindBy(xpath="//div[@href='makerdnd.jsp']")
	private WebElement donot_disturb;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getCust_maintenance() {
		return cust_maintenance;
	}

	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingDoNotDisturbMenu() throws InterruptedException{
		
				
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
	
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + cust_maintenance.isDisplayed());
		action.moveToElement(cust_maintenance).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(donot_disturb)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@numberboxname='mobile_number']")
	private WebElement mobile_no;
	
	@FindBy(xpath="//input[@comboname='apply_for_all']/..//span[@class='combo-arrow']")
	private WebElement apply_all;
	
	@FindBy(xpath="//div[@value='Y' and text()='Yes']")
	private WebElement apply_value;
	
	@FindBy(xpath="//input[@numberboxname='from_date']")
	private WebElement from_date;
	
	@FindBy(xpath="//input[@numberboxname='to_date']")
	private WebElement to_date;
	
	@FindBy(xpath="//input[@numberboxname='from_time']")
	private WebElement from_time;
	
	@FindBy(xpath="//input[@numberboxname='to_time']")
	private WebElement to_time;
		
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
		
	public WebElement getMobile_no() {
		return mobile_no;
	}

	public WebElement getApply_all() {
		return apply_all;
	}

	public WebElement getApply_value() {
		return apply_value;
	}

	public WebElement getFrom_date() {
		return from_date;
	}

	public WebElement getTo_date() {
		return to_date;
	}

	public WebElement getFrom_time() {
		return from_time;
	}

	public WebElement getTo_time() {
		return to_time;
	}

	public WebElement getSave() {
		return save;
	}
	
	public void registerDoNotDisturb(String sheet_name, String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);

		getMobile_no().sendKeys(data[1][2]);
		getApply_all().click();
		getApply_value().click();
		getFrom_date().sendKeys(data[1][3]);
		getTo_date().sendKeys(data[1][4]);
		getFrom_time().sendKeys(data[1][5]);
		getTo_time().sendKeys(data[1][6]);

		getSave().click();
		Thread.sleep(500);
		
	}
	
	
}
