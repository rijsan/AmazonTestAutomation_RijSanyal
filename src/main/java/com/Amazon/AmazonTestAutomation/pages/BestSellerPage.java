package com.Amazon.AmazonTestAutomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BestSellerPage extends BasePage
{
    public BestSellerPage(WebDriver driver)
    {
        super(driver);
    }

    String BestSeller_Category_Xpath = "//div//h2[contains(text(),'%s')]//following::a[contains(text(),'See More')][1]";
    String RankedProduct_Xpath = "//span[@class='zg-bdg-text' and contains(text(),'#%s')]//following::div[@class='zg-grid-general-faceout'][1]";

    public void Click_SeeMore_For_BestSeller_Category(String category)
    {
        wait.withTimeout(Duration.ofSeconds(5));
        driver.findElement(By.xpath(String.format(BestSeller_Category_Xpath,category))).click();
        logger.info("Clicked See More button beside the required Best Seller Category");
    }
    public void Click_Specific_RankedProduct(String rank)
    {
        wait.withTimeout(Duration.ofSeconds(5));
        driver.findElement(By.xpath(String.format(RankedProduct_Xpath,rank))).click();
        logger.info("Clicked User Input Ranked Product from the choosen Best Seller Category");
    }
}
