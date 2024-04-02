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
import com.spark.millhouse.selenium.pages.LoginPage;
import com.spark.millhouse.selenium.pages.ViewDeckPage;
import com.spark.millhouse.utils.DriverManager;

@Listeners(ExtentListener.class)
public class ViewDeckTest {
	WebDriver driver;
	LoginPage loginPage;
	ViewDeckPage viewDeckPage;

	@BeforeSuite
	public void setUp() {
		try {
			ExtentManager.setExtent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = DriverManager.getDriver("chrome", "C:/Users/millh/Software/chromedriver.exe");
		loginPage = new LoginPage(driver);
		viewDeckPage = new ViewDeckPage(driver);
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
	public void testViewDeck() throws InterruptedException {
		// Navigating the browser to the URL "http://localhost:4200/view-deck/4".
		driver.get("http://localhost:4200/customer/view-deck/34");

		// Creating an instance of the WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ViewDeckPage.cardButton));
		viewDeckPage.clickFlashCard();
		Thread.sleep(1000);

		// Assertions and verifications:
		String cardAnswer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Answer1']")))
				.getText();
		assertEquals("Answer1", cardAnswer);
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(ViewDeckPage.cardButton));
		viewDeckPage.clickFlashCard();
		Thread.sleep(1000);

		// Assertions and verifications:
		String cardQuestion = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Question1']"))).getText();
		assertEquals("Question1", cardQuestion);
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
