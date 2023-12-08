package com.spark.millhouse.selenium.test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	public void testViewDeck() throws InterruptedException {
		driver.get("http://localhost:4200/view-deck/1");
		Thread.sleep(2000);

		viewDeckPage.clickFlashCard();
		Thread.sleep(2000);

		String cardAnswer = driver.findElement(By.xpath("//*[text()='Answer1']")).getText();
		assertEquals("Answer1", cardAnswer);
		Thread.sleep(2000);

		viewDeckPage.clickFlashCard();
		Thread.sleep(2000);

		String cardQuestion = driver.findElement(By.xpath("//*[text()='Question1']")).getText();
		assertEquals("Question1", cardQuestion);
		Thread.sleep(2000);

	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		driver.quit();
	}
}
