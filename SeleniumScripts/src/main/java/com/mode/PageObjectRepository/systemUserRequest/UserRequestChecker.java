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

public class UserRequestChecker extends DriverClass {

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfchecker l-btn-icon-left']")
	private WebElement checker_menu;

	@FindBy(xpath = "//div[@href='checkersysuserrequest.jsp']")
	private WebElement user_req;

	public WebElement getChecker_menu() {
		return checker_menu;
	}

	

	// Code to select System User Request Menu under Checker
	public void selectingUserRequestMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checker_menu);
		Thread.sleep(500);
		Actions act = new Actions(driver);
		act.moveToElement(getChecker_menu()).perform();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(user_req)).click();

		Thread.sleep(500);

	}

}
