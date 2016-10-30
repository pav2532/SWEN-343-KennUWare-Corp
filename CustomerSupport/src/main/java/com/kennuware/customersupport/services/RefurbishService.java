package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by rywils21 on 10/30/2016.
 */
public class RefurbishService {

    public void reportItemRefurbished(int itemID) {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            try {
                HttpPost request = new HttpPost("http://localhost:8002/refurbished");

                InventoryItem item = new InventoryItem();
                item.setWearableID(itemID);
                Gson gson = new Gson();
                StringEntity body = new StringEntity(gson.toJson(item));
                request.addHeader("content-type", "application/json");
                request.setEntity(body);

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
                String responseBody = httpclient.execute(request, responseHandler);
                System.out.println("----------------------------------------");
                System.out.println(responseBody);
            } finally {
                httpclient.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private class InventoryItem {
        private int wearableID;
        private String type;

        public InventoryItem() {
            wearableID = 0;
            type = "refurbished";
        }

        public void setWearableID(int id) {
            wearableID = id;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
