package com.spark.millhouse.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeckListPage {
	private WebDriver driver;

	public DeckListPage(WebDriver driver) {
		this.driver = driver;
	}
	public static By addDeckButton = By.id("addDeckButton");
	public static By viewDeckButton = By.id("viewDeckButton");
	public static By editDeckButton = By.id("editDeckButton");
	public static By deleteDeckButton = By.id("deleteDeckButton");
	
	public void clickAddDeckButton() {
		driver.findElement(addDeckButton).click();
	}
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
