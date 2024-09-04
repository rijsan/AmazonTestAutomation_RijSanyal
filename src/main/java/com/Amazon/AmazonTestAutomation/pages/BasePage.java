package com.Amazon.AmazonTestAutomation.pages;

import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage
{
    public WebDriver driver;
    public WebDriverWait wait;
    public static LoggerUtil logger;


    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
        logger = new LoggerUtil();
    }

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    private WebElement AddToCartButton;

    @FindBy(xpath = "//a[@id='nav-cart']")
    private WebElement NavigationToCart;

    @FindBy(xpath = "//span[@id='productTitle']")
    private WebElement ProductTitle;


    public void Click_AddToCartButton() {
        AddToCartButton.click();
        logger.info("Add To Cart Button is Clicked");
    }
    public void OpenCartLink() {
        NavigationToCart.click();
        logger.info("Navigation to the Cart screen is clicked");
    }
    public String GetProductTitleText() {
        return ProductTitle.getText();
    }


}
