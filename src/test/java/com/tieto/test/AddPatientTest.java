package com.tieto.test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tieto.base.WebDriverWrapper;
import com.tieto.utilities.ExcelUtils;

public class AddPatientTest extends WebDriverWrapper 
{
	
	@DataProvider
    public Object[][] CreatePatientData() throws IOException
    {
        return ExcelUtils.getSheetIntoObject("Testdata/OpenEMRData.xlsx", "CreatePatientData");
                
    }
	@Test
	public void createPatientTest() throws InterruptedException
	{
		driver.findElement(By.id("authUser")).sendKeys("admin");		
		driver.findElement(By.id("clearPass")).sendKeys("pass");
		Select selectLanguage = new Select(driver.findElement(By.name("languageChoice")));
		selectLanguage.selectByVisibleText("English (Indian)");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Patient/Client')]"))).build().perform();
		driver.findElement(By.xpath("//div[contains(text(),'Patients')]")).click();		
		
		String patientFinderWindow = driver.getWindowHandle();
		driver.switchTo().window(patientFinderWindow);		
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='fin']")));
		driver.findElement(By.id("create_patient_btn1")).click();
		driver.switchTo().defaultContent();
		
		String addPatientFinderWindow = driver.getWindowHandle();
		driver.switchTo().window(addPatientFinderWindow);		
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='pat']")));
		Select updateTitle = new Select(driver.findElement(By.id("form_title")));
		updateTitle.selectByVisibleText("Mr.");
		driver.findElement(By.id("form_fname")).sendKeys("Raju");
		driver.findElement(By.id("form_lname")).sendKeys("Patient");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  		  
		driver.findElement(By.id("form_DOB")).sendKeys(dtf.format(now));
		Select updateSex = new Select(driver.findElement(By.id("form_sex")));
		updateSex.selectByVisibleText("Male");
		driver.findElement(By.id("create")).click();
		driver.switchTo().defaultContent();
		
		String confirmPatientrWindow = driver.getWindowHandle();
		driver.switchTo().window(confirmPatientrWindow);		
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();
		WebDriverWait wait = new WebDriverWait(driver,40);
		Thread.sleep(5000);		
		Alert alert= driver.switchTo().alert();
		alert.accept();		
		driver.findElement(By.xpath("//div[@class='closeDlgIframe']")).click();		
		driver.switchTo().defaultContent();
		
	}

}
