package com.spark.millhouse.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewDeckPage {
	private WebDriver driver;

	public ViewDeckPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public static By cardButton = By.id("flashcard");
	
	public void clickFlashCard() {
		driver.findElement(cardButton).click();
	}
}
