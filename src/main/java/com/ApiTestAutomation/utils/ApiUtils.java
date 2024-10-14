package com.ApiTestAutomation.utils;

import com.Amazon.AmazonTestAutomation.utils.LoggerUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class ApiUtils {
    public static LoggerUtil logger = new LoggerUtil();

    public static String postRequest(String url, String jsonBody) throws IOException {
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
        logger.info("Response: " + result);
        client.close();
        return result;
    }

    public static String putRequest(String url, String jsonBody, String token) throws IOException {
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
        logger.info("Response: " + result);
        client.close();
        return result;
    }

    public static String deleteRequest(String url, String token) throws IOException {
        logger.info("Sending DELETE request to URL: " + url);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(url);
        delete.setHeader("Content-Type", "application/json");
        delete.setHeader("Cookie", "token=" + token);
        logger.info("Response: " + delete);
        CloseableHttpResponse response = client.execute(delete);
        String result = EntityUtils.toString(response.getEntity());
        logger.info("Response Code : " + response.getStatusLine().getStatusCode());
        logger.info("Response: " + result);
        client.close();
        return result;
    }
}