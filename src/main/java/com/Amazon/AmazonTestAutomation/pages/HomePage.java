package com.Amazon.AmazonTestAutomation.pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HomePage extends BasePage
{
    public HomePage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath="//a[@id='nav-link-accountList']")
    private WebElement accountNav;

    @FindBy(xpath="//input[@id='ap_email']")
    private WebElement credentialTextBox;

    @FindBy(xpath="//input[@id='continue']")
    private WebElement continueButton;

    @FindBy(xpath="//input[@id='ap_password']")
    private WebElement PasswordField;

    @FindBy(xpath="//input[@id='signInSubmit']")
    private WebElement SubmitButton;
    @FindBy(xpath="//a[@id='nav-item-signout']")
    private WebElement Logout;
    @FindBy(xpath="//h1[contains(text(),'Sign in')]")
    private WebElement SignInPageDisplayed;
    @FindBy(xpath="//a[contains(text(),'Best Sellers')]")
    private WebElement BestSellerLink;
    @FindBy(xpath="//h2[contains(text(),'Your Addresses')]")
    private WebElement YourAddressNav;

    //Hover over Account Navigation Link
    public void Hover_Over_AccountNav() {
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(accountNav));
        actions.moveToElement(accountNav).perform();
        logger.info("Hovered over the Account Options Link");
    }
    //Click on Account Navigation link to explore all account options
    public void Click_AccountNav() {
        wait.until(ExpectedConditions.elementToBeClickable(accountNav));
        accountNav.click();
        logger.info("Clicked the Account Options Link");
    }

    //Entering Email ID in Textbox
    public void Enter_EmailID(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(credentialTextBox));
        credentialTextBox.sendKeys(email);
        logger.info("Entered Email ID in Credential Text Box");
    }
    //Method to Click Continue Button
    public void Click_Continue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
        logger.info("Continue Button clicked");
    }
    //Entering Password in Textbox
    public void Enter_Password(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(PasswordField));
        PasswordField.sendKeys(password);
        logger.info("Entered Password in Password Text Box");
    }
    //Method to Click Submit Button
    public void Click_Submit() {
        wait.until(ExpectedConditions.elementToBeClickable(SubmitButton));
        SubmitButton.click();
        logger.info("Submit button clicked");
        logger.info("SUCCESSFULLY LOGGED IN TO AMAZON.IN ");
    }

    //Method to Select Your Addresses option from Account Settings page
    public void Click_YourAddresses() {
        wait.until(ExpectedConditions.elementToBeClickable(YourAddressNav));
        YourAddressNav.click();
    }

    //Logout Method to Click Logout Link
    public void Click_Logout() {
        Hover_Over_AccountNav();
        wait.withTimeout(Duration.ofSeconds(2));
        Logout.click();
        wait.until(ExpectedConditions.elementToBeClickable(SignInPageDisplayed));
        Assert.isTrue(SignInPageDisplayed.isDisplayed(),"Logout not successful");
        logger.info("SUCCESSFULLY LOGGED OUT FROM AMAZON.IN ");
    }

    //Logout Method to Click Logout Link
    public void NavigateTo_YourAddresses() {
        Click_AccountNav();
        Click_YourAddresses();
        logger.info("Your Addresses Option is Selected");
    }
    //Method to Click Best Sellers Option from Homepage
    public void clickBestSellers()
    {
        wait.until(ExpectedConditions.elementToBeClickable(BestSellerLink));
        BestSellerLink.click();
        logger.info("Best Seller page is opened ");
    }
}
