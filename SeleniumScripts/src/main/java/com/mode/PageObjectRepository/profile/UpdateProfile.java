package com.mode.PageObjectRepository.profile;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.WebDriverCommon;

public class UpdateProfile extends DriverClass {

	ExcelLib excel = new ExcelLib();
	WebDriverCommon wc = new WebDriverCommon();

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfprofile l-btn-icon-left']")
	private WebElement profile_menu;

	@FindBy(xpath = "//div[@href='updateprofile.jsp']")
	private WebElement update_profile;

	@FindBy(xpath = "//input[@name='tfFirstname']")
	private WebElement first_name;

	@FindBy(xpath = "//input[@name='tfLastname']")
	private WebElement last_name;

	@FindBy(xpath = "//td[text()='Preferred Contact Mode']/..//span[@class='combo-arrow']")
	private WebElement contact_dropdown;

	@FindBy(xpath = "//div[@value='SMS']")
	private WebElement preferred_mode;

	@FindBy(xpath = "//td[text()='Nationality']/..//span[@class='combo-arrow']")
	private WebElement nationality_dropdown;

	@FindBy(xpath = "//div[@value='1']")
	private WebElement nationality;

	@FindBy(xpath = "//td[text()='Preferred Language']/..//span[@class='combo-arrow']")
	private WebElement language_dropdown;

	@FindBy(xpath = "//div[@value='45']")
	private WebElement preferred_language;

	@FindBy(xpath = "//a[@onclick='submitForm()']//span[text()='Update Profile']")
	private WebElement submit_btn;

	public WebElement getProfile_menu() {
		return profile_menu;
	}

	public WebElement getUpdate_profile() {
		return update_profile;
	}

	public WebElement getFirst_name() {
		return first_name;
	}

	public WebElement getLast_name() {
		return last_name;
	}

	public WebElement getContact_dropdown() {
		return contact_dropdown;
	}

	public WebElement getPreferred_mode() {
		return preferred_mode;
	}

	public WebElement getNationality_dropdown() {
		return nationality_dropdown;
	}

	public WebElement getNationality() {
		return nationality;
	}

	public WebElement getLanguage_dropdown() {
		return language_dropdown;
	}

	public WebElement getPreferred_language() {
		return preferred_language;
	}

	public WebElement getSubmit_btn() {
		return submit_btn;
	}

	// Code to update profile
	public void profileUpdate(String sheet_name, String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		String[][] data = excel.getdata(sheet_name);
		Actions act = new Actions(driver);
		act.moveToElement(profile_menu).perform();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(update_profile)).click();
		
		//getUpdate_profile().click();
		new Actions(driver).moveToElement(first_name).click().build().perform();
		getFirst_name().clear();
		getFirst_name().sendKeys(data[1][2]);
		getLast_name().clear();
		getLast_name().sendKeys(data[1][3]);
		Thread.sleep(500);
		getContact_dropdown().click();
		getPreferred_mode().click();
		getNationality_dropdown().click();
		getNationality().click();
		getLanguage_dropdown().click();
		getPreferred_language().click();
		getSubmit_btn().click();
		Thread.sleep(500);

		boolean b = false;
		try {
			b = wc.isTextPresent("Profile updated successfully. Please login again ");
			Reporter.log("Profile Updated: " + b);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[][] data1 = excel.getdata("TestReports");
		for (int i = 1; i < data1.length; i++) {
			if (data1[i][1].contains("Update " + module_name) || data1[i][1].contains(module_name + " Update")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}
		Thread.sleep(1000);

	}

	@FindBy(xpath = "//div[@href='updateprofilesq.jsp']")
	private WebElement update_questions;

	@FindBy(xpath = "//input[@name='tfSeqa1']")
	private WebElement answer1;

	@FindBy(xpath = "//input[@name='tfSeqa2']")
	private WebElement answer2;

	@FindBy(xpath = "//input[@name='tfSeqa3']")
	private WebElement answer3;

	@FindBy(xpath = "//a[@onclick='submitForm()']//span[text()='Update Profile']")
	private WebElement questions_submit;

	public WebElement getUpdate_questions() {
		return update_questions;
	}

	public WebElement getAnswer1() {
		return answer1;
	}

	public WebElement getAnswer2() {
		return answer2;
	}

	public WebElement getAnswer3() {
		return answer3;
	}

	public WebElement getQuestions_submit() {
		return questions_submit;
	}

	//code to update Security Questions
	public void updateSecurityQuestions(String sheet_name, String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		String[][] data = excel.getdata(sheet_name);
		Actions act = new Actions(driver);
		act.moveToElement(profile_menu).perform();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(update_questions)).click();
		
		new Actions(driver).moveToElement(answer1).click().build().perform();
		getAnswer1().sendKeys(data[1][2]);
		getAnswer2().sendKeys(data[1][3]);
		getAnswer3().sendKeys(data[1][4]);
		getQuestions_submit().click();

		boolean b = false;
		try {
			b = wc.isTextPresent("Profile updated successfully. Please login again ");
			Reporter.log("Security Questions Updated: " + b);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[][] data1 = excel.getdata("TestReports");
		for (int i = 1; i < data1.length; i++) {
			if (data1[i][1].contains("Security Questions " + module_name)
					|| data1[i][1].contains(module_name + " Security Questions")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}
		Thread.sleep(1000);

	}
	
	@FindBy(xpath = "//div[@href='changepwd.jsp']")
	private WebElement change_password;
	
	@FindBy(xpath = "//input[@name='tfCurrentPassword']")
	private WebElement current_pwd;

	@FindBy(xpath = "//input[@name='tfUserpassword']")
	private WebElement new_pwd;

	@FindBy(xpath = "//input[@name='tfConfirmationPassword']")
	private WebElement confirmNew_pwd;

	@FindBy(xpath = "//a[@onclick='submitForm()']//span[text()='Reset Password']")
	private WebElement password_submit;
	
	@FindBy(xpath="//b[text()='Password Changed Successfully']")
	private WebElement password_text;
	
	public WebElement getChange_password() {
		return change_password;
	}

	public WebElement getPassword_text() {
		return password_text;
	}

	public WebElement getCurrent_pwd() {
		return current_pwd;
	}

	public WebElement getNew_pwd() {
		return new_pwd;
	}

	public WebElement getConfirmNew_pwd() {
		return confirmNew_pwd;
	}

	public WebElement getPassword_submit() {
		return password_submit;
	}

	//Code to change Password	
	public void changePassword(String sheet_name, String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		String[][] data = excel.getdata(sheet_name);
		Actions act = new Actions(driver);
		act.moveToElement(profile_menu).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(change_password)).click();
		
		getCurrent_pwd().sendKeys(data[1][2]);
		getNew_pwd().sendKeys(data[1][3]);
		getConfirmNew_pwd().sendKeys(data[1][4]);
		getPassword_submit().click();
		
		String newPwd=data[1][3];
		Reporter.log("New Password is-----------------"+newPwd);
		
		boolean b = false;
		try {
			b = getPassword_text().isDisplayed();
			Reporter.log("Change Password pass: " + b);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if(b=true){
			excel.setData("ValidDetails", newPwd, 3, 3);
			excel.setData(sheet_name, newPwd, 1, 2);
		}
		String[][] data1 = excel.getdata("TestReports");
		for (int i = 1; i < data1.length; i++) {
			if (data1[i][1].contains("Change Passwords " + module_name)
					|| data1[i][1].contains(module_name + " Change Password")) {
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
