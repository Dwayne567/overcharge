package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	public void testCreateDeck() throws InterruptedException {
		driver.get("http://localhost:4200/create-deck");
		Thread.sleep(2000);
		createDeckPage.enterDeckTitle("Deck1");
		Thread.sleep(2000);
		createDeckPage.clickAddCardButton();
		Thread.sleep(2000);
		createDeckPage.enterQuestion("Question1");
		Thread.sleep(2000);
		createDeckPage.enterAnswer("Answer1");
		Thread.sleep(2000);
		createDeckPage.clickCreateDeckButton();
		Thread.sleep(2000);

		// Assertions and verifications:

		// Example 1: Check if the user is redirected to the deck list page after
		// successful deck creation
		assertEquals("http://localhost:4200/deck-list", driver.getCurrentUrl());

		// Example 2: Verify that the deck title is displayed on the deck list page
		String deckTitle = driver.findElement(By.xpath("//td[text()='Deck1']")).getText();
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
