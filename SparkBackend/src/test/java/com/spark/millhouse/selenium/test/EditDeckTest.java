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
	public void testCreateDeck() {
		driver.get("http://localhost:4200/edit-deck/1");

		// Wait for the deck title element to be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement deckTitleInput = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.deckTitleField));

		// Clear the deck title
		deckTitleInput.clear();

		// Enter the new deck title
		deckTitleInput.sendKeys("NewDeck");

		// Click the remove card button
		WebElement removeCardButton = wait
				.until(ExpectedConditions.elementToBeClickable(EditDeckPage.removeCardButton));
		removeCardButton.click();

		// Click the add card button
		WebElement addCardButton = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.addCardButton));
		addCardButton.click();

		// Enter the new question
		WebElement questionInput = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.questionField));
		questionInput.sendKeys("NewQuestion");

		// Enter the new answer
		WebElement answerInput = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.answerField));
		answerInput.sendKeys("NewAnswer");

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

		// Example 2: Verify that the deck title is displayed on the deck list page
		WebElement deckTitleElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='NewDeck']")));
		String deckTitle = deckTitleElement.getText();
		assertEquals("NewDeck", deckTitle);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
