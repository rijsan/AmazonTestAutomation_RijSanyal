package com.Amazon.AmazonTestAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage
{
    //Locators on Cart Page
    public CartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//div[@class='sc-list-item-content']")
    private WebElement cartItem;
    @FindBy(xpath="//span[contains(@class,'product-title')]")
    private List<WebElement> multipleCartItems;
    @FindBy(xpath="//input[contains(@name,'submit.delete')]")
    private List<WebElement> DeleteButtons;
    @FindBy(xpath="(//input[contains(@name,'submit.delete')])[1]")
    private WebElement DeleteButton;

    public String get_CartItem_TitleText() {
        wait.until(ExpectedConditions.visibilityOf(cartItem));
        logger.info("Title of Products in Cart is fetched");
        return cartItem.getText();
    }
    public void clear_Cart()
    {
        openCartLink();
        wait.withTimeout(Duration.ofSeconds(5));
        for (WebElement delete : DeleteButtons)
        {
            wait.until(ExpectedConditions.elementToBeClickable(DeleteButton));
            DeleteButton.click();
        }
        logger.info("All existing products in the cart is cleared");
    }
    public List<String> getListOfAddedItemsInCart() throws InterruptedException {
        List<String> listOfTitle = new ArrayList<>();

        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        openCartLink();
        wait.withTimeout(Duration.ofSeconds(3));
        for(WebElement titleText : multipleCartItems)
        {
            wait.until(ExpectedConditions.visibilityOf(titleText));
            listOfTitle.add(titleText.getText());
        }
        logger.info("List of Products in Cart is fetched");
        return listOfTitle;
    }
}
