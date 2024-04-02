package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spark.millhouse.reports.ExtentListener;
import com.spark.millhouse.reports.ExtentManager;
import com.spark.millhouse.selenium.pages.CreateDeckPage;
import com.spark.millhouse.selenium.pages.LoginPage;
import com.spark.millhouse.utils.DriverManager;

@Listeners(ExtentListener.class)
public class CreateDeckTest {
	WebDriver driver;
	LoginPage loginPage;
	CreateDeckPage createDeckPage;

	@BeforeSuite
	public void setUp() {
		try {
			ExtentManager.setExtent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = DriverManager.getDriver("chrome", "C:/Users/millh/Software/chromedriver.exe");
		loginPage = new LoginPage(driver);
		createDeckPage = new CreateDeckPage(driver);
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
	public void testCreateDeck() throws InterruptedException {
		// Navigating the browser to the URL "http://localhost:4200/create-deck".
		driver.get("http://localhost:4200/customer/create-deck");

		// Creating an instance of the WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.deckTitleField));
		createDeckPage.enterDeckTitle("Deck1");
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(CreateDeckPage.addCardButton));
		createDeckPage.clickAddCardButton();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.questionField));
		createDeckPage.enterQuestion("Question1");
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.answerField));
		createDeckPage.enterAnswer("Answer1");
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(CreateDeckPage.createDeckButton));
		createDeckPage.clickCreateDeckButton();
		Thread.sleep(1000);

		// Assertions and verifications:

		// Example 1: Check if the user is redirected to the deck list page after
		// successful deck creation
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/customer/deck-list"));
		assertEquals("http://localhost:4200/customer/deck-list", driver.getCurrentUrl());

		// Example 2: Verify that the deck title is displayed on the deck list page
		String deckTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Deck1']")))
				.getText();
		assertEquals("Deck1", deckTitle);
	}

	@AfterSuite
	public void tearDown() {
		ExtentManager.endReport();
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
