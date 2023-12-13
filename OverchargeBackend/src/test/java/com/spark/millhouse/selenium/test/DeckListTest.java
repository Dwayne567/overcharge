package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spark.millhouse.selenium.pages.DeckListPage;
import com.spark.millhouse.utils.DriverManager;

public class DeckListTest {
    WebDriver driver;
    DeckListPage deckListPage;

    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        deckListPage = new DeckListPage(driver);
    }

    @Test
    public void testDeckList() {
    	// Navigating the browser to the URL "http://localhost:4200/deck-list".
        driver.get("http://localhost:4200/deck-list");
        
        // Creating an instance of the WebDriverWait class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(DeckListPage.viewDeckButton));
        deckListPage.clickViewDeckButton();
        
        // Assertions and verifications:
        
        // Example 1: Check if the user is redirected to the view deck page
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/view-deck/1"));
        assertEquals("http://localhost:4200/view-deck/1", driver.getCurrentUrl());

        // Instructs the browser to navigate back to the previous page in the browsing history.
        driver.navigate().back();

        wait.until(ExpectedConditions.elementToBeClickable(DeckListPage.editDeckButton));
        deckListPage.clickEditDeckButton();
        
        // Assertions and verifications:

        // Example 2: Check if the user is redirected to the edit deck page
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/edit-deck/1"));
        assertEquals("http://localhost:4200/edit-deck/1", driver.getCurrentUrl());

        // Instructs the browser to navigate back to the previous page in the browsing history.
        driver.navigate().back();

        wait.until(ExpectedConditions.elementToBeClickable(DeckListPage.deleteDeckButton));
        deckListPage.clickDeleteDeckButton();
        
        // Switch to the alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        // Click the "OK" button on the alert
        alert.accept();
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
