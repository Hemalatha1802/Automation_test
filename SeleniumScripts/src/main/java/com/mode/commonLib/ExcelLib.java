package com.mode.commonLib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLib {
	public PropertiesClass prop= new PropertiesClass();
	
	// to get data from Excel
	public String[][] getdata(String sheet) throws EncryptedDocumentException, InvalidFormatException, IOException {

		FileInputStream fis = new FileInputStream(PropertiesClass.filepath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		Row row = sh.getRow(1);
		int r = sh.getLastRowNum();
		int c = row.getLastCellNum();

		String[][] data = new String[r + 1][c];

		for (int i = 1; i <= r; i++) {
			row = sh.getRow(i);

			for (int j = 1; j < c; j++) {

				Cell column = row.getCell(j);

				try {
					if (column == null) {
						data[i][j] = "";
					} else {
						data[i][j] = column.getStringCellValue();
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
				}

			}

		}

		return data;

	}

	
	// To set data into Excel
	public void setData(String sheet, String result, int row, int col)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(PropertiesClass.filepath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		Row r = sh.getRow(row);
		Cell c = r.getCell(col);
		if (c == null) {
			c = r.createCell(col);
			c.setCellValue(result);
		} else {
			c.setCellValue(result);
		}

		FileOutputStream fos = new FileOutputStream(PropertiesClass.filepath);
		wb.write(fos);
		wb.close();

	}

}
