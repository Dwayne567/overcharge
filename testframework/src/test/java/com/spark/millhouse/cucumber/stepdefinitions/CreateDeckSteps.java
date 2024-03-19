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

import com.spark.millhouse.cucumber.test.CreateDeckRunner;
import com.spark.millhouse.selenium.pages.CreateDeckPage;

public class CreateDeckSteps {

    public static WebDriver driver = CreateDeckRunner.driver;
    public static CreateDeckPage createDeckPage = CreateDeckRunner.createDeckPage;
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the create deck page")
    public void iAmOnTheCreateDeckPage() {
        driver.get("http://localhost:4200/create-deck");
    }

    @When("I enter my deck title")
    public void iEnterMyDeckTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.deckTitleField));
        createDeckPage.enterDeckTitle("Deck1");
    }

    @And("I click the add card button")
    public void iClickTheAddCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(CreateDeckPage.addCardButton));
        createDeckPage.clickAddCardButton();
    }

    @And("I enter my question and answer")
    public void iEnterMyQuestionAndAnswer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.questionField));
        createDeckPage.enterQuestion("Question1");

        wait.until(ExpectedConditions.visibilityOfElementLocated(CreateDeckPage.answerField));
        createDeckPage.enterAnswer("Answer1");
    }

    @And("I click the create deck button")
    public void iClickTheCreateDeckButton() {
        wait.until(ExpectedConditions.elementToBeClickable(CreateDeckPage.createDeckButton));
        createDeckPage.clickCreateDeckButton();
    }

    // Assertions and verifications:
    @Then("I should be directed to the deck list page")
    public void iShouldBeDirectedToDeckListPage() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/deck-list"));
        assertEquals("http://localhost:4200/deck-list", driver.getCurrentUrl());
    }

    @Then("my deck should be displayed")
    public void myDeckShouldBeDisplayed() throws InterruptedException {
    	String deckTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Deck1']"))).getText();
        assertEquals("Deck1", deckTitle);
        Thread.sleep(5000);
    }
}


