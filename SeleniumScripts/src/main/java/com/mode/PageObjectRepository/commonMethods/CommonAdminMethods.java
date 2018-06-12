package com.mode.PageObjectRepository.commonMethods;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;

public class CommonAdminMethods extends DriverClass {

	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfview']")
	private WebElement view_btn;

	@FindBy(xpath = "//span[text()='Close']")
	private WebElement close_btn;
	
	
	
	@FindBy(xpath = "//div[@class='datagrid-cell-rownumber' and text()='1']")
	private WebElement select_row;

	@FindBy(xpath = "//span[text()='Home']")
	private WebElement view_cmts;

	public WebElement getView_cmts() {
		return view_cmts;
	}

	public WebElement getSelect_row() {
		return select_row;
	}

	public WebElement getView_btn() {
		return view_btn;
	}

	public WebElement getClose_btn() {
		return close_btn;
	}

	// Common method to View all Records  
	public void viewRecord(String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));
		
		getSelect_row().click();
		getView_btn().click();
		Thread.sleep(1000);
		getClose_btn().click();
		Thread.sleep(2000);
		boolean b;
		try {
			b = getView_cmts().isDisplayed();
			Reporter.log(module_name + " View Record successfully: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[][] data = excel.getdata("TestReports");

		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("View " + module_name) || data[i][1].contains(module_name + " View ")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfsetmpin']")
	private WebElement reset_mpin;

	@FindBy(xpath = "//div[contains(text(),'M-Pin Reset Successful.')]")
	private WebElement resetMpin_msg;

	public WebElement getReset_mpin() {
		return reset_mpin;
	}

	public WebElement getResetMpin_msg() {
		return resetMpin_msg;
	}
	
	// Common method code to reset M-Pin
	public void resetMPin(String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getReset_mpin().click();

		boolean b;
		try {
			b = getResetMpin_msg().isDisplayed();
			Reporter.log(module_name + " Reset M-Pin successful: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[][] data = excel.getdata("TestReports");

		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("M-Pin " + module_name) || data[i][1].contains(module_name + " M-Pin")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

		Thread.sleep(3000);

	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfsettpin']")
	private WebElement reset_tpin;

	public WebElement getReset_tpin() {
		return reset_tpin;
	}

	@FindBy(xpath = "//div[contains(text(),'T-Pin Reset Successful.')]")
	private WebElement resetTpin_msg;

	public WebElement getResetTpin_msg() {
		return resetTpin_msg;
	}
	
	// Common method code to reset T-Pin
	public void resetTPin(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getReset_tpin().click();

		boolean b;
		try {
			b = getResetTpin_msg().isDisplayed();
			Reporter.log(module_name + " Reset T-Pin successful: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Thread.sleep(1000);

		String[][] data = excel.getdata("TestReports");

		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("T-Pin " + module_name) || data[i][1].contains(module_name + " T-Pin")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

		Thread.sleep(2000);
	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfpwd']")
	private WebElement password_btn;

	public WebElement getPassword_btn() {
		return password_btn;
	}

	public WebElement getPassword_msg() {
		return password_msg;
	}

	@FindBy(xpath = "//div[contains(text(),'Password Reset Successful.')]")
	private WebElement password_msg;

	
	// Common method code to reset Password
	public void resetPassword(String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getPassword_btn().click();
		Thread.sleep(1000);
		boolean b;
		try {
			b = getPassword_msg().isDisplayed();
			Reporter.log(module_name + " Reset Password successful: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		String[][] data = excel.getdata("TestReports");

		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Password " + module_name) || data[i][1].contains(module_name + " Password")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

		Thread.sleep(2000);

	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfblock']")
	private WebElement block_btn;

	@FindBy(xpath = "//div[contains(text(),'Blocked Successfully')]")
	private WebElement block_msg;

	public WebElement getBlock_btn() {
		return block_btn;
	}

	public WebElement getBlock_msg() {
		return block_msg;
	}
	
	//Common method to block 
	public void block(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getBlock_btn().click();

		boolean b;
		try {
			b = getBlock_msg().isDisplayed();
			Reporter.log(module_name + " blocked successful: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);

		String[][] data = excel.getdata("TestReports");

		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Block " + module_name) || data[i][1].contains(module_name + " Block")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

		Thread.sleep(1000);
	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfunblock']")
	private WebElement unblock_btn;

	@FindBy(xpath = "//div[contains(text(),'Unblocked Successfully')]")
	private WebElement unblock_msg;

	public WebElement getUnblock_btn() {
		return unblock_btn;
	}

	public WebElement getUnblock_msg() {
		return unblock_msg;
	}

	//Common method code to unblock
	public void unblock(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getUnblock_btn().click();

		boolean b;
		try {
			b = getUnblock_msg().isDisplayed();
			Reporter.log(module_name + " unblocked successful: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);

		String[][] data = excel.getdata("TestReports");

		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Unblock " + module_name) || data[i][1].contains(module_name + " Unblock")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

		Thread.sleep(1000);
	}
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfremove']")
	private WebElement remove;
	
	@FindBy(xpath="//span[text()='Ok']")
	private WebElement ok;
	
	public WebElement getRemove() {
		return remove;
	}

	public WebElement getOk() {
		return ok;
	}
	
	public void deleteRecord(String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));
		
		getSelect_row().click();
		getRemove().click();
		Thread.sleep(500);
		getOk().click();
		Thread.sleep(2000);
	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfreset']")
	private WebElement reset_approval;
	
	@FindBy(xpath="//div[text()='Reset Approval Comments']/..//input[@name='temp_comment']")
	private WebElement reject_cmts;
	
	@FindBy(xpath="//span[text()='Process Reset Approvals']")
	private WebElement reset_submit;
	
	public WebElement getReset_approval() {
		return reset_approval;
	}

	public WebElement getReject_cmts() {
		return reject_cmts;
	}

	public WebElement getReset_submit() {
		return reset_submit;
	}

	public void resetApproval(String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));
		
		getSelect_row().click();
		getReset_approval().click();
		Thread.sleep(500);
		String[][] reset = excel.getdata("Modules");
		getReject_cmts().sendKeys(reset[1][6]);
		getReset_submit().click();
		Thread.sleep(2000);
	}

	
}
