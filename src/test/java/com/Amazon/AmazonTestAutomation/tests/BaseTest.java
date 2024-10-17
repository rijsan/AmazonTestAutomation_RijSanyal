package com.Amazon.AmazonTestAutomation.tests;

import com.Amazon.AmazonTestAutomation.config.Config;
import com.Amazon.AmazonTestAutomation.pages.HomePage;
import com.Amazon.AmazonTestAutomation.utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest
{
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    ExcelHelper helper;

    @BeforeMethod
//    @Parameters("browser")
    public void setUp() throws InterruptedException, IOException {
        String browser="chrome";
        final String propertiesFilePath = "src/main/java/com/Amazon/AmazonTestAutomation/config/config.properties";
        Properties properties = new Properties();
        //String URL = null;
        try
        {
            properties.load(new FileInputStream(propertiesFilePath));
            //URL = properties.getProperty("AmazonURL");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        helper = new ExcelHelper();
        extent = ExtentManager.getInstance();
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.navigate().to(Config.AmazonURL);
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver,Config.ScreenshotFolderPath);
    }

    public void LoginToAmazon()
    {
        //Steps to login to Amazon.in account
        HomePage homePage = new HomePage(driver);

        homePage.click_AccountNav();
        homePage.enter_EmailID(helper.getAccountEmailFromExcel());                      // Entering Email ID
        homePage.click_Continue();
        homePage.enter_Password(helper.getAccountPasswordFromExcel());                  // Entering Password
        homePage.click_Submit();                                                        // Entering Submit button
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    @AfterMethod
    public void tearDown()
    {
        // Quit and clean up web driver instance
        DriverFactory.quitDriver();
        extent.flush();
    }
}
