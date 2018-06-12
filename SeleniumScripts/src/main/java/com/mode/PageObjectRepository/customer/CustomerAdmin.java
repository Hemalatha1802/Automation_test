package com.mode.PageObjectRepository.customer;

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

public class CustomerAdmin extends DriverClass {

	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmanage l-btn-icon-left']")
	private WebElement admin_menu;

	@FindBy(xpath = "//div[@href='admincustomer.jsp']")
	private WebElement cust_menu;

	@FindBy(xpath = "//div[text()='Customer ']")
	private WebElement customer_selection_msg;

	public WebElement getCustomer_selection_msg() {
		return customer_selection_msg;
	}

	public WebElement getAdmin_menu() {
		return admin_menu;
	}

	public WebElement getCust_menu() {
		return cust_menu;
	}

	// Code to select Customer Menu under admin
	public void selectingCustomerMenu(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		ExcelLib excel = new ExcelLib();
		
		Actions act = new Actions(driver);
		act.moveToElement(getAdmin_menu()).perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin_menu);
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getCust_menu())).click();

		Thread.sleep(500);
		boolean b = false;
		try {
			b = getCustomer_selection_msg().isDisplayed();
			Reporter.log("Customer menu selected: " + b);

		} catch (Exception e) {

			e.printStackTrace();
		}
		String[][] data = excel.getdata("TestReports");
		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Admin " + module_name) || data[i][1].contains(module_name + " Admin ")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}
	}
}
