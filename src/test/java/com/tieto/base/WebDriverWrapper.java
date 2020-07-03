package com.tieto.base;

 

import java.io.IOException;
import java.util.concurrent.TimeUnit;

 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.tieto.utilities.PropUtils;

 

public class WebDriverWrapper {

 

    // TODO Auto-generated method stub
    public WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod
    public void init(@Optional("ch") String browserName) throws IOException {
    	
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "Driver/geckodriver.exe");
		System.setProperty("webdriver.ie.driver", "Driver/InternetExplorerDriver.exe");
		if(!browserName.isEmpty()) {
			 if (browserName.toLowerCase().equals("ie")) {
		        	driver = new InternetExplorerDriver();
		        }
		        else if (browserName.toLowerCase().equals("ch")) {
		        	driver = new ChromeDriver();
		        }
		        else if (browserName.toLowerCase().equals("ff")) {
		        	driver = new FirefoxDriver();
		        }
		}else driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(PropUtils.getProperty("TestData/data.properties", "url"));
      
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }

 

}