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

public class MessageTemplate extends DriverClass {

	ExcelLib excel = new ExcelLib();
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath = "//div[@id='globaladmin']//span[text()='Global Configuration']")
	private WebElement global_config;

	@FindBy(xpath = "//div[@href='managemessagetemplates.jsp']")
	private WebElement message_template;

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;

	@FindBy(xpath = "//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='template_code']")
	private WebElement code;

	@FindBy(xpath = "//textarea[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='template']")
	private WebElement template;

	@FindBy(xpath = "//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='template_subj']")
	private WebElement subject;

	@FindBy(xpath = "//input[@comboname='lang_code']/..//span[@class='combo-arrow']")
	private WebElement lang_dropdown;

	@FindBy(xpath = "//div[@value='en_US']")
	private WebElement lang_dropdown_value;

	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;

	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public WebElement getCode() {
		return code;
	}

	public WebElement getTemplate() {
		return template;
	}

	public WebElement getSubject() {
		return subject;
	}

	public WebElement getLang_dropdown() {
		return lang_dropdown;
	}

	public WebElement getLang_dropdown_value() {
		return lang_dropdown_value;
	}

	public WebElement getSave() {
		return save;
	}

	// Code to register message Template
	public void createMessageTemplate(String sheet_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + global_config.isDisplayed());
		action.moveToElement(global_config).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(message_template)).click();
		Thread.sleep(1000);

		String[][] data = excel.getdata(sheet_name);
		getAdd_btn().click();
		getCode().sendKeys(data[1][2]);
		getTemplate().sendKeys(data[1][3]);
		getSubject().sendKeys(data[1][4]);
		getLang_dropdown().click();
		getLang_dropdown_value().click();
		getSave().click();
	}

}
