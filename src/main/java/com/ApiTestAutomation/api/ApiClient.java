package com.ApiTestAutomation.api;

import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;
import com.ApiTestAutomation.utils.ApiUtils;
import com.ApiTestAutomation.utils.ExcelReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {
    public static LoggerUtil logger = new LoggerUtil();
    private ExcelReader excelReader;

    public ApiClient(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }
    public String createBooking() throws IOException {
        Map<String, String> data = excelReader.getData("CreateBooking");
        String url = data.get("URL");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", data.get("FirstName"));
        requestBody.put("lastname", data.get("LastName"));
        requestBody.put("totalprice", Integer.parseInt(data.get("TotalPrice")));
        requestBody.put("depositpaid", Boolean.parseBoolean(data.get("DepositPaid")));
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", data.get("CheckIn"));
        bookingDates.put("checkout", data.get("CheckOut"));
        requestBody.put("bookingdates", bookingDates);

        requestBody.put("additionalneeds", data.get("AdditionalNeeds"));

        String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
        return ApiUtils.postRequest(url, jsonBody);
    }

    public String getNewBookingID() throws IOException {
        Map<String, String> data = excelReader.getData("CreateBooking");
        String url = data.get("URL");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", data.get("FirstName"));
        requestBody.put("lastname", data.get("LastName"));
        requestBody.put("totalprice", Integer.parseInt(data.get("TotalPrice")));
        requestBody.put("depositpaid", Boolean.parseBoolean(data.get("DepositPaid")));
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", data.get("CheckIn"));
        bookingDates.put("checkout", data.get("CheckOut"));
        requestBody.put("bookingdates", bookingDates);
        requestBody.put("additionalneeds", data.get("AdditionalNeeds"));

        String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
        String responseBody = ApiUtils.postRequest(url, jsonBody);
        JSONObject jsonResponse1 = new JSONObject(responseBody);
        logger.info("Booking id : " + String.valueOf(jsonResponse1.getInt("bookingid")));
        return String.valueOf(jsonResponse1.getInt("bookingid"));
    }

    public String updateBooking(String token, String id) throws IOException {
        Map<String, String> data = excelReader.getData("UpdateBooking");
        String url = data.get("URL") + "/" + id;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("firstname", data.get("FirstName"));
        requestBody.put("lastname", data.get("LastName"));
        requestBody.put("totalprice", data.get("TotalPrice"));
        requestBody.put("depositpaid", Boolean.parseBoolean(data.get("DepositPaid")));

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", data.get("CheckIn"));
        bookingDates.put("checkout", data.get("CheckOut"));
        requestBody.put("bookingdates", bookingDates);
        requestBody.put("additionalneeds", data.get("AdditionalNeeds"));

        String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
        return ApiUtils.putRequest(url, jsonBody, token);
    }

    public String deleteBooking(String token, String ID) throws IOException {
        Map<String, String> data = excelReader.getData("DeleteBooking");
        String url = data.get("URL") + "/" + ID;
        return ApiUtils.deleteRequest(url, token);
    }
}