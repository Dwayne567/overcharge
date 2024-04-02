package com.spark.millhouse.basetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.spark.millhouse.reports.ExtentManager;
import com.spark.millhouse.utils.DriverManager;

public class BaseTest {
	
    public static Properties prop;
    private static WebDriver driver;

    @BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
    public void loadConfig() throws IOException {
        // Reports
        ExtentManager.setExtent();
        // Logging
        DOMConfigurator.configure("log4j.xml");
        // Properties
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\Configuration\\config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void launchApp(String browserName, String driverPath) {
    	//Get the WebDriver instance
        driver = DriverManager.getDriver(browserName, driverPath);
        //Maximize the screen
        driver.manage().window().maximize();
        //Delete all the cookies
        driver.manage().deleteAllCookies();
        //Page load timeouts
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        //Launching the URL
        driver.get(prop.getProperty("url"));
        //Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
    }

    @AfterSuite(groups = { "Smoke", "Regression","Sanity" })
    public void afterSuite() {
        DriverManager.quitDriver();
        ExtentManager.endReport();
    }
}