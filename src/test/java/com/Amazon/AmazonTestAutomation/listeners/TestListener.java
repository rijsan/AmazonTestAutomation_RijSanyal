package com.Amazon.AmazonTestAutomation.listeners;
import com.Amazon.AmazonTestAutomation.tests.BaseTest;
import com.Amazon.AmazonTestAutomation.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Log the failure
        Reporter.log("Test Failed: " + result.getName());
        // Get the driver from the test class
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        // Take screenshot
        ScreenshotUtil.takeScreenshot();
    }

    // Other methods from ITestListener can be overridden as needed
    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}

