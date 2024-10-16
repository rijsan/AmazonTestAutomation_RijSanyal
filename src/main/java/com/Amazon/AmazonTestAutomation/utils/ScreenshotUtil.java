package com.Amazon.AmazonTestAutomation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.util.Date;

public class ScreenshotUtil {
    private static WebDriver driver;
    private static String Screenshot_DIR = null;

    public ScreenshotUtil(WebDriver driver, String path)
    {
        Screenshot_DIR = path;
        ScreenshotUtil.driver = driver;
    }

    /*
    * Take a screenshot of Current browser Window and save it to screenshots directory
    *
    * */

    public static String takeScreenshot()
    {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String filepath = Screenshot_DIR + screenshotname + "_" + timestamp + ".png";
        String filepath = Screenshot_DIR + "capture_" + timestamp + ".png";
        //Take the screenshot
        File srcFile = ((TakesScreenshot) ScreenshotUtil.driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(filepath);

        try
        {
            //Save screenshot to a dedicated folder for all Screenshots
            Files.copy(srcFile.toPath(),destFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved to: "+destFile.getAbsolutePath());
        }
        catch(IOException e)
        {
            System.err.println("Failed to save screenshot: "+e.getMessage());
        }
        return destFile.getAbsolutePath();
    }
}
