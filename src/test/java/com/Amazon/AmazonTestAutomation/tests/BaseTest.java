package com.Amazon.AmazonTestAutomation.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.Amazon.AmazonTestAutomation.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest
{
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp(Method method)
    {
        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());


    }
}
