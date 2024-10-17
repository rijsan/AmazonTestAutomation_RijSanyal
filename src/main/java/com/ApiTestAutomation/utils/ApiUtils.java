package com.ApiTestAutomation.utils;

import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class ApiUtils {
    public static LoggerUtil logger = new LoggerUtil();

    // Method to handle POST request
    public static String postRequest(String url, String jsonBody, ExtentTest test) throws IOException {
        logger.info("Sending POST request to URL: " + url);
        logger.debug("Request Body: " + jsonBody);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(jsonBody));

        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        logger.info("Response Code : " + response.getStatusLine().getStatusCode());
        test.log(Status.INFO,"Response Code : "+response.getStatusLine().getStatusCode());
        logger.info("Response: " + result);
        test.log(Status.INFO,"Response Body : "+result);
        client.close();
        return result;
    }

    // Method to handle PUT request
    public static String putRequest(String url, String jsonBody, String token, ExtentTest test) throws IOException {
        logger.info("Sending PUT request to URL: " + url);

        logger.info(jsonBody);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);
        put.setHeader("Content-Type", "application/json");
        put.setHeader("Accept", "application/json");
        put.setHeader("Cookie", "token=" + token);
        put.setEntity(new StringEntity(jsonBody));

        CloseableHttpResponse response = client.execute(put);
        String result = EntityUtils.toString(response.getEntity());
        logger.info("Response Code : " + response.getStatusLine().getStatusCode());
        test.log(Status.INFO,"Response Code : "+response.getStatusLine().getStatusCode());
        logger.info("Response: " + result);
        test.log(Status.INFO,"Response Body : "+result);
        client.close();
        return result;
    }

    // Method to handle DELETE request
    public static String deleteRequest(String url, String token, ExtentTest test) throws IOException {
        logger.info("Sending DELETE request to URL: " + url);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(url);
        delete.setHeader("Content-Type", "application/json");
        delete.setHeader("Cookie", "token=" + token);
        logger.info("Response: " + delete);
        CloseableHttpResponse response = client.execute(delete);
        String result = EntityUtils.toString(response.getEntity());
        logger.info("Response Code : " + response.getStatusLine().getStatusCode());
        test.log(Status.INFO,"Response Code : "+response.getStatusLine().getStatusCode());
        logger.info("Response: " + result);
        test.log(Status.INFO,"Response Body : "+result);
        client.close();
        return result;
    }

    // Method to handle GET request
    public static String getRequest(String url) throws IOException {
        logger.info("Sending GET request to URL: " + url);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "application/json");

        CloseableHttpResponse response = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("Response Code: " + statusCode);  // Log response code

        String result = EntityUtils.toString(response.getEntity());
        logger.info("Response Body: " + result);  // Log response body

        client.close();
        return result;
    }
}