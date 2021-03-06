package com.mode.TestNG;

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
import com.mode.PageObjectRepository.systemUserRequest.UserRequestAdmin;
import com.mode.PageObjectRepository.systemUserRequest.UserRequestChecker;
import com.mode.PageObjectRepository.systemUserRequest.UserRequestMaker;
import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.PropertiesClass;
import com.mode.commonLib.WebDriverCommon;


@Listeners(com.mode.commonLib.ScreenShot.class)
public class SystemUserRequest {

	DriverClass driver;
	WebDriverCommon drivercommon;
	PropertiesClass prop;
	LoginPage login;
	HomePage home;
	ExcelLib excel;
	String[][] module_data;
	
	CommonMakerMethods maker_methods;
	CommonCheckerMethods checker_methods;
	CommonAdminMethods admin_methods;
	UserRequestMaker user_maker;
	UserRequestChecker user_checker;
	UserRequestAdmin user_admin;

	@BeforeClass
	public void beforeClass() throws EncryptedDocumentException, InvalidFormatException, IOException {
		driver = new DriverClass();
		drivercommon = new WebDriverCommon();
		excel = new ExcelLib();
	
		prop = new PropertiesClass();

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
	public void login()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		login = PageFactory.initElements(DriverClass.driver, LoginPage.class);
		String[][] data = excel.getdata(module_data[3][1]);
		String status = "";

		for (int i = 3; i < data.length - 1; i++) {
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
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		user_maker = PageFactory.initElements(DriverClass.driver, UserRequestMaker.class);
		user_maker.selectUserRequestMenu(module_data[31][2]);
		Reporter.log("Maker System User Menu selected", true);
	}

	@Test
	public void register()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		user_maker.registerSystemUserRequest(module_data[31][1], module_data[31][2]);
		Reporter.log("User Request record created  Successfully", true);
	}

	@Test
	public void searchMaker()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		maker_methods = PageFactory.initElements(DriverClass.driver, CommonMakerMethods.class);
		maker_methods.search(module_data[31][1], module_data[31][2]);
		Reporter.log("performed search based on User Id", true);
	}

	@Test
	public void edit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.edit(module_data[31][2]);
		Reporter.log("Updated System User request details", true);
	}

	@Test
	public void auditLog()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.audit(module_data[31][2]);
		Reporter.log("System User Request Audit Log test case pass", true);
	}

	@Test
	public void approvalHistory()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.approvalHistory(module_data[31][2]);
		Reporter.log("System User Request Approval History test case pass", true);
	}

	@Test
	public void submitRecord() throws InterruptedException {

		try {
			maker_methods.submitRecord(module_data[31][1], module_data[31][2]);
		} catch (EncryptedDocumentException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Reporter.log("Record submitted successfully", true);

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

		String[][] data = excel.getdata(module_data[3][1]);
		String status = "";

		for (int i = 4; i < data.length; i++) {
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
		Thread.sleep(1000);

	}

	@Test
	public void selectChecker()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		user_checker = PageFactory.initElements(DriverClass.driver, UserRequestChecker.class);
		user_checker.selectingUserRequestMenu(module_data[31][2]);

	}

	@Test
	public void searchChecker()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.search(module_data[31][1], module_data[31][2]);
		Reporter.log("performed search based on UserId", true);
	}

	@Test
	public void approve()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.approve(module_data[31][1], module_data[31][2]);
		Reporter.log("System User Request Record Approved", true);

	}

	@Test
	public void deny()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		checker_methods.deny(module_data[31][1], module_data[31][2]);
		Reporter.log("System User Request Record Rejected", true);
	}

	@Test
	public void selectAdmin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		user_admin = PageFactory.initElements(DriverClass.driver, UserRequestAdmin.class);
		user_admin.selectingSystemUserRequestMenu(module_data[31][2]);
		Reporter.log("System User Request menu selected", true);
		
	}

	@Test
	public void searchAdmin()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		maker_methods.search(module_data[31][1], module_data[31][2]);
		Reporter.log("Search record successful", true);
	}

	@Test
	public void view()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		admin_methods = PageFactory.initElements(DriverClass.driver, CommonAdminMethods.class);
		admin_methods.viewRecord(module_data[31][2]);
		Reporter.log("View System user Request Successful", true);
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
