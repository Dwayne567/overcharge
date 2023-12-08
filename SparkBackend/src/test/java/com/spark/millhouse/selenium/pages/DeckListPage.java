package com.spark.millhouse.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeckListPage {
	private WebDriver driver;

	public DeckListPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By viewDeckButton = By.id("viewDeckButton");
	By editDeckButton = By.id("editDeckButton");
	By deleteDeckButton = By.id("deleteDeckButton");
	
	public void clickViewDeckButton() {
		driver.findElement(viewDeckButton).click();
	}
	public void clickEditDeckButton() {
		driver.findElement(editDeckButton).click();
	}
	public void clickDeleteDeckButton() {
		driver.findElement(deleteDeckButton).click();
	}
}
