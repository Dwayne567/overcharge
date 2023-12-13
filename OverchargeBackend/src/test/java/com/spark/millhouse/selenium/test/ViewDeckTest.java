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

import com.spark.millhouse.selenium.pages.ViewDeckPage;
import com.spark.millhouse.utils.DriverManager;

public class ViewDeckTest {
    WebDriver driver;
    ViewDeckPage viewDeckPage;

    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        viewDeckPage = new ViewDeckPage(driver);
    }

    @Test
    public void testViewDeck() {
    	// Navigating the browser to the URL "http://localhost:4200/view-deck/4".
        driver.get("http://localhost:4200/view-deck/4");

        // Creating an instance of the WebDriverWait class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ViewDeckPage.cardButton));
        viewDeckPage.clickFlashCard();
        
        // Assertions and verifications:
        String cardAnswer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Answer1']"))).getText();
        assertEquals("Answer1", cardAnswer);

        wait.until(ExpectedConditions.elementToBeClickable(ViewDeckPage.cardButton));
        viewDeckPage.clickFlashCard();
        
        // Assertions and verifications:
        String cardQuestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Question1']"))).getText();
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
