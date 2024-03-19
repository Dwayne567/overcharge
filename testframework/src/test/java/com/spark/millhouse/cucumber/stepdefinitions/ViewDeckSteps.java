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

import com.spark.millhouse.cucumber.test.ViewDeckRunner;
import com.spark.millhouse.selenium.pages.ViewDeckPage;

public class ViewDeckSteps {

    public static WebDriver driver = ViewDeckRunner.driver;
    public static ViewDeckPage viewDeckPage = ViewDeckRunner.viewDeckPage;
    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the view deck page")
    public void iAmOnTheViewDeckPage() {
        driver.get("http://localhost:4200/view-deck/4");
    }

    @When("I click on the flashcard")
    public void iClickOnTheFlashcard() {
        wait.until(ExpectedConditions.elementToBeClickable(ViewDeckPage.cardButton));
        viewDeckPage.clickFlashCard();
    }

    @Then("I should be able to see the answer")
    public void iShouldBeAbleToSeeTheAnswer() {
        String cardAnswer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Answer1']"))).getText();
        assertEquals("Answer1", cardAnswer);
    }

    @And("I click on the flashcard again")
    public void iClickOnTheFlashcardAgain() {
        wait.until(ExpectedConditions.elementToBeClickable(ViewDeckPage.cardButton));
        viewDeckPage.clickFlashCard();
    }

    @Then("I should be able to see the question")
    public void iShouldBeAbleToSeeTheQuestion() throws InterruptedException {
        String cardQuestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Question1']"))).getText();
        assertEquals("Question1", cardQuestion);
        Thread.sleep(5000);
    }
}

