package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.domain.ItemOrders;
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

    public void orderRefurbishedItem(Order order, ItemOrders itemOrder) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            try {
                HttpPost request = new HttpPost("http://localhost:8002/productorder");

                InventoryCustomer customer = new InventoryCustomer();
                customer.setCustomerName(order.getCustomerName());

                InventoryOrder iOrder = new InventoryOrder();
                iOrder.setOrderDetails(customer);
                iOrder.setType("refurbished");
                iOrder.setWearableID(itemOrder.getItemId());

                Gson gson = new Gson();
                StringEntity body = new StringEntity(gson.toJson(iOrder));
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

    public void orderWarrantyItem(Order order, ItemOrders itemOrder) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {
                HttpPost request = new HttpPost("http://localhost:8002/productorder");

                InventoryCustomer customer = new InventoryCustomer();
                customer.setCustomerName(order.getCustomerName());

                InventoryOrder iOrder = new InventoryOrder();
                iOrder.setOrderDetails(customer);
                iOrder.setType("warranty");
                iOrder.setWearableID(itemOrder.getItemId());

                Gson gson = new Gson();
                StringEntity body = new StringEntity(gson.toJson(iOrder));
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

    private class InventoryOrder {
        private int wearableID;
        private String type;
        private InventoryCustomer orderDetails;

        public InventoryOrder() {}

        public void setWearableID(int id) {
            wearableID = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setOrderDetails(InventoryCustomer customer) {
            orderDetails = customer;
        }

    }

    private class InventoryCustomer {
        private String customerName;
        private String address;

        public InventoryCustomer() {
            customerName = "";
            address = "";
        }

        public void setCustomerName(String name) {
            customerName = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
