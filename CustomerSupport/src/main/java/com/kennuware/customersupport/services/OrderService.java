package com.kennuware.customersupport.services;

import com.kennuware.customersupport.domain.Order;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by rywils21 on 10/30/2016.
 */
public class OrderService {

    public void orderRefurbishedItem(Order order) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            try {
                HttpPost request = new HttpPost("http://localhost:8002/productorder");

                StringEntity params = new StringEntity("{\"name\":\"myname\",\"age\":\"20\"}");
                request.addHeader("content-type", "application/json");
                request.setEntity(params);

                System.out.println("Executing request " + request.getRequestLine());

                // Create a custom response handler
                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    }

                };
                String responseBody = httpClient.execute(request, responseHandler);
                System.out.println("----------------------------------------");
                System.out.println(responseBody);
            } finally {
                httpClient.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void orderWarrantyItem(Order order) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {
                HttpPost httpPost = new HttpPost("http://localhost:8002/productorder");

                System.out.println("Executing request " + httpPost.getRequestLine());

                // Create a custom response handler
                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    }

                };
                String responseBody = httpclient.execute(httpPost, responseHandler);
                System.out.println("----------------------------------------");
                System.out.println(responseBody);
            } finally {
                httpclient.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
