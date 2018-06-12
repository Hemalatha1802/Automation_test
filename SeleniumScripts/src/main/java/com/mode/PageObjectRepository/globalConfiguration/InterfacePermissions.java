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

public class InterfacePermissions extends DriverClass {

	ExcelLib excel = new ExcelLib();
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath = "//div[@id='globaladmin']//span[text()='Global Configuration']")
	private WebElement global_config;

	@FindBy(xpath = "//div[@href='manageinterfacepermissions.jsp']")
	private WebElement permission;

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "(//input[@comboname='intf_code']/..//span[@class='combo-arrow'])[2]")
	private WebElement intf_code;

	@FindBy(xpath = "//div[@value='Admin Console']")
	private WebElement intf_dropdown_value;
	
	@FindBy(xpath = "//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='permitted_ip']")
	private WebElement permitted_ip;

	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getIntf_code() {
		return intf_code;
	}

	public WebElement getIntf_dropdown_value() {
		return intf_dropdown_value;
	}

	public WebElement getPermitted_ip() {
		return permitted_ip;
	}

	
	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

		
	public WebElement getSave() {
		return save;
	}

	// Code to Add Interface permission
	public void addInterfacePermissionRecord(String sheet_name)
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

		String[][] data = excel.getdata(sheet_name);
		getAdd_btn().click();
		getIntf_code().click();
		getIntf_dropdown_value().click();
		getPermitted_ip().sendKeys(data[1][3]);
		
		getSave().click();
		
		Thread.sleep(1000);
	}

	

}
