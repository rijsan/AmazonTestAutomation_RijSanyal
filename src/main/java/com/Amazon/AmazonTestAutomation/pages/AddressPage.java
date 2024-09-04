package com.Amazon.AmazonTestAutomation.pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;

public class AddressPage extends BasePage
{
    private WebDriver driver;
    private WebDriverWait wait;
    private static LoggerUtil logger;


    public AddressPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
        logger = new LoggerUtil();
    }
    @FindBy(xpath = "//div[@id='ya-myab-plus-address-icon']")
    private WebElement AddNewAddress;

    @FindBy(xpath = "//h2[contains(text(),'Add a new address')]")
    private WebElement AddNewAddressPageHeading;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']")
    private WebElement FullNameInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPhoneNumber']")
    private WebElement MobileNumberInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPostalCode']")
    private WebElement PincodeInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']")
    private WebElement HouseDetailsInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine2']")
    private WebElement AreaDetailsInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-landmark']")
    private WebElement LandmarkDetailsInput;

    @FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity']")
    private WebElement TownInput;

    @FindBy(xpath = "//span[@id='address-ui-widgets-enterAddressStateOrRegion']")
    private WebElement StateDropdown;
    String State_xpath = "//a[contains(text(),'%s')]";

    @FindBy(xpath = "//span[contains(text(),'Add address')]//preceding::input[1]")
    private WebElement AddAddressButton;

    public void Click_AddNewAddressButton() {
        AddNewAddress.click();
        logger.info("Add New Address Option Clicked");
    }
    //Method to check whether Add Address page is displayed or not
    public void Validate_AddAddressPage_Displayed()
    {
        Assert.isTrue(AddNewAddressPageHeading.isDisplayed(),"Add new Address Page not displayed");
        logger.info("Add Address Page is now displayed");
    }
    //Method to Enter full name
    public void Enter_FullName(String name)
    {
        FullNameInput.sendKeys(name);
        logger.info("Full Name is entered");
    }
    //Method to Enter Mobile Number
    public void Enter_MobileNo(String mobileNo)
    {
        MobileNumberInput.sendKeys(mobileNo);
        logger.info("Mobile Number is entered");
    }
    //Method to Enter Pincode value
    public void Enter_Pincode(String pincode)
    {
        PincodeInput.sendKeys(pincode);
        logger.info("Pincode is entered");
    }
    //Method to Enter House details value
    public void Enter_HouseDetails(String houseDetails)
    {
        HouseDetailsInput.sendKeys(houseDetails);
        logger.info("House Details is entered");
    }
    //Method to Enter Area details value
    public void Enter_AreaDetails(String areaDetails)
    {
        AreaDetailsInput.sendKeys(areaDetails);
        logger.info("Area Details is entered");
    }
    //Method to Enter Landmark value
    public void Enter_Landmark(String landmark)
    {
        LandmarkDetailsInput.sendKeys(landmark);
        logger.info("Landmark is entered");
    }
    //Method to Enter Town value
    public void Enter_Town(String town)
    {
        TownInput.sendKeys(town);
        logger.info("Town name is entered");
    }
    //Method to Enter State value
    public void Enter_State(String stateValue)
    {
        StateDropdown.click();
        driver.findElement(By.xpath(String.format(State_xpath,stateValue.toUpperCase()))).click();
        logger.info("State is selected from Dropdown");
    }
    //Method to click on Add address button
    public void ClickSubmit_AddAddressButton()
    {
        wait.withTimeout(Duration.ofSeconds(10));
        AddAddressButton.click();
        logger.info("Add Address Submit button selected");
        wait.withTimeout(Duration.ofSeconds(20));

    }

}
