package com.Amazon.AmazonTestAutomation.tests;

import com.Amazon.AmazonTestAutomation.listeners.TestListener;
import com.Amazon.AmazonTestAutomation.pages.*;
import com.Amazon.AmazonTestAutomation.utils.ScreenshotUtil;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

@Listeners(TestListener.class)
public class TestCases extends BaseTest
{
    @Test(priority = 1)
    public void Validate_OpenCategory() throws InterruptedException
    {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        //Initialising objects for different pages
        ItemPage itemPage = new ItemPage(driver);

        //Creating Extent Test
        test = extent.createTest(methodName);

        try
        {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in to Amazon.in");
            //Selecting user defined section
            itemPage.navigateTo_UserDefined_Section(helper.getSectionNameFromExcel());
            test.log(Status.INFO,"Navigated to User defined section");
            Assert.assertTrue(itemPage.verifyNavigation_To_UserDefined_Section(helper.getSectionNameFromExcel()));

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.PASS,"Test Case Passed ");
            test.addScreenCaptureFromPath(path);
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.FAIL,"Test Case Failed ");
            test.addScreenCaptureFromPath(path);
            Assert.fail("Test Case Failed due to exception: "+e.getMessage());
        }
    }
    @Test(priority = 2)
    public void Validate_AddToCartFeature() throws InterruptedException
    {
        //Storing method name
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        //Initialising objects for different pages
        BasePage basePage = new BasePage(driver);
        ItemPage itemPage = new ItemPage(driver);
        CartPage cartPage = new CartPage(driver);

        //Creating Extent Test
        test = extent.createTest(methodName);

        //Reading User Defined Input from Excel File
        List<String> listOfItems = new ArrayList<String>();

        try {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in with Amazon account");

            int count = Integer.parseInt(helper.getCountOfSearchDataFromExcel());

            for(int i=1;i<=count;i++)
            {
                String itemTitle = itemPage.addItemToCart(helper.getProductNameFromExcel(i),helper.getSectionNameFromExcel(i)).substring(0,30);
                listOfItems.add(itemTitle);
                test.log(Status.INFO,"Item No. " + i + " added to Cart");
            }
            // Extracting all Cart Item Titles from Cart Page
            List<String> cartItems = cartPage.getListOfAddedItemsInCart();

            for(int i=1;i<=count;i++)
            {
                Assert.assertTrue(basePage.validateItemsInCart(listOfItems.get(i-1),cartItems));
                test.log(Status.INFO,"Item No. " + i + " is displayed on the Cart Page");
            }

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.FAIL,"Test Case Failed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Assert.fail("Test Case Failed due to exception: "+e.getMessage());
        }
    }
    @Test(priority = 3)
    public void Validate_SearchAndFilter() throws InterruptedException
    {
        //Storing method name
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        //Initialising objects for different pages
        ItemPage itemPage = new ItemPage(driver);

        //creating Extent Test for the test
        test = extent.createTest(methodName);

        try {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in with Amazon account");

            //Searching with User Given data and checking if results are matching with it
            itemPage.searchItem(helper.getSearchDataFromExcel());
            test.log(Status.INFO,"Item is searched with user input data");
            boolean checkSearch = itemPage.validateProductsDisplayed(helper.getSearchDataFromExcel());
            Assert.assertTrue(checkSearch);
            test.log(Status.INFO,"Search result is correct");

            //Filtering with the second data on the list of filters and checking if results are matching with it
            String filterName = itemPage.selectFilter();
            test.log(Status.INFO,"Second Filter from the list of filters is selected");
            boolean checkFilter = itemPage.validateProductsDisplayed(filterName);
            Assert.assertTrue(checkFilter);
            test.log(Status.INFO,"Filter result is correct");

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.FAIL,"Test Case Failed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Assert.fail("Test Case Failed due to exception: "+e.getMessage());
        }
    }
    @Test(priority = 4)
    public void Validate_Amazon_AddAddress() throws InterruptedException
    {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        //Initialising objects for different pages
        HomePage homePage = new HomePage(driver);
        AddressPage addressPage = new AddressPage(driver);

        //Creating Extent Test
        test = extent.createTest(methodName);

        try
        {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in to Amazon.in");

            homePage.navigateTo_YourAddresses();
            test.log(Status.INFO,"Navigated to Address page ");

            //Entering User Input Data from Excel to all the fields of Add Address Page
            addressPage.click_AddNewAddressButton();
            addressPage.validate_AddAddressPage_Displayed();
            addressPage.enter_FullName(helper.getFullNameFromExcel());
            addressPage.enter_MobileNo(helper.getMobileNoFromExcel());
            addressPage.enter_Pincode(helper.getPincodeFromExcel());
            addressPage.enter_HouseDetails(helper.getHouseDetailsFromExcel());
            addressPage.enter_AreaDetails(helper.getAreaDetailsFromExcel());
            addressPage.enter_Landmark(helper.getLandmarkDetailsFromExcel());
            addressPage.enter_Town(helper.getTownNameFromExcel());
            addressPage.enter_State(helper.getStateNameFromExcel());
            test.log(Status.INFO,"All Details Entered for adding new address");

            addressPage.clickSubmit_AddAddressButton();
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Assert.assertTrue(addressPage.validate_AddedAddress(helper.getFullNameFromExcel()));
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.FAIL,"Test Case Failed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Assert.fail("Test Case Failed due to exception: "+e.getMessage());
        }
    }

    /*
    Find below the steps covered in the following Test Case
        1.	Login to ecommerce application.
        2.	Go to Best sellers section of top panel.
        3.	Scroll down to best sellers in Bags, Wallets & Luggage section.
        4.	Select the item from #8 rank seller.
        5.	Add the item from #8 rank seller to cart and verify the item added
        in cart is the right one selected from selection page
        All the inputs are User Defined in the Test
    */
    @Test(priority = 5)
    public void Validate_BestSellerPage() throws InterruptedException
    {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        //Initialising objects for different pages
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        BestSellerPage bestSeller = new BestSellerPage(driver);
        BasePage basePage = new BasePage(driver);

        //Creating Extent Test
        test = extent.createTest(methodName);

        //Try block to handle exceptions
        try
        {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in to Amazon.in");

            //Click Best Sellers link on HomePage
            homePage.clickBestSellers();
            test.log(Status.INFO,"Landed on Best Seller page");

            //Click BestSeller Category whereas category is taken from user
            bestSeller.click_BestSeller_Category(helper.getBestSellerCategoryFromExcel());
            test.log(Status.INFO,"Clicked "+ helper.getBestSellerCategoryFromExcel() +" on Best Seller Page");

            //Click Ranked Product whereas rank is taken from user
            bestSeller.click_Specific_RankedProduct(helper.getRankFromExcel());
            test.log(Status.INFO,"Clicked Rank No." + helper.getRankFromExcel() + " Product on "
                    + helper.getBestSellerCategoryFromExcel() +" page");

            //Storing product Title for the selected product
            String productTitle = bestSeller.getProductTitleText().substring(0,30);

            //Click Add to Cart Button on the selected product page
            bestSeller.click_AddToCartButton();
            test.log(Status.INFO,"Click Add to cart button on Product page");

            //Click Cart link from the top Nav bar
            bestSeller.openCartLink();
            test.log(Status.INFO,"Clicked Cart link from top Nav bar");

            // Extracting all Cart Item Titles from Cart Page
            List<String> cartItems = cartPage.getListOfAddedItemsInCart();

            //Validate Item Title
            Assert.assertTrue(basePage.validateItemsInCart(productTitle,cartItems));

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

            //Logout from Amazon account
            homePage.click_Logout();
            test.log(Status.INFO,"Logged Out from Amazon account");
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot();
            test.log(Status.FAIL,"Test Case Failed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Assert.fail("Test Case Failed due to exception: "+e.getMessage());
        }
    }
}
