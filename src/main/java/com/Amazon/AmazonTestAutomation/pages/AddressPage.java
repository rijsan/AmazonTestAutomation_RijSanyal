package com.Amazon.AmazonTestAutomation.pages;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddressPage extends BasePage
{
    //constructor for Address page
    public AddressPage(WebDriver driver) {

        super(driver);
    }
    @FindBy(id = "ya-myab-plus-address-icon")
    private WebElement addNewAddress;
    @FindBy(xpath = "//h2[contains(text(),'Add a new address')]")
    private WebElement addNewAddressPageHeading;
    @FindBy(id = "address-ui-widgets-enterAddressFullName")
    private WebElement fullNameInput;
    @FindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
    private WebElement mobileNumberInput;
    @FindBy(id = "address-ui-widgets-enterAddressPostalCode")
    private WebElement pincodeInput;
    @FindBy(id = "address-ui-widgets-enterAddressLine1")
    private WebElement houseDetailsInput;
    @FindBy(id = "address-ui-widgets-enterAddressLine2")
    private WebElement areaDetailsInput;
    @FindBy(id = "address-ui-widgets-landmark")
    private WebElement landmarkDetailsInput;
    @FindBy(id = "address-ui-widgets-enterAddressCity")
    private WebElement townInput;
    @FindBy(id = "address-ui-widgets-enterAddressStateOrRegion")
    private WebElement stateDropdown;
    String state_xpath = "//a[contains(text(),'%s')]";
    String checkAddressName = "(//h5[contains(@id,'address') and contains(text(),'%s')])[1]";

    @FindBy(xpath = "//span[contains(text(),'Add address')]//preceding::input[1]")
    private WebElement addAddressButton;

    public void click_AddNewAddressButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewAddress));
        addNewAddress.click();
        logger.info("Add New Address Option Clicked");
    }
    //Method to check whether Add Address page is displayed or not
    public void validate_AddAddressPage_Displayed()
    {
        Assert.isTrue(addNewAddressPageHeading.isDisplayed(),"Add new Address Page not displayed");
        logger.info("Add Address Page is now displayed");
    }
    //Method to Enter full name
    public void enter_FullName(String name)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        fullNameInput.sendKeys(name);
        logger.info("Full Name is entered");
    }
    //Method to Enter Mobile Number
    public void enter_MobileNo(String mobileNo)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        mobileNumberInput.sendKeys(mobileNo);
        logger.info("Mobile Number is entered");
    }
    //Method to Enter Pincode value
    public void enter_Pincode(String pincode)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        pincodeInput.sendKeys(pincode);
        logger.info("Pincode is entered");
    }
    //Method to Enter House details value
    public void enter_HouseDetails(String houseDetails)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        houseDetailsInput.sendKeys(houseDetails);
        logger.info("House Details is entered");
    }
    //Method to Enter Area details value
    public void enter_AreaDetails(String areaDetails) throws InterruptedException {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        areaDetailsInput.sendKeys(areaDetails);
        logger.info("Area Details is entered");
    }
    //Method to Enter Landmark value
    public void enter_Landmark(String landmark)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        landmarkDetailsInput.sendKeys(landmark);
        logger.info("Landmark is entered");
    }
    //Method to Enter Town value
    public void enter_Town(String town)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        townInput.clear();
        townInput.sendKeys(town);
        logger.info("Town name is entered");
    }
    //Method to Enter State value
    public void enter_State(String stateValue)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}
        stateDropdown.click();
        driver.findElement(By.xpath(String.format(state_xpath,stateValue.toUpperCase()))).click();
        logger.info("State is selected from Dropdown");
    }
    //Method to click on Add address button
    public void clickSubmit_AddAddressButton()
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        addAddressButton.click();
        logger.info("Add Address Submit button selected");
    }

    public boolean validate_AddedAddress(String name)
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        if(driver.findElement(By.xpath(String.format(checkAddressName,name))).isDisplayed()) {
            logger.info("Address is added Successfully");
            return true;
        }
        else
            return false;
    }
}
