/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.kennuware.sales.domain.Catalog;
import com.kennuware.sales.domain.Wearables.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;

public class ItemServices {
    //Gets a catalog of all items that can be sold
    public static Catalog getCatalog(){
        return new Catalog();
    }
    public static String getItems() {
        String responseBody = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {
                HttpGet httpget;
                httpget = new HttpGet("http://localhost:8002/itemCatalog");

                System.out.println("Executing request " + httpget.getRequestLine());

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
                responseBody = httpclient.execute(httpget, responseHandler);
                System.out.println("----------------------------------------");
                System.out.println(responseBody);
            } finally {
                httpclient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBody;
    }
    //Gets a previously sold wearable for customer support
    //Change to pull a wearable from the database based off idNumber
    public static Wearable getWearable(int idNumber){
        return null;
    }
}
