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
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/create-deck"));
    }

    @When("I enter my deck title")
    public void iEnterMyDeckTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deckTitle"))); // Replace with the actual ID
        createDeckPage.enterDeckTitle("Deck1");
    }

    @And("I click the add card button")
    public void iClickTheAddCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("addCardButton"))); // Replace with the actual ID
        createDeckPage.clickAddCardButton();
    }

    @And("I enter my question and answer")
    public void iEnterMyQuestionAndAnswer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("question0"))); // Replace with the actual ID
        createDeckPage.enterQuestion("Question1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("answer0"))); // Replace with the actual ID
        createDeckPage.enterAnswer("Answer1");
    }

    @And("I click the create deck button")
    public void iClickTheCreateDeckButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("createDeckButton"))); // Replace with the actual ID
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
        WebElement deckTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Deck1']")));
        String deckTitle = deckTitleElement.getText();
        assertEquals("Deck1", deckTitle);
        Thread.sleep(5000);
    }
}


