package com.mode.PageObjectRepository.systemUserRequest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;

public class UserRequestMaker extends DriverClass {

	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath = "//div[@href='makersysuserrequest.jsp']")
	private WebElement user_req;

	
	// Code to select User Request  Menu under maker
	public void selectUserRequestMenu(String module_name) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Actions act = new Actions(driver);
		act.moveToElement(maker_menu).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(user_req)).click();
		Thread.sleep(1000);


	}
	@FindBy(xpath="//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='user_id']")
	private WebElement user_id;
		
	@FindBy(xpath="//a[@onclick='loadUserName()']")
	private WebElement load_user;
	
	@FindBy(xpath="//input[@comboname='request_type']/..//span[@class='combo-arrow']")
	private WebElement req_dropdown;
	
	@FindBy(xpath="//div[@value='Unblock User']")
	private WebElement req_dropdown_value;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getUser_id() {
		return user_id;
	}

	public WebElement getLoad_user() {
		return load_user;
	}

	public WebElement getReq_dropdown() {
		return req_dropdown;
	}

	public WebElement getReq_dropdown_value() {
		return req_dropdown_value;
	}
	
	
	public WebElement getSave() {
		return save;
	}

		
	public WebElement getAdd_btn() {
		return add_btn;
	}
	
	// Code to register System User Request Menu under Maker
	public void registerSystemUserRequest(String sheet_name, String module_name ) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		getUser_id().sendKeys(data[1][2]);
		getLoad_user().click();
		getReq_dropdown().click();
		getReq_dropdown_value().click();
		getSave().click();
		
		Thread.sleep(500);

		
		
	}
}

