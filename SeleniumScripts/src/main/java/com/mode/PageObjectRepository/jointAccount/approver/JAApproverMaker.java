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
import com.mode.commonLib.ExcelLib;

public class JAApproverMaker extends DriverClass{

	
	ExcelLib excel= new ExcelLib();
	
	@FindBy(xpath = "//span[@class='l-btn-text icon-mfmaker l-btn-icon-left']")
	private WebElement maker_menu;
	
	@FindBy(xpath="//div[@id='maker']//span[text()='Joint Accounts']")
	private WebElement joint_acc;
	
	@FindBy(xpath="//div[@href='makermfjointaccountapproverview.jsp']")
	private WebElement ja_approver;
	
	@FindBy(xpath = "//span[@class='l-btn-empty icon-mfadd']")
	private WebElement add_btn;
	
	public WebElement getMaker_menu() {
		return maker_menu;
	}

	
	public WebElement getAdd_btn() {
		return add_btn;
	}

	public void selectingjApprover() throws InterruptedException{
		
		
		Actions action = new Actions(driver);
		Reporter.log("Element is displayed: " + maker_menu.isDisplayed());
		action.moveToElement(maker_menu).perform();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", maker_menu);
		Thread.sleep(500);
		Reporter.log("Element is displayed: " + joint_acc.isDisplayed());
		action.moveToElement(joint_acc).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ja_approver)).click();
		Thread.sleep(1000);
		
				
	}
	
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='acc_num']")
	private WebElement acc_no;
	
	@FindBy(xpath="//a[@onclick='loadMasterApproverDetail()']")
	private WebElement load_custName;
	
	@FindBy(xpath="//input[@name='alias' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement alias;
	
	@FindBy(xpath="//input[@class='easyui-validatebox validatebox-text validatebox-invalid' and @name='cust_id']")
	private WebElement approver_cif;
	
	@FindBy(xpath="//a[@onclick='loadApproverName()']")
	private WebElement load_approverName;	
	
	@FindBy(xpath="//input[@comboname='mandatory']/..//span[@class='combo-arrow']")
	private WebElement approver_dropdown;
	
	@FindBy(xpath="//div[@value='Y']")
	private WebElement approver_dropdown_value;
	
	@FindBy(xpath="//input[@comboname='seq_code']/..//span[@class='combo-arrow']")
	private WebElement seq_dropdown;
	
	@FindBy(xpath="//div[@value='1']")
	private WebElement seq_dropdown_value;
	
	@FindBy(xpath="//input[@comboname='role_code']/..//span[@class='combo-arrow']")
	private WebElement role_dropdown;
	
	@FindBy(xpath="//div[@value='APPROVER']")
	private WebElement role_dropdown_value;
	
	@FindBy(xpath="//input[@numberboxname='max_approval_amount' and @class='easyui-numberbox numberbox-f validatebox-text validatebox-invalid']")
	private WebElement max_amount;
	
	@FindBy(xpath="//input[@name='comments' and @class='easyui-validatebox validatebox-text validatebox-invalid']")
	private WebElement comments;
	
	@FindBy(xpath = "//a[@onclick='saveRecord()']//span[text()='Save']")
	private WebElement save;
	
		
		
	public WebElement getJa_approver() {
		return ja_approver;
	}


	public WebElement getAlias() {
		return alias;
	}


	public WebElement getApprover_cif() {
		return approver_cif;
	}


	public WebElement getLoad_approverName() {
		return load_approverName;
	}


	public WebElement getApprover_dropdown() {
		return approver_dropdown;
	}


	public WebElement getApprover_dropdown_value() {
		return approver_dropdown_value;
	}


	public WebElement getSeq_dropdown() {
		return seq_dropdown;
	}


	public WebElement getSeq_dropdown_value() {
		return seq_dropdown_value;
	}


	public WebElement getRole_dropdown() {
		return role_dropdown;
	}


	public WebElement getRole_dropdown_value() {
		return role_dropdown_value;
	}


	public WebElement getMax_amount() {
		return max_amount;
	}


	public WebElement getAcc_no() {
		return acc_no;
	}

	
	public WebElement getComments() {
		return comments;
	}


	public WebElement getLoad_custName() {
		return load_custName;
	}

		
	public WebElement getSave() {
		return save;
	}
	
	public void registerJApprover(String sheet_name, String module_name) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		getAdd_btn().click();
		new Actions(driver).moveToElement(acc_no).build().perform();
		getAcc_no().click();
		String[][] data = excel.getdata(sheet_name);

		getAcc_no().sendKeys(data[1][2]);
		Thread.sleep(500);

		load_custName.isDisplayed();
		if (load_custName.isEnabled()) {
			new Actions(driver).moveToElement(load_custName).build().perform();
			getLoad_custName().click();
		}
		
		Thread.sleep(500);
		
		
		
		new Actions(driver).moveToElement(approver_cif).build().perform();
		getApprover_cif().click();
		getApprover_cif().sendKeys(data[1][4]);
		Thread.sleep(500);

		load_approverName.isDisplayed();
		if (load_approverName.isEnabled()) {
			new Actions(driver).moveToElement(load_approverName).build().perform();
			getLoad_approverName().click();
		}
		Thread.sleep(500);
		getSeq_dropdown().click();
		getSeq_dropdown_value().click();
		getRole_dropdown().click();
		getRole_dropdown_value().click();
		getApprover_dropdown().click();
		getApprover_dropdown_value().click();
		getAlias().sendKeys(data[1][3]);
		
		
		getMax_amount().sendKeys(data[1][5]);
		
		Thread.sleep(500);
		getSave().click();
		
	}
	
	
}
