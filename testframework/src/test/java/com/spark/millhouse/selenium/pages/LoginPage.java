package com.spark.millhouse.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By emailInputField = By.id("emailInput");
	public static By passwordInputField = By.id("passwordInput");
	public static By loginButton = By.id("loginButton");
	public static By signUpButton = By.id("signUpButton");


	public void enterEmailInput(String emailInput) {
		driver.findElement(emailInputField).sendKeys(emailInput);
	}
	
	public void enterPasswordInput(String passwordInput) {
		driver.findElement(passwordInputField).sendKeys(passwordInput);
	}
	
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public void clickSignUpButton() {
		driver.findElement(signUpButton).click();
	}
}
