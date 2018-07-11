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
import com.mode.PageObjectRepository.customer.CustomerAdmin;
import com.mode.PageObjectRepository.customer.CustomerChecker;
import com.mode.PageObjectRepository.customer.CustomerMaker;
import com.mode.commonLib.DBConnect;
import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.PropertiesClass;
import com.mode.commonLib.WebDriverCommon;

@Listeners(com.mode.commonLib.ScreenShot.class)
public class CustomerTest {

	DriverClass driver;
	WebDriverCommon drivercommon;
	PropertiesClass prop;
	LoginPage login;
	HomePage home;
	ExcelLib excel;
	String[][] module_data;
	DBConnect dbconnect;
	CustomerMaker maker;
	CustomerChecker cust_checker;
	CommonMakerMethods maker_methods;
	CommonCheckerMethods checker_methods;
	CustomerAdmin cust_admin;
	CommonAdminMethods admin_methods;
	
	
	

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
	
	/*@AfterMethod
	public void writeResult(ITestResult result)
	{
	String method=result.getMethod().getMethodName();
	    try
	    {
	        if(result.getStatus() == ITestResult.SUCCESS)
	        {
	        	excel.setData("TestReports (2)", method, 1, 1);
	            excel.setData("TestReports (2)", "Pass", 1, 2);
	        }
	        else if(result.getStatus() == ITestResult.FAILURE)
	        {
	           
	        	if(result.getStatus() == ITestResult.SUCCESS)
		        {
		        	excel.setData("TestReports (2)", method, 1, 1);
		            excel.setData("TestReports (2)", "Fail", 1, 2);
		        }

	        }
	        else if(result.getStatus() == ITestResult.SKIP)
	        {
	        	if(result.getStatus() == ITestResult.SUCCESS)
		        {
		        	excel.setData("TestReports (2)", method, 1, 1);
		            excel.setData("TestReports (2)", "Skipped", 1, 2);
		        }

	        }
	    }
	    catch(Exception e)
	    {
	        Reporter.log("\nLog Message::@AfterMethod: Exception caught");
	        e.printStackTrace();
	    }

	}
*/
	@Test()
	public void loginWithEmptyDetails()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		login = PageFactory.initElements(DriverClass.driver, LoginPage.class);
		// ------------------------- Empty Details------------------

		String[][] data = excel.getdata(module_data[1][1]);
		String status = "";

		for (int i = 1; i < data.length; i++) {
			login.login(data[i][2], data[i][3]);
			Thread.sleep(2000);
			String msg = drivercommon.getAlertText();
			drivercommon.acceptAlert();
			if (msg != null) {
				status = "Pass";
				excel.setData(module_data[1][1], status, i, 4);

			} else {
				status = "Fail";
				excel.setData(module_data[1][1], status, i, 4);
			}

			Reporter.log("TestCase pass with Empty details", true);
			driver.getUrl();
		}
	}

	@Test

	public void loginFailure()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

		// ------------------------- Invalid Details------------------

		String[][] data = excel.getdata(module_data[2][1]);
		String status = "";

		for (int i = 1; i < data.length; i++) {
			login.login(data[i][2], data[i][3]);
			boolean text = drivercommon.isTextPresent("Authentication Failure");
			Reporter.log("Result is:" +text);
			if (text = true) {
				status = "Pass";
				excel.setData(module_data[2][1], status, i, 4);
				Thread.sleep(2000);
			} else {
				status = "Fail";
				excel.setData(module_data[2][1], status, i, 4);
			}

			Reporter.log("Authentication Failure", true);
			driver.getUrl();
		}
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

		maker = PageFactory.initElements(DriverClass.driver, CustomerMaker.class);
		maker.selectingCustomermenu(module_data[4][2]);
		Reporter.log("Selected Customer Menu", true);
	}

	@Test

	public void register()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker.registerCustomer(module_data[4][2]);
		Reporter.log("Customer is created ", true);

	}

	@Test

	public void searchMaker()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		maker_methods = PageFactory.initElements(DriverClass.driver, CommonMakerMethods.class);
		maker_methods.search(module_data[4][1], module_data[4][2]);
		Reporter.log("performed search based on CIF", true);
	}

	@Test

	public void edit()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.edit(module_data[1][2]);
		Reporter.log("Updated Customer details", true);
	}

	@Test

	public void auditLog()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.audit(module_data[4][2]);
		Reporter.log(" Customer Audit Log test case pass", true);
	}

	@Test

	public void approvalHistory()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		maker_methods.approvalHistory(module_data[4][2]);
		Reporter.log(" Customer Approval History test case pass", true);
	}

	@Test

	public void submitRecord() throws InterruptedException {

		try {
			maker_methods.submitRecord(module_data[4][1], module_data[4][2]);
		} catch (EncryptedDocumentException e) {

			e.printStackTrace();
		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Reporter.log("Customer record submitted successfully", true);

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
		String status = "";

		for (int i = 2; i < data.length - 2; i++) {
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

		cust_checker = PageFactory.initElements(DriverClass.driver, CustomerChecker.class);
		cust_checker.selectingCustomerMenu(module_data[4][2]);

	}

	@Test

	public void searchChecker()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		maker_methods.search(module_data[4][1], module_data[4][2]);
		Reporter.log("performed search based on CIF", true);

	}

	@Test
	public void approve()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.approve(module_data[4][1], module_data[4][2]);
		Reporter.log("Customer Approved", true);

	}

	@Test
	public void deny()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		checker_methods = PageFactory.initElements(DriverClass.driver, CommonCheckerMethods.class);
		checker_methods.deny(module_data[4][1], module_data[4][2]);
		Reporter.log("Customer Rejected", true);
	}

	@Test
	public void selectAdmin()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		cust_admin = PageFactory.initElements(DriverClass.driver, CustomerAdmin.class);
		cust_admin.selectingCustomerMenu(module_data[4][2]);
		Reporter.log("Customer menu selected", true);
		
	}

	@Test
	public void searchAdmin()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {

		maker_methods.search(module_data[4][1], module_data[4][2]);
		Reporter.log("Search Customer successful", true);
	}
	
	@Test
	public void view()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		admin_methods = PageFactory.initElements(DriverClass.driver, CommonAdminMethods.class);
		admin_methods.viewRecord(module_data[4][2]);
		Reporter.log("Customer View Successful", true);

	}
	@Test
	public void resetCustomerMPin()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		
		admin_methods.resetMPin(module_data[4][2]);
		Reporter.log("Customer Reset M-Pin Successful", true);
	}

	

	@Test
	public void resetCustomerTPin()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		admin_methods.resetTPin(module_data[4][2]);
		Reporter.log("Customer Reset T-Pin Successful", true);
	}

	@Test
	public void blockCustomer()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		admin_methods.block(module_data[4][2]);
		Reporter.log("Customer blocked Successfully", true);
	}

	@Test
	public void unblockCustomer()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		admin_methods.unblock(module_data[4][2]);
		Reporter.log("Customer unblocked Successfully", true);
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
