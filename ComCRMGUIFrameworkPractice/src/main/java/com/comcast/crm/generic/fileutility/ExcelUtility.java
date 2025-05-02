package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcelFile(String sheetName, int row, int cell)
			throws EncryptedDocumentException, IOException {

		String filePath = "./testData/testscript.xlsx";
		Workbook wb = null;
		try (FileInputStream fis = new FileInputStream(filePath)) {
			wb = WorkbookFactory.create(fis);
		} catch (IOException | EncryptedDocumentException e) {
			System.err.println("Error reading the Excel file: " + e.getMessage());
			e.printStackTrace();
		}
		String data = wb.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return data;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {

		// Path to the .xlsx file
		String filePath1 = "./testData/testscript.xlsx";
		Workbook wb = null;

		// Using try-catch to handle exceptions
		FileInputStream fis = new FileInputStream(filePath1);

		// Open the Excel workbook
		wb = WorkbookFactory.create(fis);

		// Get the first sheet from the workbook
		int rowCount = wb.getSheet(sheetName).getLastRowNum();

		wb.close();

		return rowCount;
	}

	public void setDataBackToExcelFile(String sheetName, int row, int cell, String data)
			throws EncryptedDocumentException, IOException {

		// Path to the .xlsx file
		String filePath1 = "./testData/testscript.xlsx";
		FileInputStream fis = new FileInputStream(filePath1);

		// Open the Excel workbook
		Workbook wb = WorkbookFactory.create(fis);

		// Get the first sheet from the workbook
		wb.getSheet(sheetName).getRow(row).createCell(cell);

		FileOutputStream fos = new FileOutputStream("./testData/testscript.xlsx");
		wb.write(fos);
		wb.close();
	}
}
