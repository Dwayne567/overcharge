package com.spark.millhouse.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.spark.millhouse.basetest.BaseTest;

public class TestUtil extends BaseTest {
    private static WebDriver driver;


    public void switchToFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }

}