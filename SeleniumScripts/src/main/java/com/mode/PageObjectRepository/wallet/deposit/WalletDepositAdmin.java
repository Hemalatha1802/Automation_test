package com.mode.PageObjectRepository.wallet.deposit;

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

public class WalletDepositAdmin extends DriverClass {

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;
	

	@FindBy(xpath="//div[@id='admin']//span[text()='Wallet']")
	private WebElement wallet;


	@FindBy(xpath = "//div[@href='admindeposit.jsp']")
	private WebElement deposit_menu;

	

	public WebElement getDeposit_menu() {
		return deposit_menu;
	}



	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	

	// Code to select Deposit Menu under Wallet
	public void selectingDepositMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + admin_menu.isDisplayed());
		action.moveToElement(admin_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + wallet.isDisplayed());
		action.moveToElement(wallet).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getDeposit_menu())).click();

		Thread.sleep(500);
		
		
	}
}
