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

public class UserRequestAdmin extends DriverClass {

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath = "//div[@href='adminsysuserrequest.jsp']")
	private WebElement user_req;

	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	

	// Code to select System User Request Menu under admin
	public void selectingSystemUserRequestMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		((JavascriptExecutor) driver).executeScript("scroll(0,200)");
		Actions act = new Actions(driver);
		act.moveToElement(getAdmin_menu()).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(user_req)).click();

		Thread.sleep(500);
		
	}
}
