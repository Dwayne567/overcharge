package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spark.millhouse.selenium.pages.CreateDeckPage;
import com.spark.millhouse.selenium.pages.EditDeckPage;
import com.spark.millhouse.utils.DriverManager;

import java.time.Duration;

public class EditDeckTest {
	WebDriver driver;
	EditDeckPage editDeckPage;

	@BeforeSuite
	public void setUp() {
		driver = DriverManager.getDriver();
		editDeckPage = new EditDeckPage(driver);
	}

	@Test
	public void testEditDeck() {
    	// Navigating the browser to the URL "http://localhost:4200/edit-deck/4".
		driver.get("http://localhost:4200/edit-deck/4");
		
        // Creating an instance of the WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.deckTitleField)).clear();
		editDeckPage.enterDeckTitle("NewDeck");

		wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.removeCardButton));
		editDeckPage.clickRemoveCardButton();
		
        wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.addCardButton));
		editDeckPage.clickAddCardButton();

		wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.questionField));
		editDeckPage.enterQuestion("NewQuestion");
		
        wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.answerField));
        editDeckPage.enterAnswer("NewAnswer");

		// Click the update deck button
		WebElement updateDeckButton = wait
				.until(ExpectedConditions.elementToBeClickable(EditDeckPage.updateDeckButton));
		// Scroll the element into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateDeckButton);
		updateDeckButton.click();
		
		// Assertions and verifications:

		// Example 1: Check if the user is redirected to the deck list page after
		// successful deck creation
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/deck-list"));
        assertEquals("http://localhost:4200/deck-list", driver.getCurrentUrl());

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
