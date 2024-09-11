package com.Amazon.AmazonTestAutomation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory
{
    private static WebDriver driver;

    public static WebDriver getDriver(String browser)
    {
        if(driver==null)
        {
            switch(browser.toLowerCase())
            {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                    driver=new ChromeDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/msedgedriver");
                    driver=new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
        }
        return driver;
    }
    public static void quitDriver()
    {
        if(driver != null)
            driver.quit();
        driver=null;
        ExtentManager.getInstance().flush();
    }
}
