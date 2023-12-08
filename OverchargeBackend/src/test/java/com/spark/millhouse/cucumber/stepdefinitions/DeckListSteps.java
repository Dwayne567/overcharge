package com.spark.millhouse.cucumber.stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.spark.millhouse.cucumber.test.DeckListRunner;
import com.spark.millhouse.selenium.pages.DeckListPage;

public class DeckListSteps {

    public static WebDriver driver = DeckListRunner.driver;
    public static DeckListPage deckListPage = DeckListRunner.deckListPage;

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the deck list page")
    public void iAmOnTheDeckListPage() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:4200/deck-list"));
        driver.get("http://localhost:4200/deck-list");
    }

    @When("I click the view deck button")
    public void iClickTheViewDeckButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("viewDeckButton"))).click();
    }

    @Then("I should be directed to the view deck page")
    public void iShouldBeDirectedToTheViewDeckPage() {
        assertEquals("http://localhost:4200/view-deck/1", driver.getCurrentUrl());
    }

    @And("I navigate back")
    public void iNavigateBack() {
        driver.navigate().back();
    }

    @And("I click the edit deck button")
    public void iClickTheEditDeckButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("editDeckButton"))).click();
    }

    @Then("I should be directed to the edit deck page")
    public void iShouldBeDirectedToTheEditDeckPage() {
        assertEquals("http://localhost:4200/edit-deck/1", driver.getCurrentUrl());
    }

    @And("I navigate back again")
    public void iNavigateBackAgain() {
        driver.navigate().back();
    }

    @And("I click the delete deck button")
    public void iClickTheDeleteButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteDeckButton"))).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(5000);
    }
}

