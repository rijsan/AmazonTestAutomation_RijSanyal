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

        //Reading User Defined Input from Excel File
        excelReader.selectSheet(methodName);
        String userDefined_Section = excelReader.getData("SECTION_NAME", "USERDATA");

        try
        {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in to Amazon.in");

            //Selecting user defined section
            itemPage.navigateTo_UserDefined_Section(userDefined_Section);
            test.log(Status.INFO,"Navigated to User defined section");
            Assert.assertTrue(itemPage.verifyNavigation_To_UserDefined_Section(userDefined_Section));

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
            test.log(Status.FAIL,"Test Case Failed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
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
        excelReader.selectSheet(methodName);
        String userDefined_Section_1 = excelReader.getData("SECTION_NAME", "USERDATA1");
        String item1 = excelReader.getData("PRODUCT_NAME", "USERDATA1");
        String userDefined_Section_2 = excelReader.getData("SECTION_NAME", "USERDATA2");
        String item2 = excelReader.getData("PRODUCT_NAME", "USERDATA2");
        String userDefined_Section_3 = excelReader.getData("SECTION_NAME", "USERDATA3");
        String item3 = excelReader.getData("PRODUCT_NAME", "USERDATA3");

        try {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in with Amazon account");

            //Method to clear all existing cart items
            cartPage.clear_Cart();
            test.log(Status.INFO,"Existing items in cart is removed");

            //Adding 1st Item to Cart
            String itemTitle1 = itemPage.addItemToCart(item1,userDefined_Section_1).substring(0,50);
            listOfItems.add(itemTitle1);
            test.log(Status.INFO,"Added First Item to Cart");

            //Adding 2nd Item to Cart
            String itemTitle2 = itemPage.addItemToCart(item2,userDefined_Section_2).substring(0,50);
            listOfItems.add(itemTitle2);
            test.log(Status.INFO,"Added Second Item to Cart");

            //Adding 3rd Item to Cart
            String itemTitle3 = itemPage.addItemToCart(item3,userDefined_Section_3).substring(0,50);
            listOfItems.add(itemTitle3.substring(0,50));
            test.log(Status.INFO,"Added Third Item to Cart");

            // Extracting all Cart Item Titles from Cart Page
            List<String> cartItems = cartPage.getListOfAddedItemsInCart();

            //Validate First Item Title
            Assert.assertTrue(basePage.validateItemsInCart(itemTitle1,cartItems));
            test.log(Status.INFO,"First Item Added to cart - Verifed");

            //Validate Second Item Title
            Assert.assertTrue(basePage.validateItemsInCart(itemTitle2,cartItems));
            test.log(Status.INFO,"Second Item Added to cart - Verifed");

            //Validate Third Item Title
            Assert.assertTrue(basePage.validateItemsInCart(itemTitle3,cartItems));
            test.log(Status.INFO,"Third Item Added to cart - Verifed");

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
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

        //Reading User Defined Input from Excel File
        excelReader.selectSheet(methodName);
        String searchData = excelReader.getData("SEARCH", "USERDATA");

        //creating Extent Test for the test
        test = extent.createTest(methodName);

        try {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in with Amazon account");

            //Searching with User Given data and checking if results are matching with it
            itemPage.searchItem(searchData);
            test.log(Status.INFO,"Item is searched with user input data");
            boolean checkSearch = itemPage.validateProductsDisplayed(searchData);
            Assert.assertTrue(checkSearch);
            test.log(Status.INFO,"Search result is correct");

            //Filtering with the second data on the list of filters and checking if results are matching with it
            String filterName = itemPage.selectFilter();
            test.log(Status.INFO,"Second Filter from the list of filters is selected");
            boolean checkFilter = itemPage.validateProductsDisplayed(filterName);
            Assert.assertTrue(checkFilter);
            test.log(Status.INFO,"Filter result is correct");

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
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

        /*
        Reading Credentials from Excel File
        All Address Details is saved in excel
        */
        excelReader.selectSheet(methodName);
        String fullName = excelReader.getData("FULL NAME", "ADDRESS");
        String mobileNo = excelReader.getData("MOBILE NO", "ADDRESS");
        System.out.println(mobileNo);
        String pincode = excelReader.getData("PINCODE", "ADDRESS");
        String houseDetails = excelReader.getData("HOUSE DETAILS", "ADDRESS");
        String areaDetails = excelReader.getData("AREA DETAILS", "ADDRESS");
        String landmark = excelReader.getData("LANDMARK", "ADDRESS");
        String town = excelReader.getData("TOWN", "ADDRESS");
        String state = excelReader.getData("STATE", "ADDRESS");

        //Try block to handle exceptions
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
            addressPage.enter_FullName(fullName);
            addressPage.enter_MobileNo(mobileNo);
            addressPage.enter_Pincode(pincode);
            addressPage.enter_HouseDetails(houseDetails);
            addressPage.enter_AreaDetails(areaDetails);
            addressPage.enter_Landmark(landmark);
            addressPage.enter_Town(town);
            addressPage.enter_State(state);
            test.log(Status.INFO,"All Details Entered for adding new address");

            addressPage.clickSubmit_AddAddressButton();
            String path = ScreenshotUtil.takeScreenshot(methodName);
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Assert.assertTrue(addressPage.validate_AddedAddress(fullName));
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
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

        //Creating Extent Test
        test = extent.createTest(methodName);

        //Reading User Defined Input from Excel File
        excelReader.selectSheet(methodName);
        String userDefined_Category = excelReader.getData("BEST SELLER CATEGORY", "SAMPLE DATA 1");
        String userDefined_Rank = excelReader.getData("RANK", "SAMPLE DATA 1");

        //Try block to handle exceptions
        try
        {
            // Logging in to Amazon.in with Email Id and Password
            LoginToAmazon();
            test.log(Status.INFO,"Logged in to Amazon.in");

            //Clearing all existing Cart Items
            cartPage.clear_Cart();
            test.log(Status.INFO,"Removing all Products from Cart");

            //Click Best Sellers link on HomePage
            homePage.clickBestSellers();
            test.log(Status.INFO,"Landed on Best Seller page");

            //Click BestSeller Category whereas category is taken from user
            bestSeller.click_SeeMore_For_BestSeller_Category(userDefined_Category);
            test.log(Status.INFO,"Clicked "+userDefined_Category+" on Best Seller Page");

            //Click Ranked Product whereas rank is taken from user
            bestSeller.click_Specific_RankedProduct(userDefined_Rank);
            test.log(Status.INFO,"Clicked Rank No."+userDefined_Rank+" Product on "+userDefined_Category+" page");

            //Storing product Title for the selected product
            String productTitle = bestSeller.getProductTitleText();

            //Click Add to Cart Button on the selected product page
            bestSeller.click_AddToCartButton();
            test.log(Status.INFO,"Click Add to cart button on Product page");

            //Click Cart link from the top Nav bar
            bestSeller.openCartLink();
            test.log(Status.INFO,"Clicked Cart link from top Nav bar");

            //Storing Title for the selected product
            String cartItemTitle = cartPage.get_CartItem_TitleText();

            //Comparing the title from product page and the title of product added to the cart
            Assert.assertTrue(cartPage.matchProductDetails(cartItemTitle,productTitle));

            //Taking Screenshot for Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
            test.log(Status.PASS,"Test Case Passed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

            //Logout from Amazon account
            homePage.click_Logout();
            test.log(Status.INFO,"Logged Out from Amazon account");
        }
        catch (Exception e)
        {
            //Taking Screenshot for Failed Test Case and showing it in Extent Report
            String path = ScreenshotUtil.takeScreenshot(methodName);
            test.log(Status.FAIL,"Test Case Failed ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Assert.fail("Test Case Failed due to exception: "+e.getMessage());
        }
    }
}
