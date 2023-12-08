package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;

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
	public void testDeckList() throws InterruptedException {
		driver.get("http://localhost:4200/deck-list");
		Thread.sleep(2000);
		
		deckListPage.clickViewDeckButton();
		Thread.sleep(2000);
		// Check if the user is redirected to the view deck page
		assertEquals("http://localhost:4200/view-deck/2", driver.getCurrentUrl());
		Thread.sleep(1000);
        driver.navigate().back();
		Thread.sleep(2000);

		deckListPage.clickEditDeckButton();
		Thread.sleep(2000);
		// Check if the user is redirected to the edit deck page
		assertEquals("http://localhost:4200/edit-deck/2", driver.getCurrentUrl());
		Thread.sleep(1000);
        driver.navigate().back();
		Thread.sleep(2000);

		deckListPage.clickDeleteDeckButton();
		Thread.sleep(2000);
        // Switch to the alert
        Alert alert = driver.switchTo().alert();
        // Click the "OK" button on the alert
        alert.accept();

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
