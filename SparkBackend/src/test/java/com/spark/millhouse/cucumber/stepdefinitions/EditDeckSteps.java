package com.spark.millhouse.cucumber.stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
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
        driver.get("http://localhost:4200/edit-deck/1");
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/edit-deck/1"));
    }

    @When("I clear old deck title")
    public void iClearOldDeckTitle() {
        WebElement deckTitleInput = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.deckTitleField));
        deckTitleInput.clear();
    }

    @And("I enter a new deck title")
    public void iEnterMyDeckTitle() {
        WebElement deckTitleInput = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.deckTitleField));
        deckTitleInput.sendKeys("NewDeck");
    }

    @And("I remove a card")
    public void iClickTheRemoveCardButton() {
        WebElement removeCardButton = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.removeCardButton));
        removeCardButton.click();
    }

    @And("I enter a new question and answer")
    public void iEnterMyQuestionAndAnswer() {
        WebElement questionInput = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.questionField));
        questionInput.sendKeys("NewQuestion");

        WebElement answerInput = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.answerField));
        answerInput.sendKeys("NewAnswer");
    }

    @And("I click the update deck button")
    public void iClickTheCreateDeckButton() {
        WebElement updateDeckButton = wait.until(ExpectedConditions.elementToBeClickable(EditDeckPage.updateDeckButton));
        updateDeckButton.click();
    }

    // Assertions and verifications:
    @Then("I should be redirected to the deck list page")
    public void iShouldBeRedirectedToDeckListPage() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/deck-list"));
    }

    @Then("my new deck should be displayed")
    public void myDeckShouldBeDisplayed() throws InterruptedException {
        WebElement deckTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='NewDeck']")));
        String deckTitle = deckTitleElement.getText();
        assertEquals("NewDeck", deckTitle);
        Thread.sleep(5000);
    }
}


