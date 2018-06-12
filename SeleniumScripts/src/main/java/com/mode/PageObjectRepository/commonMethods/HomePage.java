package com.mode.PageObjectRepository.commonMethods;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
//--------------------------- Logout---------------------
	@FindBy(xpath = "//a[@class='easyui-linkbutton l-btn l-btn-plain' and @href='login.jsp']")
	private WebElement logout;

	public WebElement getLogout() {

		return logout;
	}

	public void logout() {

		logout.click();
	}
	
	//--------------------- uploading file-------------------------------------------

	
public void fileUpload() throws IOException{
	
	Runtime.getRuntime().exec("C:\\Users\\Hemalatha\\Desktop\\fileupload.exe");
}
	//------------------------------ Maker Menu------------------------
	
	
}
