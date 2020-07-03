package com.tieto.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Object[][] getSheetIntoObject(String fileName, String sheetName) throws IOException {

		FileInputStream file = new FileInputStream(fileName);

		XSSFWorkbook book = new XSSFWorkbook(file);

		XSSFSheet sheet = book.getSheet(sheetName);

		Object[][] inputData = new Object[sheet.getPhysicalNumberOfRows() - 1][sheet.getRow(0).getPhysicalNumberOfCells()];

		// sheet.getLastRowNum(

		for (int r = 1; r < sheet.getPhysicalNumberOfRows(); r++) {
			for (int c = 0; c < sheet.getRow(r).getPhysicalNumberOfCells(); c++) {
				// System.out.println(new
				// DataFormatter().formatCellValue(sheet.getRow(r).getCell(c)));
				inputData[r - 1][c] = new DataFormatter().formatCellValue(sheet.getRow(r).getCell(c));
			}

		}

		book.close();
		file.close();
		return inputData;

	}
}
