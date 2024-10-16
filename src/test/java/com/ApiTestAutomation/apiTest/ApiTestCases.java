package com.ApiTestAutomation.apiTest;
import com.Amazon.AmazonTestAutomation.utils.ExtentManager;
import com.ApiTestAutomation.api.ApiClient;
import com.ApiTestAutomation.api.AuthClient;
import com.ApiTestAutomation.config.Config;
import com.ApiTestAutomation.utils.ExcelReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;

public class ApiTestCases {
    private AuthClient authClient;
    private ApiClient apiClient;
    private ExcelReader excelReader;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setup() throws IOException {
        excelReader = new ExcelReader(Config.EXCEL_PATH);
        authClient = new AuthClient(excelReader);
        apiClient = new ApiClient(excelReader);
        extent = ExtentManager.getInstance();
    }

    @Test(description = "Authentication Test")
    public void testAuthentication() throws IOException {
        test = extent.createTest("Authentication Token Generation");
        String token = authClient.getAuthToken(test);
        Assert.assertNotNull(token, "Authentication token should not be null");
    }

    @Test(description = "Create Booking Test")
    public void testCreateBooking() throws IOException {
        test = extent.createTest("Create Booking test");
        String response = apiClient.createBooking(test);
        try {
            Assert.assertTrue(response.contains("bookingid"), "Booking ID should be present in response");
            test.log(Status.PASS,"Create Booking successful.");
        }
        catch (Exception e)
        {
            test.log(Status.FAIL,"Create Booking NOT Successful.");
        }
    }

    @Test(description = "Update Booking Test")
    public void testUpdateBooking() throws IOException {
        test = extent.createTest("Update Booking test");
        String token = authClient.getAuthToken(test);
        test.log(Status.INFO,"Authentication Code Generated successfully. Auth Code : "+token);
        String bookingID = apiClient.getNewBookingID(test);
        test.log(Status.INFO,"New Booking created successfully. Booking ID : "+bookingID);
        try {
            String response = apiClient.updateBooking(token, bookingID, test);
            Assert.assertTrue(response.contains("firstname"), "First name should be present in response");
            test.log(Status.PASS,"Update Booking successful for booking ID : "+bookingID);
        }
        catch (Exception e)
        {
            test.log(Status.FAIL,"Update Booking Not Successful for booking ID : "+bookingID);
        }

    }

    @Test(description = "Delete Booking Test")
    public void testDeleteBooking() throws IOException {
        test = extent.createTest("Delete Booking test");
        String token = authClient.getAuthToken(test);
        test.log(Status.INFO,"Authentication Code Generated successfully. Auth Code : "+token);
        String bookingID = apiClient.getNewBookingID(test);
        test.log(Status.INFO,"New Booking created successfully. Booking ID : "+bookingID);
        try {
            String response = apiClient.deleteBooking(token, bookingID, test);
            Assert.assertTrue(response.toLowerCase().contains("created"), "Response should confirm the deletion");
            test.log(Status.PASS,"Deletion successful for booking ID : "+bookingID);
        }
        catch (Exception e)
        {
            test.log(Status.FAIL,"Deletion Not Successful for booking ID : "+bookingID);
        }
    }
    @AfterClass
    public void tearDown()
    {
        // Close Extent report instance
        extent.flush();
    }
}