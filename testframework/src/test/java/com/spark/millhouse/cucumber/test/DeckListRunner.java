package com.spark.millhouse.cucumber.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.openqa.selenium.WebDriver;
import com.spark.millhouse.selenium.pages.DeckListPage;
import com.spark.millhouse.utils.DriverManager;

@CucumberOptions(
    features = "src/test/resources/DeckList.feature",
    glue = {"com.spark.millhouse.cucumber.stepdefinitions", "DeckListSteps"},
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class DeckListRunner extends AbstractTestNGCucumberTests {
	
    public static WebDriver driver;
    public static DeckListPage deckListPage;
	
    @BeforeSuite
    public static void setup() {
    	driver = DriverManager.getDriver("chrome", "C:/Users/millh/Software/chromedriver.exe");
        deckListPage = new DeckListPage(driver);
    }

    @AfterSuite
    public static void teardown() {
        driver.quit();
    }
}
