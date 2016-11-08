package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.inventory.InventoryItem;
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

    public static void reportItemRefurbished(int itemID, HttpUtils util) {

        System.out.println("refurbishing");
        InventoryItem item = new InventoryItem();
        item.setWearableID(itemID);

        String responseBody = util.post(item, "http://localhost:8002/refurbished");
        System.out.println("----------------------------------------");
        System.out.println(responseBody);
    }
}
