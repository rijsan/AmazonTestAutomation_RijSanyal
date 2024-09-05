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

    @BeforeTest
    public void setUp() throws InterruptedException {

        final String propertiesFilePath = "src/main/java/com/Amazon/AmazonTestAutomation/config/config.properties";
        Properties properties = new Properties();
        String browser = null;
        String URL = null;
        try
        {
            properties.load(new FileInputStream(propertiesFilePath));
            browser = properties.getProperty("browser");
            URL = properties.getProperty("AmazonURL");
            excelReader = new ExcelReader(properties.getProperty("ExcelFilePath"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        extent = ExtentManager.getInstance();
        //test = extent.createTest(method.getName());
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

        homePage.Click_AccountNav();
        homePage.Enter_EmailID(email); // Entering Email ID
        homePage.Click_Continue();
        homePage.Enter_Password(password); // Entering Password
        homePage.Click_Submit();
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    @AfterTest
    public void tearDown()
    {
        // Quit and clean up web driver instance
        DriverFactory.quitDriver();
    }
}
