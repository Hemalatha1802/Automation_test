package com.mode.TestNG.payment;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mode.PageObjectRepository.bulkPayment.fileUpload.UploadAdmin;
import com.mode.PageObjectRepository.bulkPayment.fileUpload.UploadChecker;
import com.mode.PageObjectRepository.bulkPayment.fileUpload.UploadMaker;
import com.mode.PageObjectRepository.commonMethods.CommonAdminMethods;
import com.mode.PageObjectRepository.commonMethods.CommonCheckerMethods;
import com.mode.PageObjectRepository.commonMethods.CommonMakerMethods;
import com.mode.PageObjectRepository.commonMethods.HomePage;
import com.mode.PageObjectRepository.commonMethods.LoginPage;
import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.PropertiesClass;
import com.mode.commonLib.WebDriverCommon;

@Listeners(com.mode.commonLib.ScreenShot.class)
public class BulkPaymentFileUpload {
  
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
	UploadMaker maker;
	UploadChecker checker;
	UploadAdmin admin;
	

	@BeforeClass
	public void beforeClass() throws EncryptedDocumentException, InvalidFormatException, IOException {
		driver = new DriverClass();
		drivercommon = new WebDriverCommon();
		excel = new ExcelLib();
		
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

		maker = PageFactory.initElements(DriverClass.driver, UploadMaker.class);
		maker.selectingfileUploadMenu();
		Reporter.log("Selected File Upload Menu", true);
	}

	@Test

	public void upload()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException, AWTException {

		maker.uploadFile();
		Reporter.log("Bulk Payment file Uploaded successfully ", true);

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

		checker = PageFactory.initElements(DriverClass.driver, UploadChecker.class);
		checker.selectingPaymentMenu(module_data[30][2]);

	}
	
	@Test
	public void searchChecker() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		
		maker_methods=PageFactory.initElements(DriverClass.driver, CommonMakerMethods.class);
		maker_methods.search(module_data[30][1], module_data[30][2]);
		Reporter.log("performed search based on To Account", true);
		Thread.sleep(2000);
	}
	
		
	@Test
	public void approve()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.approve(module_data[30][1], module_data[30][2]);
		Reporter.log("Bulk Payment record Approved", true);

	}

	@Test
	public void deny()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.deny(module_data[30][1], module_data[30][2]);
		Reporter.log("Bulk Payment record Rejected", true);
	}
	
	@Test
	public void approveGroup()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		checker_methods.approveGroup(module_data[30][1], module_data[30][2]);
		Reporter.log("Bulk Payment record Approved", true);

	}

	@Test
	public void denyGroup()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.denyGroup(module_data[30][1], module_data[30][2]);
		Reporter.log("Bulk Payment record Rejected", true);
	}

	@Test
	public void selectAdmin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		admin = PageFactory.initElements(DriverClass.driver, UploadAdmin.class);
		admin.selectingPaymentMenu(module_data[30][2]);
		Reporter.log("Bulk Payment menu selected", true);
		
	}

	@Test
	public void searchAdmin() throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException{
		
		
		maker_methods.search(module_data[30][1], module_data[30][2]);
		Reporter.log("performed search based on To acc no", true);
		Thread.sleep(2000);
	}
		
	@Test
	public void view()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		admin_methods=PageFactory.initElements(DriverClass.driver, CommonAdminMethods.class);
		admin_methods.viewRecord(module_data[30][2]);
		Reporter.log("View Bulk Payment record Successful", true);

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
