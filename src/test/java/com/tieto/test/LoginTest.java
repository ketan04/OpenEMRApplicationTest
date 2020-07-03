package com.tieto.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tieto.base.WebDriverWrapper;
import com.tieto.pages.DashboardPage;
import com.tieto.pages.LoginPage;
import com.tieto.utilities.ExcelUtils;


public class LoginTest extends WebDriverWrapper { 
	
	@Test(priority = 1, dataProvider = "validCredentialData")
	public void validCredentialTest(String userName, String password, String language, String expectedValue) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsernName(userName);
		loginPage.enterPassword(password);
		loginPage.setLanguage(language);
		loginPage.clickLogin();
		
		DashboardPage dashboardPage = new DashboardPage(driver);
		dashboardPage.waitForPresenceofMessageCenterText();		
		String actualTitle = dashboardPage.getCurrentTabTittle();
		System.out.println(actualTitle);
		
		AssertJUnit.assertEquals(actualTitle, expectedValue);
		
		/*
		 * new Actions(driver).moveToElement(driver.findElement(By.xpath(
		 * "//span[@data-bind='text:fname']"))).perform();
		 * (driver.findElement(By.xpath("//li[@data-bind='click: logout']"))).click();
		 */
	}
	
	
	@Test(priority = 2, dataProvider = "excelReadForInvalidData")
	public void inValidCredentialTest(String userName, String password, String language, String expectedValue) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsernName(userName);
		loginPage.enterPassword(password);
		loginPage.setLanguage(language);
		loginPage.clickLogin();

		
		String actualTitle = (driver.findElement(By.xpath("//div[@class='alert alert-danger login-failure m-1']"))).getText();
		System.out.println(actualTitle);
		
		AssertJUnit.assertTrue(actualTitle.contains(expectedValue));
		
	}
	
	@DataProvider
	public Object[][] validCredentialData() throws IOException {
		
		return ExcelUtils.getSheetIntoObject("TestData/OpenEMRData.xlsx", "validCredentialData");
	}
	
	@DataProvider
	public Object[][] excelReadForInvalidData() throws IOException {
		
		return ExcelUtils.getSheetIntoObject("TestData/OpenEMRData.xlsx", "inValidCredentialData");
	}
	
	
	
}
