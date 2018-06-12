package com.mode.PageObjectRepository.partners.atmBranch;

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

public class AtmBranchMaker extends DriverClass{

	
ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Partners']")
	private WebElement partner;
	
	@FindBy(xpath="//div[@href='makeratmbranch.jsp']")
	private WebElement atm_branch;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	
	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingAtmBranch() throws InterruptedException{
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + partner.isDisplayed());
		action.moveToElement(partner).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(atm_branch)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_name']")
	private WebElement name;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_address']")
	private WebElement address;
	
	@FindBy(xpath="//input[@comboname='ab_type']/..//span[@class='combo-arrow']")
	private WebElement type_dropdown;
	
	@FindBy(xpath="//div[@value='Branch']")
	private WebElement type_dropdown_value;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_phone']")
	private WebElement phone_no;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_fax']")
	private WebElement fax;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_hours']")
	private WebElement hours;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_long']")
	private WebElement longitude;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_latt']")
	private WebElement latitude;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_mgr']")
	private WebElement manager;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_city']")
	private WebElement city;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='ab_region']")
	private WebElement region;
	
	@FindBy(xpath="//input[@comboname='lang_code']/..//span[@class='combo-arrow']")
	private WebElement language_dropdown;
	
	@FindBy(xpath="//div[@value='en_US']")
	private WebElement language_dropdown_value;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	
	
	public WebElement getName() {
		return name;
	}


	public WebElement getAddress() {
		return address;
	}


	public WebElement getType_dropdown() {
		return type_dropdown;
	}


	public WebElement getType_dropdown_value() {
		return type_dropdown_value;
	}


	public WebElement getPhone_no() {
		return phone_no;
	}


	public WebElement getFax() {
		return fax;
	}


	public WebElement getHours() {
		return hours;
	}


	public WebElement getLongitude() {
		return longitude;
	}


	public WebElement getLatitude() {
		return latitude;
	}


	public WebElement getManager() {
		return manager;
	}


	public WebElement getCity() {
		return city;
	}


	public WebElement getRegion() {
		return region;
	}


	public WebElement getLanguage_dropdown() {
		return language_dropdown;
	}


	public WebElement getLanguage_dropdown_value() {
		return language_dropdown_value;
	}


	public WebElement getSave() {
		return save;
	}
	
	public void registerAtmBranch(String sheet_name,String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		Thread.sleep(500);
		
		getName().sendKeys(data[1][2]);
		getAddress().sendKeys(data[1][3]);
		getType_dropdown().click();
		getType_dropdown_value().click();
		getPhone_no().sendKeys(data[1][4]);
		getFax().sendKeys(data[1][5]);
		getHours().sendKeys(data[1][6]);
		getLongitude().sendKeys(data[1][7]);
		getLatitude().sendKeys(data[1][8]);
		getManager().sendKeys(data[1][9]);
		getCity().sendKeys(data[1][10]);
		getRegion().sendKeys(data[1][11]);
		getLanguage_dropdown().click();
		getLanguage_dropdown_value().click();
		Thread.sleep(500);
		getSave().click();
		
	}
}
