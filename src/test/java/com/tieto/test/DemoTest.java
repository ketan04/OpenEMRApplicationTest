package com.tieto.test;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoTest {
	
	
	
	public Object[][] fillFormData(){
		
		Object[][] main = new Object[3][2];
		
		main[0][0]="Jhon";
		main[0][1]="Jhon123";
		main[1][0]="peter";
		main[1][1]="peter123";
		main[2][0]="paul";
		main[2][1]="paul123";
		
		return main;
	}
	
	public Object[][] fillInvalidCredentialTest(){
		
		Object[][] main = new Object[2][4];
		
		main[0][0]="Jhon";
		main[0][1]="Jhon123";
		main[0][2]="Default - English (Standard)";
		main[0][3]="Invalid username or password";
		main[1][0]="peter";
		main[1][1]="peter123";
		main[1][2]="Armenian";
		main[1][3]="Invalid username or password";
		
		return main;
	}

	
	public void fillFormTest(String userName, String password ) {
		
		System.out.println(userName + password);
	}
	
	@Test(dataProvider = "fillInvalidCredentialTest")
	public void invalidCredentialTest(String userName, String password, String language, String expectedValue) {
		System.out.println(userName+password+language+expectedValue);
	}
	
	
	

	public Object[][] excelRead() throws IOException {
		
		FileInputStream file = new FileInputStream("TestData/OpenEMRData.xlsx");
		
		XSSFWorkbook book = new XSSFWorkbook(file);
		
		XSSFSheet sheet = book.getSheet("validCredentialData");
		
		Object[][] inputData = new Object[sheet.getPhysicalNumberOfRows()][4];
		
		//sheet.getLastRowNum(
		
		for(int r=1;r<sheet.getPhysicalNumberOfRows();r++) {
			for(int c=0; c<sheet.getRow(r).getPhysicalNumberOfCells(); c++) {
				//System.out.println(new DataFormatter().formatCellValue(sheet.getRow(r).getCell(c)));
				inputData[r][c]=new DataFormatter().formatCellValue(sheet.getRow(r).getCell(c));
			}
			
		}
		
		book.close();
		file.close();
		return inputData;
		
	}
	
	
	public void readProperties() throws IOException {
		
		FileInputStream file = new FileInputStream("TestData/data.properties");
		Properties prop = new Properties();
		prop.load(file);
		String baseURL = prop.getProperty("url");
		
		System.out.println(baseURL);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
