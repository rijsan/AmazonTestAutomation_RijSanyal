package com.Amazon.AmazonTestAutomation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager
{
    private static ExtentReports extent;

    public static ExtentReports getInstance()
    {
        if(extent==null)
            createInstance("src/test/resources/reports/ExtentReport/extent.html");
        return extent;
    }
    public static ExtentReports createInstance(String fileName)
    {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Report for Amazon.in");
        sparkReporter.config().setReportName("TEST REPORT");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        return extent;
    }
}
