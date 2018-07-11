package com.mode.TestNG.globalConfiguration;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mode.PageObjectRepository.commonMethods.CommonMakerMethods;
import com.mode.PageObjectRepository.commonMethods.HomePage;
import com.mode.PageObjectRepository.commonMethods.LoginPage;
import com.mode.PageObjectRepository.globalConfiguration.Permissions;
import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.PropertiesClass;
import com.mode.commonLib.WebDriverCommon;

@Listeners(com.mode.commonLib.ScreenShot.class)
public class Permission {

	DriverClass driver;
	WebDriverCommon drivercommon;
	PropertiesClass prop;
	LoginPage login;
	HomePage home;
	ExcelLib excel;
	String[][] module_data;
	Permissions permission;
	CommonMakerMethods maker_methods;

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
	public void createPermissionRecord()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		permission = PageFactory.initElements(DriverClass.driver, Permissions.class);
		permission.createPermissionRecord(module_data[33][2]);
		Reporter.log("Permission record created successfully", true);
	}

	
	@Test
	public void searchMaker()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		maker_methods = PageFactory.initElements(DriverClass.driver, CommonMakerMethods.class);
		maker_methods.search(module_data[33][1], module_data[33][2]);
		Reporter.log("performed search based on Feature code", true);
	}

	@Test
	public void edit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.edit(module_data[33][2]);
		Reporter.log("Updated Permission details", true);
	}

	@Test
	public void auditLog()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.audit(module_data[33][2]);
		Reporter.log("Permission Audit Log test case pass", true);
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
