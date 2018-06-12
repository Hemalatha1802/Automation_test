package com.mode.PageObjectRepository.systemUser;

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

public class SystemUserMaker extends DriverClass {

	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;

	@FindBy(xpath = "//div[@href='globalmakeruser.jsp']")
	private WebElement user_menu;

	@FindBy(xpath = "//div[text()='User ']")
	private WebElement user_selection_msg;

	public WebElement getUser_selection_msg() {
		return user_selection_msg;
	}

	// Code to select System User Menu under maker
	public void selectUserMenu(String module_name) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Actions act = new Actions(driver);
		act.moveToElement(maker_menu).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(user_menu)).click();
		Thread.sleep(1000);

		boolean b = false;
		try {
			b = getUser_selection_msg().isDisplayed();
			Reporter.log("System User menu selected: " + b);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String[][] data = excel.getdata("TestReports");
		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Maker " + module_name) || data[i][1].contains(module_name + " Maker ")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

	}
	@FindBy(xpath="//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='user_id']")
	private WebElement user_id;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='first_name']")
	private WebElement first_name;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='last_name']")
	private WebElement last_name;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='email']")
	private WebElement email_id;
		
	@FindBy(xpath="//input[@class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid' and @numberboxname='mobile']")
	private WebElement mobile_no;
	
	@FindBy(xpath="(//input[@comboname='institution_id']/..//span[@class='combo-arrow'])[1]")
	private WebElement inst_dropdown;
	
	@FindBy(xpath="(//div[@value='4' and text()='Bank of Bhutan-Bhutan'])[1]")
	private WebElement institution;
	
		
	@FindBy(xpath="(//input[@comboname='user_role_id']/..//span[@class='combo-arrow'])[1]")
	private WebElement role;
	
	@FindBy(xpath="(//div[@value='4' and text()='ADMIN'])[1]")
	private WebElement role_value;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
	@FindBy(xpath = "//div[contains(text(),'Data Addition Successful')]")
	private WebElement addition_success_msg;
	
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	public WebElement getUser_menu() {
		return user_menu;
	}

	public WebElement getUser_id() {
		return user_id;
	}

	public WebElement getFirst_name() {
		return first_name;
	}

	public WebElement getLast_name() {
		return last_name;
	}

	public WebElement getEmail_id() {
		return email_id;
	}

	public WebElement getMobile_no() {
		return mobile_no;
	}

	public WebElement getSave() {
		return save;
	}

	public WebElement getAddition_success_msg() {
		return addition_success_msg;
	}
	
	public WebElement getInst_dropdown() {
		return inst_dropdown;
	}

	public WebElement getInstitution() {
		return institution;
	}

	

	public WebElement getRole() {
		return role;
	}

	public WebElement getRole_value() {
		return role_value;
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}
	
	// Code to register System User Menu under Maker
	public void registerSystemUser(String sheet_name, String module_name ) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		String[][] data = excel.getdata(sheet_name);
		getUser_id().sendKeys(data[1][2]);
		getFirst_name().sendKeys(data[1][3]);
		getLast_name().sendKeys(data[1][4]);
		getEmail_id().sendKeys(data[1][5]);
		getRole().click();
		getRole_value().click();
		getInst_dropdown().click();
		getInstitution().click();
		getMobile_no().sendKeys(data[1][6]);
		
		
		
		getSave().click();
		
		Thread.sleep(500);

		boolean b = false;
		try {
			b = getAddition_success_msg().isDisplayed();
			Reporter.log("System User created successfully: " + b);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[][] data1 = excel.getdata("TestReports");
		for (int i = 1; i < data1.length; i++) {
			if (data1[i][1].contains("Registration " + module_name)
					|| data1[i][1].contains(module_name + " Registration")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}
		Thread.sleep(1000);

	}

	

		
	
}

