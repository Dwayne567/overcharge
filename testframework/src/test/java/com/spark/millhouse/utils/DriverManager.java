package com.spark.millhouse.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {
        // private constructor to prevent instantiation
    }

    public static WebDriver getDriver(String browserName, String driverPath) {
        if (driver == null) {
            switch (browserName.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", driverPath);
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", driverPath);
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", driverPath);
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }
        }
        return driver;
    }
    
    public static WebDriver getChromeDriver() {
        if (driver == null) {
            String driverPath = "C:/Users/millh/Software/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
