package com.mode.PageObjectRepository.globalConfiguration;

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

public class Permissions extends DriverClass {

	ExcelLib excel = new ExcelLib();
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath = "//div[@id='globaladmin']//span[text()='Global Configuration']")
	private WebElement global_config;

	@FindBy(xpath = "//div[@href='managepermissions.jsp']")
	private WebElement permission;

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "//input[@comboname='role_code']/..//span[@class='combo-arrow']")
	private WebElement role_code;

	@FindBy(xpath = "//div[@value='ADMIN']")
	private WebElement role_dropdown_value;
	
	@FindBy(xpath = "//input[@comboname='feature_code']/..//span[@class='combo-arrow']")
	private WebElement feature_code;

	@FindBy(xpath = "//div[@value='ADMIN Menu Agency Banking']")
	private WebElement feature_dropdown_value;
	
	@FindBy(xpath = "//input[@comboname='block']/..//span[@class='combo-arrow']")
	private WebElement block_dropdown;

	@FindBy(xpath = "//div[@value='Y']")
	private WebElement block_dropdown_value;

	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;

	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public WebElement getRole_code() {
		return role_code;
	}

	public WebElement getRole_dropdown_value() {
		return role_dropdown_value;
	}

	public WebElement getFeature_code() {
		return feature_code;
	}

	public WebElement getFeature_dropdown_value() {
		return feature_dropdown_value;
	}

	public WebElement getBlock_dropdown() {
		return block_dropdown;
	}

	public WebElement getBlock_dropdown_value() {
		return block_dropdown_value;
	}

	
	public WebElement getSave() {
		return save;
	}

	// Code to register permission
	public void createPermissionRecord(String sheet_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + global_config.isDisplayed());
		action.moveToElement(global_config).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(permission)).click();
		Thread.sleep(1000);

		
		getAdd_btn().click();
		getRole_code().click();
		getRole_dropdown_value().click();
		Thread.sleep(500);
		getBlock_dropdown().click();
		getBlock_dropdown_value().click();
		Thread.sleep(500);
		getFeature_code().click();
		getFeature_dropdown_value().click();
		
		getSave().click();
		
		Thread.sleep(1000);
	}

	

}
