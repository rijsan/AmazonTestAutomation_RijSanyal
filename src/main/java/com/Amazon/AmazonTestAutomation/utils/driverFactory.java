package com.Amazon.AmazonTestAutomation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class driverFactory
{
    private static WebDriver driver;

    public static WebDriver getDriver(String browser)
    {
        if(driver==null)
        {
            switch(browser.toLowerCase())
            {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                    driver=new FirefoxDriver();
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
    }
}
