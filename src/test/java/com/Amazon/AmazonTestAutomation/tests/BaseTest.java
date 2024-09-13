package com.Amazon.AmazonTestAutomation.tests;

import com.Amazon.AmazonTestAutomation.pages.HomePage;
import com.Amazon.AmazonTestAutomation.utils.DriverFactory;
import com.Amazon.AmazonTestAutomation.utils.ExcelReader;
import com.Amazon.AmazonTestAutomation.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.Amazon.AmazonTestAutomation.utils.ExtentManager;
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
    ExcelReader excelReader;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) throws InterruptedException {
        final String propertiesFilePath = "src/main/java/com/Amazon/AmazonTestAutomation/config/config.properties";
        Properties properties = new Properties();
        String URL = null;
        try
        {
            properties.load(new FileInputStream(propertiesFilePath));
            URL = properties.getProperty("AmazonURL");
            excelReader = new ExcelReader(properties.getProperty("ExcelFilePath"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        extent = ExtentManager.getInstance();
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver,properties.getProperty("ScreenshotFolderPath"));
    }

    public void LoginToAmazon()
    {
        /*
        Reading Credentials from Excel File
        Email ID and Password is saved in excel
        */
        excelReader.selectSheet("Account Details");
        String email = excelReader.getData("EMAIL ID", "CREDENTIALS");
        String password = excelReader.getData("PASSWORD", "CREDENTIALS");

        //Steps to login to Amazon.in account
        HomePage homePage = new HomePage(driver);

        homePage.click_AccountNav();
        homePage.enter_EmailID(email);                                      // Entering Email ID
        homePage.click_Continue();
        homePage.enter_Password(password);                                  // Entering Password
        homePage.click_Submit();                                            // Entering Submit button
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
