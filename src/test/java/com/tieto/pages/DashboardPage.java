package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	
	private WebDriver driver;
	private By messageCenterLocator = By.xpath("//span[text()='Message Center']");

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForPresenceofMessageCenterText() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.presenceOfElementLocated(messageCenterLocator));
	}
	
	public String getCurrentTabTittle() {
		return driver.getTitle();
	}

}
