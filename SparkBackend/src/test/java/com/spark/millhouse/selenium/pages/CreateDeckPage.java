package com.spark.millhouse.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateDeckPage {
	private WebDriver driver;

	public CreateDeckPage(WebDriver driver) {
		this.driver = driver;
	}

	public static By deckTitleField = By.id("deckTitle");
	public static By addCardButton = By.id("addCardButton");
	public static By removeCardButton = By.id("removeCardButton");
	public static By questionField = By.id("question0");
	public static By answerField = By.id("answer0");
	public static By createDeckButton = By.id("createDeckButton");

	public void enterDeckTitle(String deckTitle) {
		driver.findElement(deckTitleField).sendKeys(deckTitle);
	}
	
	public void clickAddCardButton() {
		driver.findElement(addCardButton).click();
	}
	
	public void clickRemoveCardButton() {
		driver.findElement(removeCardButton).click();
	}

	public void enterQuestion(String question) {
		driver.findElement(questionField).sendKeys(question);
	}

	public void enterAnswer(String answer) {
		driver.findElement(answerField).sendKeys(answer);
	}
	
	public void clickCreateDeckButton() {
		driver.findElement(createDeckButton).click();
	}
}
