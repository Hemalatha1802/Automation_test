package com.mode.PageObjectRepository.jointAccount.approver;

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

public class JAApproverAdmin extends DriverClass {

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath="//div[@id='admin']//span[text()='Joint Accounts']")
	private WebElement joint_acc;

	
	@FindBy(xpath = "//div[@href='adminmfjointaccountapproverview.jsp']")
	private WebElement jApprover_menu;

		
	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	

	// Code to select Joint Account Approver Menu under admin
	public void selectingJApproverMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + joint_acc.isDisplayed());
		action.moveToElement(joint_acc).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(jApprover_menu)).click();
		Thread.sleep(1000);
	}
}
