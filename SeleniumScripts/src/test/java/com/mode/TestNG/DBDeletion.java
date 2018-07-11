package com.mode.TestNG;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mode.commonLib.DBConnect;
import com.mode.commonLib.DriverClass;
import com.mode.commonLib.ExcelLib;
import com.mode.commonLib.PropertiesClass;


public class DBDeletion {
  
	DriverClass driver;
	
	PropertiesClass prop;
	
	ExcelLib excel;
	String[][] module_data;
	DBConnect dbconnect;
	
	
	@BeforeClass
	public void beforeClass() throws EncryptedDocumentException, InvalidFormatException, IOException {
		driver = new DriverClass();
		
		excel = new ExcelLib();
		dbconnect = new DBConnect();
		prop=new PropertiesClass();
				
		prop.prop();
		
		module_data = excel.getdata("Modules");
	}
	
	@Test
	public void deleteCustomerRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteCustomer(module_data[4][1]);
		Reporter.log("Customer Record deleted successfully", true);
	}
	
	@Test
	public void deleteAccountRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteAccount(module_data[5][1]);
		Reporter.log("Account Record deleted successfully", true);
	}
	
	
	@Test
	public void deleteBillerRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteBiller(module_data[16][1]);
		Reporter.log("Biller Record deleted successfully");
	}
	
	@Test
	public void deleteProviderRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteProvider(module_data[17][1]);
		Reporter.log("Airtime Provider Record deleted successfully", true);
	}
	
	@Test
	public void deleteUserRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteSystemUser(module_data[6][1]);
		Reporter.log("System User record deleted successfully", true);
	}
	
	@Test
	public void deleteWalletCustomerRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteWalletCustomer(module_data[18][1]);
		Reporter.log("Wallet Customer Record deleted successfully", true);
	}
	
	@Test
	public void deleteWalletAccountRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteWalletAccount(module_data[19][1]);
		Reporter.log("Wallet Account Record deleted successfully", true);
	}
		
	@Test
	public void deleteJointAccountApproverRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteJointAccountApprover(module_data[25][1]);
		Reporter.log("Joint Account Approver Record deleted successfully", true);
	}	
	
	@Test
	public void deleteJointAccountRecord() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException{
		
		dbconnect.deleteJointAccount(module_data[24][1]);
		Reporter.log("Joint Account Approver Record deleted successfully", true);
	}	

	
}
