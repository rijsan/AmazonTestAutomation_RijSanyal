package com.Amazon.AmazonTestAutomation.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BestSellerPage extends BasePage
{
    public BestSellerPage(WebDriver driver)
    {
        super(driver);
    }

    String bestSeller_Category_Xpath = "//div//h2[contains(text(),'%s')]//following::a[contains(text(),'See More')][1]";
    String bestSeller_Category_AlternateXpath = "//a[contains(text(),'%s')]";
    String rankedProduct_Xpath = "//span[@class='zg-bdg-text' and contains(text(),'%s')]//following::div[@class='zg-grid-general-faceout'][1]";

    public void click_BestSeller_Category(String category)
    {
        try {
            WebElement seeMoreLink = driver.findElement(By.xpath(String.format(bestSeller_Category_Xpath,category)));
            wait.until(ExpectedConditions.elementToBeClickable(seeMoreLink));
            //Scroll down to the user input Category
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",seeMoreLink);
            //Wait for 2 seconds
            try{
                f_wait.until(driver -> false);}
            catch (Exception e) {}

            //Click on See more link
            seeMoreLink.click();
        }
        catch (Exception e)
        {
            WebElement categoryTitle = driver.findElement(By.xpath(String.format(bestSeller_Category_AlternateXpath,category)));
            wait.until(ExpectedConditions.elementToBeClickable(categoryTitle));
            categoryTitle.click();
        }
        logger.info("Clicked See More button beside the required Best Seller Category");
    }
    public void click_Specific_RankedProduct(String rank)
    {
        WebElement rankedProduct = driver.findElement(By.xpath(String.format(rankedProduct_Xpath,rank)));
        wait.until(ExpectedConditions.elementToBeClickable(rankedProduct));
        rankedProduct.click();
        logger.info("Clicked User Input Ranked Product from the chosen Best Seller Category");
    }
}
