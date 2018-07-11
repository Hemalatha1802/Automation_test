package com.mode.TestNG.wallet;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mode.PageObjectRepository.commonMethods.CommonAdminMethods;
import com.mode.PageObjectRepository.commonMethods.CommonCheckerMethods;
import com.mode.PageObjectRepository.commonMethods.CommonMakerMethods;
import com.mode.PageObjectRepository.commonMethods.HomePage;
import com.mode.PageObjectRepository.commonMethods.LoginPage;
import com.mode.PageObjectRepository.wallet.deposit.WalletDepositAdmin;
import com.mode.PageObjectRepository.wallet.deposit.WalletDepositChecker;
import com.mode.PageObjectRepository.wallet.deposit.WalletDepositMaker;
import com.mode.commonLib.DBConnect;
import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.PropertiesClass;
import com.mode.commonLib.WebDriverCommon;

@Listeners(com.mode.commonLib.ScreenShot.class)
public class WalletDeposit {
  
	DriverClass driver;
	WebDriverCommon drivercommon;
	PropertiesClass prop;
	LoginPage login;
	HomePage home;
	ExcelLib excel;
	String[][] module_data;
	DBConnect dbconnect;
	WalletDepositMaker maker;
	CommonMakerMethods maker_methods;
	CommonCheckerMethods checker_methods;
	CommonAdminMethods admin_methods;
	WalletDepositAdmin admin;
	WalletDepositChecker checker;

	@BeforeClass
	public void beforeClass() throws EncryptedDocumentException, InvalidFormatException, IOException {
		driver = new DriverClass();
		drivercommon = new WebDriverCommon();
		excel = new ExcelLib();
		dbconnect = new DBConnect();
		prop=new PropertiesClass();
				
		prop.prop();
		// -------------- Launching Browser------------------
		driver.launchBrowser();
		Reporter.log("Browser Launched", true);

		// ------------------- Maximize Browser--------------
		driver.maximizeBrowser();

		// --------------- Get URL to pass-------------------
		driver.getUrl();
		Reporter.log("URL passed", true);
		drivercommon.WaitForPageToLoad();
		module_data = excel.getdata("Modules");
	}
	
	@Test
	public void login() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		login = PageFactory.initElements(DriverClass.driver, LoginPage.class);
		// ------------------- Valid Details--------------------------
		String[][] data = excel.getdata(module_data[3][1]);
		String status = "";

		for (int i = 1; i < data.length - 3; i++) {
			login.login(data[i][2], data[i][3]);
			
			boolean b;
			try {
				b = drivercommon.isTextPresent("Welcome");
				Reporter.log("Logged in: " + b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (b = true) {

				status = "Pass";
				excel.setData(module_data[3][1], status, i, 4);
			} else {

				status = "Fail";
				excel.setData(module_data[3][1], status, i, 4);
			}

			Reporter.log("Logged into Application ", true);
		}

		Reporter.log("loop completed");

	}

	@Test

	public void selectingMaker()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker = PageFactory.initElements(DriverClass.driver, WalletDepositMaker.class);
		maker.selectingDepositmenu();
		Reporter.log("Selected Wallet Deposit Menu", true);
	}

	@Test

	public void deposit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker.DepositWalletAmount(module_data[20][1],module_data[20][2]);
		Reporter.log("Wallet Deposit record is created ", true);

	}
	
	@Test
	public void searchMaker() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		
		maker_methods = PageFactory.initElements(DriverClass.driver, CommonMakerMethods.class);
		maker_methods.search(module_data[20][1], module_data[20][2]);
		Reporter.log("performed search based on account no", true);
		Thread.sleep(1000);
	}
	
	
	@Test

	public void edit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.edit(module_data[20][2]);
		Reporter.log("Updated Wallet Deposit details", true);
		Thread.sleep(2000);
	}
	@Test

	public void auditLog()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.audit(module_data[20][2]);
		Reporter.log(" Wallet Deposit Audit Log test case pass", true);
		Thread.sleep(1000);
	}

	@Test

	public void approvalHistory()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.approvalHistory(module_data[20][2]);
		Reporter.log(" Wallet Deposit Approval History test case pass", true);
	}
	
	@Test

	public void submitRecord() throws InterruptedException {

		try {
			maker_methods.submitRecord(module_data[20][1], module_data[20][2]);
		} catch (EncryptedDocumentException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Reporter.log("Wallet Deposit record submitted successfully", true);

	}
	
	@Test

	public void logout() throws InterruptedException {
		home = PageFactory.initElements(DriverClass.driver, HomePage.class);
		home.logout();
		Thread.sleep(1000);
	}
	
	@Test
	public void checkerLogin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		
		// ------------------- Valid Details--------------------------
		String[][] data = excel.getdata(module_data[3][1]);
		

		for (int i = 2; i < data.length - 2; i++) {
			login.login(data[i][2], data[i][3]);
		}
			Thread.sleep(1000);

	}

	@Test

	public void selectChecker()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		checker = PageFactory.initElements(DriverClass.driver, WalletDepositChecker.class);
		checker.selectingDepositMenu(module_data[20][2]);

	}
	
	@Test
	public void searchChecker() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		
		maker_methods = PageFactory.initElements(DriverClass.driver, CommonMakerMethods.class);
		maker_methods.search(module_data[20][1], module_data[20][2]);
		Reporter.log("performed search based on account no", true);
		Thread.sleep(1000);
	}
	
	@Test
	public void approve()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.approve(module_data[20][1], module_data[20][2]);
		Reporter.log("Wallet Deposit record Approved", true);

	}

	@Test
	public void deny()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.deny(module_data[20][1], module_data[20][2]);
		Reporter.log("Wallet Deposit record Rejected", true);
	}

	@Test
	public void selectAdmin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		admin = PageFactory.initElements(DriverClass.driver, WalletDepositAdmin.class);
		admin.selectingDepositMenu(module_data[20][2]);
		Reporter.log("Wallet Deposit menu selected", true);
		
	}

	@Test
	public void searchAdmin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		
		maker_methods = PageFactory.initElements(DriverClass.driver, CommonMakerMethods.class);
		maker_methods.search(module_data[20][1], module_data[20][2]);
		Reporter.log("performed search based on account no", true);
		Thread.sleep(1000);
	}
	
	@Test
	public void view()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		admin_methods=PageFactory.initElements(DriverClass.driver, CommonAdminMethods.class);
		admin_methods.viewRecord(module_data[20][2]);
		Reporter.log("View Wallet Deposit Successful", true);

	}
		
			
	@Test

	public void checkerLogout() throws InterruptedException {

		home.logout();
		Thread.sleep(1000);
	}
	
	@AfterClass
	public void afterClass() {

		driver.quitBrowser();
	}

	
}
