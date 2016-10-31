package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.Utilities.HttpUtils;
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

    public void reportItemRefurbished(int itemID, HttpUtils util) {

        InventoryItem item = new InventoryItem();
        item.setWearableID(itemID);

        String responseBody = util.post(item, "http://localhost:8002/refurbished");
        System.out.println("----------------------------------------");
        System.out.println(responseBody);
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
