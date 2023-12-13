package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spark.millhouse.selenium.pages.CreateDeckPage;
import com.spark.millhouse.utils.DriverManager;

public class CreateDeckTest {
    WebDriver driver;
    CreateDeckPage createDeckPage;

    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        createDeckPage = new CreateDeckPage(driver);
    }

    @Test
    public void testCreateDeck() {
    	// Navigating the browser to the URL "http://localhost:4200/create-deck".
        driver.get("http://localhost:4200/create-deck");
        
        // Creating an instance of the WebDriverWait class
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.deckTitleField));
        createDeckPage.enterDeckTitle("Deck1");

        wait.until(ExpectedConditions.elementToBeClickable(CreateDeckPage.addCardButton));
        createDeckPage.clickAddCardButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.questionField));
        createDeckPage.enterQuestion("Question1");

        wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.answerField));
        createDeckPage.enterAnswer("Answer1");

        wait.until(ExpectedConditions.elementToBeClickable(CreateDeckPage.createDeckButton));
        createDeckPage.clickCreateDeckButton();
        
        // Assertions and verifications:

        // Example 1: Check if the user is redirected to the deck list page after successful deck creation
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/deck-list"));
        assertEquals("http://localhost:4200/deck-list", driver.getCurrentUrl());

        // Example 2: Verify that the deck title is displayed on the deck list page
        String deckTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Deck1']"))).getText();
        assertEquals("Deck1", deckTitle);
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
