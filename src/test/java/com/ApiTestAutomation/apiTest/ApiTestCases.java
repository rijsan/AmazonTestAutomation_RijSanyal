package com.ApiTestAutomation.apiTest;
import com.Amazon.AmazonTestAutomation.utils.ExtentManager;
import com.ApiTestAutomation.api.ApiClient;
import com.ApiTestAutomation.api.AuthClient;
import com.ApiTestAutomation.config.Config;
import com.ApiTestAutomation.utils.ExcelReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
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
        String token = authClient.getAuthToken();
        Assert.assertNotNull(token, "Authentication token should not be null");
    }

    @Test(description = "Create Booking Test")
    public void testCreateBooking() throws IOException {
        test = extent.createTest("Create Booking test");
        String response = apiClient.createBooking();
        Assert.assertTrue(response.contains("bookingid"), "Booking ID should be present in response");
    }

    @Test(description = "Update Booking Test")
    public void testUpdateBooking() throws IOException {
        test = extent.createTest("Update Booking test");
        String token = authClient.getAuthToken();
        String bookingID = apiClient.getNewBookingID();
        String response = apiClient.updateBooking(token, bookingID);
        Assert.assertTrue(response.contains("firstname"), "First name should be present in response");
    }

    @Test(description = "Delete Booking Test")
    public void testDeleteBooking() throws IOException {
        test = extent.createTest("Delete Booking test");
        String token = authClient.getAuthToken();
        String bookingID = apiClient.getNewBookingID();
        String response = apiClient.deleteBooking(token, bookingID);
        Assert.assertTrue(response.toLowerCase().contains("created"), "Response should confirm the deletion");
    }
    @AfterClass
    public void tearDown()
    {
        // Close Extent report instance
        extent.flush();
    }
}