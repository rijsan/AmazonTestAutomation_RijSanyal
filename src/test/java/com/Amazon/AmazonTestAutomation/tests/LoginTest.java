package com.Amazon.AmazonTestAutomation.tests;

import com.Amazon.AmazonTestAutomation.pages.AddressPage;
import org.testng.annotations.Test;
import com.Amazon.AmazonTestAutomation.pages.HomePage;

public class LoginTest extends BaseTest
{

    @Test
    public void Validate_Amazon_AddAddress() throws InterruptedException
    {
        /*
        Reading Credentials from Excel File
        All Address Details is saved in excel
        */
        excelReader.selectSheet("Account Details");
        String fullName = excelReader.getData("FULL NAME", "ADDRESS");
        String mobileNo = excelReader.getData("MOBILE NO", "ADDRESS");
        String pincode = excelReader.getData("PINCODE", "ADDRESS");
        String houseDetails = excelReader.getData("HOUSE DETAILS", "ADDRESS");
        String areaDetails = excelReader.getData("AREA DETAILS", "ADDRESS");
        String landmark = excelReader.getData("LANDMARK", "ADDRESS");
        String town = excelReader.getData("TOWN", "ADDRESS");
        String state = excelReader.getData("STATE", "ADDRESS");



        LoginToAmazon();
        HomePage homePage = new HomePage(driver);
        homePage.NavigateTo_YourAddresses();

        AddressPage addressPage = new AddressPage(driver);
        addressPage.Click_AddNewAddressButton();
        addressPage.Validate_AddAddressPage_Displayed();
        addressPage.Enter_FullName(fullName);
        addressPage.Enter_MobileNo(mobileNo);
        addressPage.Enter_Pincode(pincode);
        addressPage.Enter_HouseDetails(houseDetails);
        addressPage.Enter_AreaDetails(areaDetails);
        addressPage.Enter_Landmark(landmark);
        addressPage.Enter_Town(town);
        addressPage.Enter_State(state);

        addressPage.ClickSubmit_AddAddressButton();

        assert true:"Passed";
    }
}
