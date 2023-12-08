package com.spark.millhouse.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {
        // private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            // Initialize the WebDriver based on configuration
            String browser = System.getProperty("browser", "chrome");
            driver = createDriver(browser);
        }
        return driver;
    }

    private synchronized static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:/Users/millh/Software/chromedriver.exe");
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            //driver = null;
        }
    }
}
