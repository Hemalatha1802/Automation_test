package com.mode.commonLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;

public class WebDriverCommon {

	public void WaitForPageToLoad() {

		DriverClass.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	//Verifies whether text is present
	public boolean isTextPresent(String text) {
		try {
			boolean b = DriverClass.driver.getPageSource().contains(text);
			return b;
		} catch (Exception e) {
			return false;
		}
	}

	
	// Accepts HTML alert
	public void acceptAlert() {

		Alert alert = DriverClass.driver.switchTo().alert();
		alert.accept();
	}

	//Dismiss HTML alert
	public void dismissAlert() {

		Alert alert = DriverClass.driver.switchTo().alert();
		alert.dismiss();
	}

	// gets text from alert
	public String getAlertText() {

		Alert alert = DriverClass.driver.switchTo().alert();
		String text = alert.getText();
		return text;
	}

}
