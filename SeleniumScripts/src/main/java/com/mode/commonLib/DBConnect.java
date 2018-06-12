package com.mode.commonLib;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Reporter;

public class DBConnect {
	ExcelLib ex = new ExcelLib();
	public static Connection con;
	public static Statement stmt;

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mode", "root", "root");
			stmt = con.createStatement();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// deleting Customer from DB
	public void deleteCustomer(String sheet_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);
		String cif_no = data[1][4];
		Reporter.log(cif_no);
		DBConnect.connection();
		String sql = "delete from customer where cif=" + cif_no;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		

	}

	// deleting Account from DB
	public void deleteAccount(String sheet_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);

		String acc_no = data[1][2];

		Reporter.log("--------------------------------------------------");
		Reporter.log(acc_no);

		DBConnect.connection();
		String sql = "delete from account where ACC_NUM=" + acc_no;

		try {

			stmt.executeUpdate(sql);

			Reporter.log("Account record is:" + sql);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// deleting Biller from DB
	public void deleteBiller(String sheet_name) throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);

		String acc_no = data[1][3];

		DBConnect.connection();
		String sql = "delete from biller where BILLER_ACNO=" + acc_no;

		try {

			stmt.executeUpdate(sql);

			Reporter.log("Biller record is:" + sql);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// deleting System User from DB
	public void deleteSystemUser(String sheet_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);
		String user_id = data[1][2];
		Reporter.log("--------------------------------------------------");
		Reporter.log(user_id);
		DBConnect.connection();

		String sql = "delete from system_user where USER_ID='" + user_id + "'";
		try {

			stmt.executeUpdate(sql);

			Reporter.log("System user record is:" + sql);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// deleting Airtime Provider from DB
	public void deleteProvider(String sheet_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);

		String acc_no = data[1][3];

		DBConnect.connection();
		String sql = "delete from atp_provider where ATP_ACNO=" + acc_no;

		try {

			stmt.executeUpdate(sql);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// deleting Wallet Customer from DB
	public void deleteWalletCustomer(String sheet_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);

		String cif = data[1][2];

		DBConnect.connection();
		String sql = "delete from cbcustomer where CIF=" + cif;

		try {

			stmt.executeUpdate(sql);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// deleting Wallet Account from DB
	public void deleteWalletAccount(String sheet_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);

		String acc_no = data[1][3];

		DBConnect.connection();
		String sql = "delete from cbaccount where ACC_NUM=" + acc_no;

		try {

			stmt.executeUpdate(sql);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// deleting Joint Account Approver from DB
	public void deleteJointAccountApprover(String sheet_name)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String[][] data = ex.getdata(sheet_name);

		String acc_no = data[1][2];

		DBConnect.connection();
		String sql = "delete from join_account_approver where ACC_NUM=" + acc_no;

		try {

			stmt.executeUpdate(sql);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	// deleting Joint Account from DB
		public void deleteJointAccount(String sheet_name)
				throws EncryptedDocumentException, InvalidFormatException, IOException {
			String[][] data = ex.getdata(sheet_name);

			String acc_no = data[1][2];

			DBConnect.connection();
			String sql = "delete from joint_account_request where ACC_NO=" + acc_no;

			try {

				stmt.executeUpdate(sql);

			} catch (Exception e) {

				e.printStackTrace();

			}

		}


}
