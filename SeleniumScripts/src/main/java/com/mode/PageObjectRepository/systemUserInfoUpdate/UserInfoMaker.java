package com.mode.PageObjectRepository.systemUserInfoUpdate;

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

public class UserInfoMaker extends DriverClass {

	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath = "//div[@href='makeruserinfoupdate.jsp']")
	private WebElement user_menu;

	@FindBy(xpath = "//div[text()='User ']")
	private WebElement user_selection_msg;

	public WebElement getUser_selection_msg() {
		return user_selection_msg;
	}

	// Code to select User Info Update Menu under maker
	public void selectUserInfoMenu(String module_name) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Actions act = new Actions(driver);
		act.moveToElement(maker_menu).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(user_menu)).click();
		Thread.sleep(1000);


	}
	@FindBy(xpath="//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='orig_user_id']")
	private WebElement user_id;
		
	@FindBy(xpath="//a[@onclick='loadUserID()']")
	private WebElement load_user;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='group_code']")
	private WebElement group_code;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	public WebElement getUser_id() {
		return user_id;
	}

	public WebElement getLoad_user() {
		return load_user;
	}

	public WebElement getGroup_code() {
		return group_code;
	}
		
	
	public WebElement getSave() {
		return save;
	}

		
	public WebElement getAdd_btn() {
		return add_btn;
	}
	
	// Code to update System User Info Menu under Maker
	public void registerSystemUserInfo(String sheet_name, String module_name ) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		getUser_id().sendKeys(data[1][2]);
		getLoad_user().click();
		getGroup_code().sendKeys(data[1][3]);
		getSave().click();
		
		Thread.sleep(500);

		
		
	}
}

