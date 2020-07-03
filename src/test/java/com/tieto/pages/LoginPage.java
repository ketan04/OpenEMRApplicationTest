package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

	private By userNameLocator = By.id("authUser");
	private By userPasswordLocator = By.id("clearPass");
	private By languageSelectLocator = By.name("languageChoice");
	private By logingLocator = By.xpath("//button[@type='submit']");

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsernName(String userName) {
		driver.findElement(userNameLocator).sendKeys(userName);
	}

	public void enterPassword(String userPassword) {
		(driver.findElement(userPasswordLocator)).sendKeys(userPassword);
	}

	public void setLanguage(String text) {
		new Select(driver.findElement(languageSelectLocator)).selectByVisibleText(text);
	}

	public void clickLogin() {
		(driver.findElement(logingLocator)).submit();
	}

}
