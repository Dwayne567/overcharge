package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spark.millhouse.reports.ExtentManager;
import com.spark.millhouse.selenium.pages.DeckListPage;
import com.spark.millhouse.selenium.pages.LoginPage;
import com.spark.millhouse.utils.DriverManager;

public class DeckListTest {
	WebDriver driver;
	LoginPage loginPage;
	DeckListPage deckListPage;

	@BeforeSuite
	public void setUp() {
		try {
			ExtentManager.setExtent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = DriverManager.getDriver("chrome", "C:/Users/millh/Software/chromedriver.exe");
		loginPage = new LoginPage(driver);
		deckListPage = new DeckListPage(driver);
	}

	@BeforeMethod
	public void login() throws InterruptedException {
		// Navigate to the login page
		driver.get("http://localhost:4200");

		// Creating an instance of the WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.emailInputField));
		loginPage.enterEmailInput("bob@test.com");
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.passwordInputField));
		loginPage.enterPasswordInput("newmoney");
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(LoginPage.loginButton));
		loginPage.clickLoginButton();
		Thread.sleep(1000);
	}

	@Test
	public void testDeckList() throws InterruptedException {
		// Navigating the browser to the URL "http://localhost:4200/deck-list".
		driver.get("http://localhost:4200/customer/deck-list");

		// Creating an instance of the WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(DeckListPage.viewDeckButton));
		deckListPage.clickViewDeckButton();
		Thread.sleep(1000);

		// Assertions and verifications:

		// Example 1: Check if the user is redirected to the view deck page
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/customer/view-deck/49"));
		assertEquals("http://localhost:4200/customer/view-deck/49", driver.getCurrentUrl());

		// Instructs the browser to navigate back to the previous page in the browsing
		// history.
		driver.navigate().back();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(DeckListPage.editDeckButton));
		deckListPage.clickEditDeckButton();
		Thread.sleep(1000);

		// Assertions and verifications:

		// Example 2: Check if the user is redirected to the edit deck page
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/customer/edit-deck/49"));
		assertEquals("http://localhost:4200/customer/edit-deck/49", driver.getCurrentUrl());

		// Instructs the browser to navigate back to the previous page in the browsing
		// history.
		driver.navigate().back();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(DeckListPage.deleteDeckButton));
		deckListPage.clickDeleteDeckButton();
		Thread.sleep(1000);

		// Switch to the alert
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		// Click the "OK" button on the alert
		alert.accept();
		Thread.sleep(1000);

	}

	@AfterSuite
	public void tearDown() {
		try {
			System.out.println("Tearing down the test");
			if (driver != null) {
				System.out.println("Closing the WebDriver");
				driver.quit();
			}
		} catch (Exception e) {
			// Log or print the exception for debugging purposes
			e.printStackTrace();
		}
	}
}
