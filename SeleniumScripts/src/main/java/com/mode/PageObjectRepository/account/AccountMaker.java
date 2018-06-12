package com.mode.PageObjectRepository.account;

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

public class AccountMaker extends DriverClass {

	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath = "//div[@href='makeraccount.jsp']")
	private WebElement acc_menu;

	@FindBy(xpath = "//div[text()='Account ']")
	private WebElement acc_selection_msg;

	public WebElement getAcc_selection_msg() {
		return acc_selection_msg;
	}
	// Selecting Account Under maker menu
	public void selectAccountMenu(String module_name) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
		Actions act = new Actions(driver);
		act.moveToElement(maker_menu).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(acc_menu)).click();
		Thread.sleep(1000);

		boolean b = false;
		try {
			b = getAcc_selection_msg().isDisplayed();
			Reporter.log("Account menu selected: " + b);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		/*String[][] data = excel.getdata("TestReports");
		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Maker " + module_name) || data[i][1].contains(module_name + " Maker ")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}*/

	}
	@FindBy(xpath="//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	@FindBy(xpath="//input[@name='acc_num' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement accNo_text;
	
	@FindBy(xpath="//input[@name='alias' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement alias_text;
	
	@FindBy(xpath="//input[@name='cust_id' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement user_id;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	@FindBy(xpath = "//div[contains(text(),'Data Addition Successful')]")
	private WebElement addition_success_msg;
	
	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public WebElement getAccNo_text() {
		return accNo_text;
	}

	public WebElement getAlias_text() {
		return alias_text;
	}

	public WebElement getUser_id() {
		return user_id;
	}

	public WebElement getSave() {
		return save;
	}

	public WebElement getAddition_success_msg() {
		return addition_success_msg;
	}
	
	//Code to register Account
	public void registerAccount(String sheet_name, String module_name ) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		getAccNo_text().sendKeys(data[1][2]);
		getAlias_text().sendKeys(data[1][3]);
		getUser_id().sendKeys(data[1][4]);
		getSave().click();
		
		Thread.sleep(500);

		boolean b = false;
		try {
			b = getAddition_success_msg().isDisplayed();
			Reporter.log("Account created successfully: " + b);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*String[][] data1 = excel.getdata("TestReports");
		for (int i = 1; i < data1.length; i++) {
			if (data1[i][1].contains("Registration " + module_name)
					|| data1[i][1].contains(module_name + " Registration")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}*/
		Thread.sleep(1000);

	}

		
	
}

