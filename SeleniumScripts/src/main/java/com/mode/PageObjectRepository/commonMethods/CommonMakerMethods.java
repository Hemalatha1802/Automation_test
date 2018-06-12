package com.mode.PageObjectRepository.commonMethods;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

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

public class CommonMakerMethods extends DriverClass {

	ExcelLib excel = new ExcelLib();
	@FindBy(xpath = "//a[@class='searchbox-menu m-btn l-btn l-btn-plain']")
	private WebElement search_dropdown;

	@FindBy(xpath = "//span[text()='Mobile']/span[@class='m-btn-downarrow']")
	private WebElement search_mobile_dropdown;

	@FindBy(xpath = "//div[@name='cif']/div[text()='User ID']")
	private WebElement search_cif;

	@FindBy(xpath = "//input[@class='searchbox-text searchbox-prompt']")
	private WebElement search_text;

	@FindBy(xpath = "//span[@class='searchbox-button']")
	private WebElement searchbtn;

	@FindBy(xpath = "//div[@class='datagrid-cell-rownumber' and text()='1']")
	private WebElement select_row;

	@FindBy(xpath = "//div[@name='acc_num']/div[text()='Account Number']")
	private WebElement search_accNo;

	@FindBy(xpath = "//div[@name='user_id']/div[text()='User ID']")
	private WebElement search_userId;

	@FindBy(xpath = "//div[@name='cmobile']/div[text()='Mobile']")
	private WebElement search_mobile;

	@FindBy(xpath = "//div[@name='accnum']/div[text()='Account']")
	private WebElement search_accReqAcc;

	@FindBy(xpath = "//div[@name='category']/div[text()='Category']")
	private WebElement search_category;
	
	@FindBy(xpath = "//div[@name='charge_type']/div[text()='Category']")
	private WebElement search_charge;
	
	@FindBy(xpath="//div[@name='biller_acno']/div[text()='Account No']")
	private WebElement biller_acc;
	
	@FindBy(xpath="//div[@name='atp_acno']/div[text()='Account Number']")
	private WebElement provider_acc;
	
	@FindBy(xpath="//div[@name='cif']/div[text()='CIF']")
	private WebElement wallet_cif;
	
	@FindBy(xpath="//div[@name='cust_id']/div[text()='CIF']")
	private WebElement walletAcc_cif;
	
	@FindBy(xpath="//div[@name='acc_num']/div[text()='To Account']")
	private WebElement wallet_toAcc;
	
	@FindBy(xpath="//div[@name='acc_num']/div[text()='From Account']")
	private WebElement wallet_fromAcc;
	
	@FindBy(xpath="//div[@name='mobile_number']/div[text()='Mobile Number']")
	private WebElement mobile_no;
	
	@FindBy(xpath="//div[@name='acc_no']/div[text()='Account Number']")
	private WebElement joint_acc;
	
	@FindBy(xpath="//div[@name='acc_num']/div[text()='Account']")
	private WebElement joint_acc_approver;
	
	@FindBy(xpath="//div[@name='ab_name']/div[text()='Name']")
	private WebElement atm_name;
	
	@FindBy(xpath="//div[@name='toacc']/div[text()='To']")
	private WebElement to_acc;
	
	@FindBy(xpath="//div[@name='template_code']/div[text()='Code']")
	private WebElement temp_code;
	
	@FindBy(xpath="//div[@name='feature_code']/div[text()='Feature Code']")
	private WebElement feature_code;
	
	@FindBy(xpath="//div[@name='intf_code']/div[text()='Interface']")
	private WebElement interface_name;
	
	@FindBy(xpath="//div[@name='currency_code']/div[text()='Currencycode']")
	private WebElement currency_code;
	
	public WebElement getCurrency_code() {
		return currency_code;
	}

	public WebElement getInterface_name() {
		return interface_name;
	}

	public WebElement getTemp_code() {
		return temp_code;
	}

	public WebElement getFeature_code() {
		return feature_code;
	}

	public WebElement getJoint_acc_approver() {
		return joint_acc_approver;
	}

	public WebElement getAtm_name() {
		return atm_name;
	}

	public WebElement getJoint_acc() {
		return joint_acc;
	}

	public WebElement getMobile_no() {
		return mobile_no;
	}

	public WebElement getWalletAcc_cif() {
		return walletAcc_cif;
	}

	public WebElement getWallet_toAcc() {
		return wallet_toAcc;
	}

	public WebElement getWallet_fromAcc() {
		return wallet_fromAcc;
	}

	public WebElement getWallet_cif() {
		return wallet_cif;
	}

	public WebElement getSearch_charge() {
		return search_charge;
	}

	public WebElement getProvider_acc() {
		return provider_acc;
	}

	public WebElement getBiller_acc() {
		return biller_acc;
	}

	public WebElement getSearch_accReqAcc() {
		return search_accReqAcc;
	}

	public WebElement getSearch_category() {
		return search_category;
	}

	public WebElement getSearch_mobile() {
		return search_mobile;
	}

	public WebElement getSearch_accNo() {
		return search_accNo;
	}

	public WebElement getSearch_userId() {
		return search_userId;
	}

	public WebElement getSearch_dropdown() {
		return search_dropdown;
	}

	public WebElement getSearch_cif() {
		return search_cif;
	}

	public WebElement getSearch_text() {
		return search_text;
	}

	public WebElement getSearchbtn() {
		return searchbtn;
	}

	public WebElement getTo_acc() {
		return to_acc;
	}
	public WebElement getSelect_row() {
		return select_row;
	}

	// common method to search record
	public void search(String sheet_name, String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] module_data = excel.getdata("Modules");
		
		boolean d = false;
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		search_dropdown.isDisplayed();
		new Actions(driver).moveToElement(search_dropdown).build().perform();
		Thread.sleep(1000);

		if (module_name.equalsIgnoreCase(module_data[4][2])) {
			
			getSearch_cif().click();
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][4]);
		} else if (module_name.equalsIgnoreCase(module_data[5][2])) {
			
			getSearch_accNo().click();
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		} else if (module_name.equalsIgnoreCase(module_data[6][2])) {
			
			getSearch_userId().click();
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		} else if (module_name.equalsIgnoreCase(module_data[10][2])) {

			getSearch_mobile().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		} else if (module_name.equalsIgnoreCase(module_data[11][2])) {

			getSearch_accReqAcc().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}

		else if (module_name.equalsIgnoreCase(module_data[12][2])) {

			getSearch_category().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[13][2])) {

			getSearch_charge().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[14][2])) {

			getSearch_charge().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[15][2])) {

			getSearch_charge().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[16][2])) {

			getBiller_acc().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][3]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[17][2])) {

			getProvider_acc().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][3]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[18][2])) {

			getWallet_cif().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[19][2])) {

			getWalletAcc_cif().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[20][2])) {

			getWallet_toAcc().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[21][2])) {

			getWallet_fromAcc().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[22][2])) {

			getWallet_cif().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][3]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[23][2])) {

			getMobile_no().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[24][2])) {

			getJoint_acc().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[25][2])) {

			getJoint_acc_approver().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[26][2])) {

			getAtm_name().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[27][2])) {

			getSearch_userId().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[30][2])) {

			getTo_acc().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[31][2])) {

			getSearch_userId().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[32][2])) {

			getTemp_code().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[33][2])) {

			getFeature_code().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[34][2])) {

			getInterface_name().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		else if (module_name.equalsIgnoreCase(module_data[35][2])) {

			getCurrency_code().click();
			Thread.sleep(500);
			String[][] data = excel.getdata(sheet_name);
			getSearch_text().sendKeys(data[1][2]);
		}
		
		getSearchbtn().click();
		Thread.sleep(1000);

		try {	
			d = getSelect_row().isDisplayed();
			Reporter.log("Search " + module_name + " :" + d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// Thread.sleep(1000);

		/*
		 * String[][] data = excel.getdata("TestReports");
		 * 
		 * for (int i = 1; i < data.length; i++) {
		 * 
		 * if (data[i][1].contains("Search " + module_name) ||
		 * data[i][1].contains(module_name + " Search")) { if (d = true) {
		 * excel.setData("TestReports", "Pass", i, 2); } else {
		 * excel.setData("TestReports", "Fail", i, 2); } break; } }
		 */

		Thread.sleep(1000);
	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfedit']")
	private WebElement edit_btn;

	@FindBy(xpath = "//div[text()='Edit']/../..//span[text()='Save']")
	private WebElement edit_save;

	@FindBy(xpath = "//div[.='Data Updation Successful']")
	private WebElement edit_success_msg;

	public WebElement getEdit_success_msg() {
		return edit_success_msg;
	}

	public WebElement getEdit_btn() {
		return edit_btn;
	}

	public WebElement getEdit_save() {
		return edit_save;
	}

	// Common method to edit record
	public void edit(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		boolean d;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(select_row));

		select_row.click();
		getEdit_btn().click();
		getEdit_save().click();
		Thread.sleep(1000);
		try {
			d = getEdit_success_msg().isDisplayed();
			Reporter.log(module_name + " Updated Successfully: " + d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);

		/*
		 * String[][] data = excel.getdata("TestReports"); for (int i = 1; i <
		 * data.length; i++) {
		 * 
		 * if (data[i][1].contains("Edit " + module_name) ||
		 * data[i][1].contains(module_name + " Edit")) { if (d = true) {
		 * excel.setData("TestReports", "Pass", i, 2); } else {
		 * excel.setData("TestReports", "Fail", i, 2); } break; } }
		 */
		Thread.sleep(1000);
	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfaudit']")
	private WebElement audit_btn;

	@FindBy(xpath = "//b[contains(text(),'Maker Submitted on:')]")
	private WebElement audit_success_msg;

	public WebElement getAudit_btn() {
		return audit_btn;
	}

	public WebElement getAudit_success_msg() {
		return audit_success_msg;
	}

	// Common method to view Audit
	public void audit(String module_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		//boolean d;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(select_row));

		getSelect_row().click();
		getAudit_btn().click();
		Thread.sleep(500);
		/*try {
			d = getAudit_success_msg().isDisplayed();
			Reporter.log(module_name + " AuditLog Test Case Pass: " + d);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		/*
		 * String[][] data = excel.getdata("TestReports"); for (int i = 1; i <
		 * data.length; i++) {
		 * 
		 * if (data[i][1].contains("Audit " + module_name) ||
		 * data[i][1].contains(module_name + " Audit")) { if (d = true) {
		 * excel.setData("TestReports", "Pass", i, 2); } else {
		 * excel.setData("TestReports", "Fail", i, 2); }
		 * 
		 * break; } }
		 */

		Thread.sleep(1000);
	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfhistory']")
	private WebElement approval_btn;

	public WebElement getApproval_btn() {
		return approval_btn;
	}

	// Common method to view Approval History
	public void approvalHistory(String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(select_row));

		getSelect_row().click();
		getApproval_btn().click();

		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		String Parent_id = it.next();
		Reporter.log(Parent_id);
		String child_id = it.next();
		Reporter.log(child_id);
		Thread.sleep(1000);
		driver.switchTo().window(child_id);
		Thread.sleep(1000);
		driver.switchTo().window(Parent_id);
		/*
		 * String[][] data = excel.getdata("TestReports"); for (int i = 1; i <
		 * data.length; i++) {
		 * 
		 * if (data[i][1].contains("Approval " + module_name) ||
		 * data[i][1].contains(module_name + " Approval")) { if (child_id !=
		 * null) { excel.setData("TestReports", "Pass", i, 2); } else {
		 * excel.setData("TestReports", "Fail", i, 2); }
		 * 
		 * driver.switchTo().window(Parent_id); break;
		 * 
		 * } }
		 */

	}

	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfsbm']")
	private WebElement submit_btn;

	@FindBy(xpath = "//div[.='Maker Comments']/..//input[@name='temp_comment']")
	private WebElement maker_comment;

	@FindBy(xpath = "//span/span[.='Submit for Approval']")
	private WebElement approval_submit;

	@FindBy(xpath = "//div[contains(text(),'Submission Successful')]")
	private WebElement submit_success_msg;

	public WebElement getSubmit_success_msg() {
		return submit_success_msg;
	}

	public WebElement getApproval_submit() {
		return approval_submit;
	}

	public WebElement getMaker_comment() {
		return maker_comment;
	}

	public WebElement getSubmit_btn() {
		return submit_btn;
	}

	// Common method to submit all records
	public void submitRecord(String sheet_name, String module_name)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(getSubmit_btn()));

		getSelect_row().click();
		getSubmit_btn().click();

		String[][] data = excel.getdata("Modules");
		getMaker_comment().sendKeys(data[1][3]);

		/*
		 * if (module_name.equalsIgnoreCase("Customer")) { String[][] data =
		 * excel.getdata(sheet_name); getMaker_comment().sendKeys(data[1][5]); }
		 * else if (module_name.equalsIgnoreCase("Account")) { String[][] data =
		 * excel.getdata(sheet_name); getMaker_comment().sendKeys(data[1][5]); }
		 * else if (module_name.equalsIgnoreCase("System User")) { String[][]
		 * data = excel.getdata(sheet_name);
		 * getMaker_comment().sendKeys(data[1][7]); } else if
		 * (module_name.equalsIgnoreCase("Customer Request")) { String[][] data
		 * = excel.getdata(sheet_name); getMaker_comment().sendKeys(data[1][4]);
		 * } else if (module_name.equalsIgnoreCase("Account Request")) {
		 * String[][] data = excel.getdata(sheet_name);
		 * getMaker_comment().sendKeys(data[1][4]); }
		 */

		getApproval_submit().click();

		boolean b;
		try {
			b = getSubmit_success_msg().isDisplayed();
			Reporter.log(module_name + " submitted successfully: " + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		/*
		 * String[][] data = excel.getdata("TestReports");
		 * 
		 * for (int i = 1; i < data.length; i++) { if (data[i][1].contains(
		 * "Submit " + module_name) || data[i][1].contains(module_name +
		 * " Submit ")) { if (b = true) { excel.setData("TestReports", "Pass",
		 * i, 2); } else { excel.setData("TestReports", "Fail", i, 2); } }
		 * 
		 * }
		 */

		Thread.sleep(1000);
	}
}
