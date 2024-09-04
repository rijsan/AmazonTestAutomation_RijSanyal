package com.Amazon.AmazonTestAutomation.pages;

import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage
{
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
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


    public void Hover_Over_AccountNav() {
        Actions actions = new Actions(driver);
        actions.moveToElement(accountNav).perform();
        LoggerUtil.info("Hovered over the Account Options Link");
    }
    //Click on Account Navigation link to explore all account options
    public void Click_AccountNav() {
        accountNav.click();
        LoggerUtil.info("Clicked the Account Options Link");
    }
    //Entering Email ID in Textbox
    public void Enter_EmailID(String email) {
        credentialTextBox.sendKeys(email);
        LoggerUtil.info("Entered Email ID in Credential Text Box");
    }
    //Method to Click Continue Button
    public void Click_Continue() {
        continueButton.click();
        LoggerUtil.info("Continue Button clicked");
    }
    //Entering Password in Textbox
    public void Enter_Password(String password) {
        wait.withTimeout(Duration.ofSeconds(5));
        PasswordField.sendKeys(password);
        LoggerUtil.info("Entered Password in Password Text Box");
    }
    //Method to Click Submit Button
    public void Click_Submit() {
        wait.withTimeout(Duration.ofSeconds(5));
        SubmitButton.click();
        LoggerUtil.info("Submit button clicked");
    }

    public void Click_YourAddresses() {
        wait.withTimeout(Duration.ofSeconds(5));
        YourAddressNav.click();
    }
    //Logout Method to Click Logout Link
    public void Click_Logout() {
        Hover_Over_AccountNav();
        Logout.click();
        wait.withTimeout(Duration.ofSeconds(5));
        Assert.isTrue(SignInPageDisplayed.isDisplayed(),"Logout not successful");
    }

    //Logout Method to Click Logout Link
    public void NavigateTo_YourAddresses() {
        Click_AccountNav();
        Click_YourAddresses();
        wait.withTimeout(Duration.ofSeconds(10));
    }

    public void clickBestSellers()
    {
        BestSellerLink.click();
    }
}
