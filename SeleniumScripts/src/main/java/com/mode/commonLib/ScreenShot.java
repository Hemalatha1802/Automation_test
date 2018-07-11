package com.mode.commonLib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenShot implements ITestListener {
	ExcelLib excel = new ExcelLib();
	private static String fileSeperator = System.getProperty("file.separator");
	public String file_created="File created ";

	public void onFinish(ITestContext arg0) {
		//Capture screen shots on test case completed
	}

	public void onStart(ITestContext result) {

		// Cleaning Failed Screenshots before script runs
		File src_failed = new File("\\SeleniumScripts\\screenshots\\failed_Sreenshots");
		
		if(src_failed.exists()){
		try {
			FileUtils.cleanDirectory(src_failed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		// Cleaning Success Screenshots before script runs
		File src_success = new File("\\SeleniumScripts\\screenshots\\success_Sreenshots");

		if(src_success.exists()){
		try {

			FileUtils.cleanDirectory(src_success);

		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		// Cleaning Skipped Screenshots before script runs
		File src_skipped = new File("\\SeleniumScripts\\screenshots\\skipped_Screenshots");
		
		if(src_skipped.exists()){
		try {
			FileUtils.cleanDirectory(src_skipped);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		//Capture screen shots for test case success percentage
	}

	// capture Screen shot if test case gets fail
	public void onTestFailure(ITestResult result) {

		String testClassName = getTestClassName(result.getInstanceName()).trim();
		File file = new File("\\SeleniumScripts\\screenshots\\failed_Sreenshots\\"
				+ fileSeperator + testClassName + fileSeperator);
		String filepath = result.getMethod().getMethodName();
		EventFiringWebDriver efw = new EventFiringWebDriver(DriverClass.driver);
		
		File src = efw.getScreenshotAs(OutputType.FILE);
		File des = new File(
				"\\SeleniumScripts\\screenshots\\failed_Sreenshots\\" + filepath + ".png");

		File desc = new File(file + "\\" + filepath + ".png");

		if (!file.exists()) {
			Reporter.log(file_created + file);
			file.mkdir();
			try {
				FileUtils.copyFile(src, desc);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		File src_failed = new File("\\SeleniumScripts\\screenshots\\failed_Sreenshots\\");
		File desc_failed = new File("\\SeleniumScripts\\backup_screens");
		try {
			FileUtils.copyFile(src, des);

			FileUtils.copyDirectoryToDirectory(src_failed, desc_failed);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// capture Screen shot if test case gets skip
	public void onTestSkipped(ITestResult result) {

		String testClassName = getTestClassName(result.getInstanceName()).trim();

		File file = new File("\\SeleniumScripts\\screenshots\\skipped_Screenshots\\"
				+ fileSeperator + testClassName + fileSeperator);
		String filepath = result.getMethod().getMethodName();

		EventFiringWebDriver efw = new EventFiringWebDriver(DriverClass.driver);
		File src = efw.getScreenshotAs(OutputType.FILE);

		File desc = new File(file + "\\" + filepath + ".png");

		if (!file.exists()) {
			Reporter.log(file_created + file);
			file.mkdir();
			try {
				FileUtils.copyFile(src, desc);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		File src_skipped = new File("\\SeleniumScripts\\screenshots\\skipped_Screenshots\\");
		File desc_skipped = new File("\\SeleniumScripts\\backup_screens");

		try {
			FileUtils.copyFile(src, desc);
			FileUtils.copyDirectoryToDirectory(src_skipped, desc_skipped);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void onTestStart(ITestResult result) {
		//Capture screen shots on start of test case 
	}

	// capture Screen shot if test case gets pass
	public void onTestSuccess(ITestResult result) {

		String testClassName = getTestClassName(result.getInstanceName()).trim();

		String filepath = result.getMethod().getMethodName();

		EventFiringWebDriver efb = new EventFiringWebDriver(DriverClass.driver);
		File src = efb.getScreenshotAs(OutputType.FILE);

		File file = new File("\\SeleniumScripts\\screenshots\\success_Sreenshots\\" + fileSeperator
				+ testClassName + fileSeperator);
		File desc = new File(file + "\\" + filepath + ".png");

		if (!file.exists()) {
			Reporter.log(file_created + file);
			file.mkdir();
			try {
				FileUtils.copyFile(src, desc);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		File src_success = new File("\\SeleniumScripts\\screenshots\\success_Sreenshots\\");
		File desc_success = new File("\\SeleniumScripts\\backup_screens");

		try {
			FileUtils.copyFile(src, desc);
			FileUtils.copyDirectoryToDirectory(src_success, desc_success);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		Reporter.log("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}
}
