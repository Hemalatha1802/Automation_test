package com.mode.PageObjectRepository.commonMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
		
	@FindBy(id="tfUseridTemp")
	private WebElement userName;
	
	@FindBy(id="tfUserpasswordTemp")
	private WebElement password;
	
	@FindBy(xpath="//a[@onclick='submitForm()']")
	private WebElement loginBtn;
	
	public WebElement getUser() {
		return userName;
	}
	public WebElement getPwd() {
		return password;
	}
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void login(String user, String pwd) throws InterruptedException {
		getUser().sendKeys(user);
		getPwd().sendKeys(pwd);
		getLoginBtn().click();
		Thread.sleep(1000);
		
		
		
		
		
		
	}

}
