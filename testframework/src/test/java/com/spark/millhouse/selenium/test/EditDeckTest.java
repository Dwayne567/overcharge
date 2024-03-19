package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spark.millhouse.reports.ExtentManager;
import com.spark.millhouse.selenium.pages.EditDeckPage;
import com.spark.millhouse.selenium.pages.LoginPage;
import com.spark.millhouse.utils.DriverManager;

import java.io.IOException;
import java.time.Duration;

public class EditDeckTest {
	WebDriver driver;
	LoginPage loginPage;	
	EditDeckPage editDeckPage;

	@BeforeSuite
	public void setUp() {
		try {
			ExtentManager.setExtent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = DriverManager.getDriver("chrome", "C:/Users/millh/Software/chromedriver.exe");
		loginPage = new LoginPage(driver);
		editDeckPage = new EditDeckPage(driver);
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
	public void testEditDeck() throws InterruptedException {
    	// Navigating the browser to the URL "http://localhost:4200/edit-deck/4".
		driver.get("http://localhost:4200/customer/edit-deck/34");
		
        // Creating an instance of the WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.deckTitleField)).clear();
		editDeckPage.enterDeckTitle("NewDeck");
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.removeCardButton));
		editDeckPage.clickRemoveCardButton();
		Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.addCardButton));
		editDeckPage.clickAddCardButton();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.questionField));
		editDeckPage.enterQuestion("NewQuestion");
		Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.answerField));
        editDeckPage.enterAnswer("NewAnswer");
		Thread.sleep(1000);

		// Click the update deck button
		WebElement updateDeckButton = wait
				.until(ExpectedConditions.elementToBeClickable(EditDeckPage.updateDeckButton));
		// Scroll the element into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateDeckButton);
		updateDeckButton.click();
		
		// Assertions and verifications:

		// Example 1: Check if the user is redirected to the deck list page after
		// successful deck creation
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/customer/deck-list"));
        assertEquals("http://localhost:4200/customer/deck-list", driver.getCurrentUrl());

        // Example 2: Verify that the deck title is displayed on the deck list page
        String deckTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='NewDeck']"))).getText();
        assertEquals("NewDeck", deckTitle);
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
