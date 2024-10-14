package com.ApiTestAutomation.api;

import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;
import com.ApiTestAutomation.utils.ApiUtils;
import com.ApiTestAutomation.utils.ExcelReader;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Map;

public class AuthClient {
    public static LoggerUtil logger = new LoggerUtil();
    private ExcelReader excelReader;

    public AuthClient(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }
    public String getAuthToken() throws IOException {
        Map<String, String> data = excelReader.getData("AuthData");
        String url = data.get("URL");
        String username = data.get("Username");
        String password = data.get("Password");

        JSONObject authBody = new JSONObject();
        authBody.put("username", username);
        authBody.put("password", password);

        logger.info("Getting authentication token from URL: " + url);
        String response = ApiUtils.postRequest(url, authBody.toString());
        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse.getString("token");
    }
}
