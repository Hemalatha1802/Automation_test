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

public class CommonCheckerMethods extends DriverClass {

	ExcelLib excel = new ExcelLib();

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfappact']")

	private WebElement approve_btn;

	@FindBy(xpath = "//div[contains(text(),'Approver(Checker)')]/..//input[@name='temp_comment']")
	private WebElement approve_cmts;

	@FindBy(xpath = "//span[text()='Approve']")
	private WebElement approve_submit;

	@FindBy(xpath = "//div[contains(text(),'Approval Successful')]")
	private WebElement approve_msg;

	@FindBy(xpath = "//div[@class='datagrid-cell-rownumber' and text()='1']")
	private WebElement select_row;

	public WebElement getSelect_row() {
		return select_row;
	}

	public WebElement getApprove_btn() {
		return approve_btn;
	}

	public WebElement getApprove_msg() {
		return approve_msg;
	}

	public WebElement getApprove_cmts() {
		return approve_cmts;
	}

	public WebElement getApprove_submit() {
		return approve_submit;
	}

	// common method to approve record
	public void approve(String sheet_name, String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getApprove_btn().click();

		String[][] approve = excel.getdata("Modules");
		getApprove_cmts().sendKeys(approve[1][4]);

		/*
		 * if (module_name.equalsIgnoreCase("Customer")) { String[][] data =
		 * excel.getdata(sheet_name); getApprove_cmts().sendKeys(data[1][6]); }
		 * else if (module_name.equalsIgnoreCase("Account")) { String[][] data =
		 * excel.getdata(sheet_name); getApprove_cmts().sendKeys(data[1][6]); }
		 * else if (module_name.equalsIgnoreCase("System User")) { String[][]
		 * data = excel.getdata(sheet_name);
		 * getApprove_cmts().sendKeys(data[1][8]); } else if
		 * (module_name.equalsIgnoreCase("Customer Request")) { String[][] data
		 * = excel.getdata(sheet_name); getApprove_cmts().sendKeys(data[1][5]);
		 * } else if (module_name.equalsIgnoreCase("Account Request")) {
		 * String[][] data = excel.getdata(sheet_name);
		 * getApprove_cmts().sendKeys(data[1][5]); } else { String[][] data =
		 * excel.getdata(sheet_name); getApprove_cmts().sendKeys(data[1][6]); }
		 */
		getApprove_submit().click();

		/*
		 * boolean b; try { b = getApprove_msg().isDisplayed();
		 * Reporter.log(module_name+" approved successfully: " + b); }
		 * catch (Exception e) { e.printStackTrace(); } Thread.sleep(1000);
		 * 
		 * String[][] data = excel.getdata("TestReports");
		 * 
		 * for (int i = 1; i < data.length; i++) { if (data[i][1].contains(
		 * "Approve " + module_name) || data[i][1].contains(module_name +
		 * " Approve ")) { if (b = true) { excel.setData("TestReports", "Pass",
		 * i, 2); } else { excel.setData("TestReports", "Fail", i, 2); } }
		 * 
		 * }
		 */

		Thread.sleep(500);

	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfdeny']")
	private WebElement deny_btn;

	@FindBy(xpath = "//div[text()='Rejection Comments']/..//input[@name='temp_comment']")
	private WebElement reject_cmts;

	@FindBy(xpath = "//a[@onclick='denyRecordNow()']//span[text()='Process Rejection']")
	private WebElement deny_submit;

	@FindBy(xpath = "//div[contains(text(),'Denial Successful')]")
	private WebElement deny_msg;

	public WebElement getDeny_btn() {
		return deny_btn;
	}

	public WebElement getReject_cmts() {
		return reject_cmts;
	}

	public WebElement getDeny_submit() {
		return deny_submit;
	}

	public WebElement getDeny_msg() {
		return deny_msg;
	}

	// common method to deny record
	public void deny(String sheet_name, String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getDeny_btn().click();

		String[][] deny = excel.getdata("Modules");
		getReject_cmts().sendKeys(deny[1][5]);

		/*
		 * if (module_name.equalsIgnoreCase("Customer")) { String[][] data =
		 * excel.getdata(sheet_name); getReject_cmts().sendKeys(data[1][7]); }
		 * else if (module_name.equalsIgnoreCase("Account")) { String[][] data =
		 * excel.getdata(sheet_name); getReject_cmts().sendKeys(data[1][7]); }
		 * else if (module_name.equalsIgnoreCase("System User")) { String[][]
		 * data = excel.getdata(sheet_name);
		 * getReject_cmts().sendKeys(data[1][9]);
		 * 
		 * } else if (module_name.equalsIgnoreCase("Customer Request")) {
		 * String[][] data = excel.getdata(sheet_name);
		 * getReject_cmts().sendKeys(data[1][6]); } else if
		 * (module_name.equalsIgnoreCase("Account Request")) { String[][] data =
		 * excel.getdata(sheet_name); getReject_cmts().sendKeys(data[1][6]); }
		 * else { String[][] data = excel.getdata(sheet_name);
		 * getReject_cmts().sendKeys(data[1][7]); }
		 */

		getDeny_submit().click();

		boolean b;
		try {
			b = getDeny_msg().isDisplayed();
			Reporter.log(module_name + " Denied successfully: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);

		String[][] data = excel.getdata("TestReports");

		for (int i = 1; i < data.length; i++) {
			if (data[i][1].contains("Deny " + module_name) || data[i][1].contains(module_name + " Deny ")) {
				if (b = true) {
					excel.setData("TestReports", "Pass", i, 2);
				} else {
					excel.setData("TestReports", "Fail", i, 2);
				}
			}

		}

		Thread.sleep(1000);

	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfapproveall']")
	private WebElement approve_group;
	
	@FindBy(xpath = "//span[text()='Approve Group']")
	private WebElement approve_group_submit;
	
	@FindBy(xpath = "//div[contains(text(),'Approver Group (Checker)')]/..//input[@name='temp_comment']")
	private WebElement approve_group_cmts;

	public WebElement getApprove_group_submit() {
		return approve_group_submit;
	}

	public WebElement getApprove_group() {
		return approve_group;
	}
	
	public WebElement getApprove_group_cmts() {
		return approve_group_cmts;
	}

	// common method to approve Group
	public void approveGroup(String sheet_name, String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getApprove_group().click();

		String[][] approve = excel.getdata("Modules");
		getApprove_group_cmts().sendKeys(approve[1][4]);

		getApprove_group_submit().click();

		Thread.sleep(500);

	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfdenyall']")
	private WebElement deny_group;

	@FindBy(xpath = "//a[@onclick='denyGroupNow()']//span[text()='Process Rejection']")
	private WebElement deny_group_submit;
	
	@FindBy(xpath = "//div[text()='Reject Group Comments']/..//input[@name='temp_comment']")
	private WebElement reject_group_cmts;


	public WebElement getReject_group_cmts() {
		return reject_group_cmts;
	}

	public WebElement getDeny_group() {
		return deny_group;
	}

	public WebElement getDeny_group_submit() {
		return deny_group_submit;
	}
	

	// common method to deny Group
	public void denyGroup(String sheet_name, String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		getSelect_row().click();
		getDeny_group().click();

		String[][] deny = excel.getdata("Modules");
		getReject_group_cmts().sendKeys(deny[1][5]);
		getDeny_group_submit().click();

		Thread.sleep(1000);

	}

}
