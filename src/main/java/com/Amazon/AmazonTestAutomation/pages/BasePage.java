package com.Amazon.AmazonTestAutomation.pages;

import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage
{
    public WebDriver driver;
    public WebDriverWait wait;
    public FluentWait f_wait;
    public static LoggerUtil logger;


    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.f_wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(4));
        PageFactory.initElements(driver,this);
        logger = new LoggerUtil();
    }

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[@id='nav-cart']")
    private WebElement navigationToCart;

    @FindBy(xpath = "//span[@id='productTitle']")
    private WebElement productTitle;

    @FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
    private WebElement LightningDealAddToCartButton;

    //Method to Click Add to Cart button
    public void click_AddToCartButton() {
        try {
            addToCartButton.click();
        }
        catch (Exception e)
        {
            LightningDealAddToCartButton.click();
        }
        logger.info("Add To Cart Button is Clicked");
    }

    //Method to Click Cart button
    public void openCartLink() {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}
        navigationToCart.click();
        logger.info("Navigation to the Cart screen is clicked");
    }

    //Method to return Product Title Text
    public String getProductTitleText() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }

    //Method to compare and match Product title
    public boolean matchProductDetails(String cartTitle, String productTitle)
    {
        cartTitle = (cartTitle.split("\n")[0]);
        cartTitle = cartTitle.substring(0,cartTitle.length()-2);
        if(productTitle.contains(cartTitle))
            return true;
        else
            return false;
    }

    //Method to check if item is present in the cart with help of the title of item
    public boolean validateItemsInCart(String itemTitle, List<String> cartItemTitles)
    {
        for(String title : cartItemTitles)
        {
            int length = itemTitle.length();
            if(title.substring(0,length).equalsIgnoreCase(itemTitle))
                return true;
            else
                continue;
        }
        return false;
    }
}
