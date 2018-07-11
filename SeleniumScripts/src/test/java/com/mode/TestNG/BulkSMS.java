package com.mode.TestNG;

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

import com.mode.PageObjectRepository.bulkSMS.SMSUpload;
import com.mode.PageObjectRepository.commonMethods.HomePage;
import com.mode.PageObjectRepository.commonMethods.LoginPage;
import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.PropertiesClass;
import com.mode.commonLib.WebDriverCommon;

@Listeners(com.mode.commonLib.ScreenShot.class)
public class BulkSMS {

	DriverClass driver;
	WebDriverCommon drivercommon;
	PropertiesClass prop;
	LoginPage login;
	HomePage home;
	ExcelLib excel;
	String[][] module_data;

	SMSUpload upload_sms;

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
	public void login() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
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
	public void selectingSMSUpload()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		upload_sms = PageFactory.initElements(DriverClass.driver, SMSUpload.class);
		upload_sms.selectingSMSUploadMenu(module_data[28][2]);
		Reporter.log("SMS Upload Menu selected", true);
	}

	@Test
	public void uploadFile()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {
		upload_sms = PageFactory.initElements(DriverClass.driver, SMSUpload.class);
		upload_sms.uploadFile();
		Reporter.log("File Uploaded Successfully", true);
	}

	@Test
	public void toCustomerGroup()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {

		upload_sms.specificCustomerGroup(module_data[28][2]);
		Reporter.log("SMS Successfully sent to Customer Group ", true);
	}

	@Test
	public void toUserGroup()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {

		upload_sms.specificUserGroup(module_data[28][2]);
		Reporter.log("SMS Successfully sent to User Group ", true);
	}

	@Test
	public void toCAAppUser()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {

		upload_sms.cAAppUserGroup(module_data[28][2]);
		Reporter.log("SMS Successfully sent to CA App User Group ", true);
	}

	@Test
	public void toSpecificList()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException {

		upload_sms.specificList(module_data[28][2]);
		Reporter.log("SMS Successfully sent to Specific List ", true);
	}

	@Test
	public void logout() throws InterruptedException {
		home = PageFactory.initElements(DriverClass.driver, HomePage.class);
		home.logout();
		Thread.sleep(1000);
	}

	@AfterClass
	public void afterClass() {

		driver.quitBrowser();
	}
}
