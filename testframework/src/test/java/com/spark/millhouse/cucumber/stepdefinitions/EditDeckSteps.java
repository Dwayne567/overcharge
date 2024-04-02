package com.spark.millhouse.cucumber.stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.spark.millhouse.cucumber.test.EditDeckRunner;
import com.spark.millhouse.selenium.pages.EditDeckPage;

public class EditDeckSteps {

    public static WebDriver driver = EditDeckRunner.driver;
    public static EditDeckPage editDeckPage = EditDeckRunner.editDeckPage;
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the edit deck page")
    public void iAmOnTheEditDeckPage() {
        driver.get("http://localhost:4200/edit-deck/4");
    }

    @When("I clear old deck title")
    public void iClearOldDeckTitle() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.deckTitleField)).clear();
    }

    @And("I enter a new deck title")
    public void iEnterMyDeckTitle() {
		editDeckPage.enterDeckTitle("NewDeck");
    }

    @And("I remove the old card")
    public void iClickTheRemoveCardButton() {
		wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.removeCardButton));
		editDeckPage.clickRemoveCardButton();
    }
    
    @And("I add a new card")
    public void iClickTheAddCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.addCardButton));
		editDeckPage.clickAddCardButton();
    }
    
    @And("I enter a new question and answer")
    public void iEnterMyQuestionAndAnswer() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.questionField));
		editDeckPage.enterQuestion("NewQuestion");
		
        wait.until(ExpectedConditions.visibilityOfElementLocated(EditDeckPage.answerField));
        editDeckPage.enterAnswer("NewAnswer");
    }

    @And("I click the update deck button")
    public void iClickTheUpdateDeckButton() {
		// Click the update deck button
		WebElement updateDeckButton = wait
				.until(ExpectedConditions.elementToBeClickable(EditDeckPage.updateDeckButton));
		// Scroll the element into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateDeckButton);
		updateDeckButton.click();
    }

    // Assertions and verifications:
    @Then("I should be redirected to the deck list page")
    public void iShouldBeRedirectedToDeckListPage() {
		wait.until(ExpectedConditions.urlToBe("http://localhost:4200/deck-list"));
        assertEquals("http://localhost:4200/deck-list", driver.getCurrentUrl());
    }

    @Then("My new deck should be displayed")
    public void myNewDeckShouldBeDisplayed() throws InterruptedException {
        String deckTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='NewDeck']"))).getText();
        assertEquals("NewDeck", deckTitle);
        Thread.sleep(5000);
    }
}


