package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.ItemOrders;
import com.kennuware.customersupport.domain.Order;
import com.kennuware.customersupport.domain.inventory.InventoryCustomer;
import com.kennuware.customersupport.domain.inventory.InventoryOrder;
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

    public void orderRefurbishedItem(Order order, ItemOrders itemOrder, HttpUtils util) {

        InventoryCustomer customer = new InventoryCustomer();
        customer.setCustomerName(order.getCustomerName());

        InventoryOrder iOrder = new InventoryOrder();
        iOrder.setOrderDetails(customer);
        iOrder.setType("refurbished");
        iOrder.setWearableID(itemOrder.getItemId());

        String responseBody = util.post(iOrder, "http://localhost:8002/productorder");
        System.out.println("----------------------------------------");
        System.out.println(responseBody);

    }

    public void orderWarrantyItem(Order order, ItemOrders itemOrder, HttpUtils util) {

        InventoryCustomer customer = new InventoryCustomer();
        customer.setCustomerName(order.getCustomerName());

        InventoryOrder iOrder = new InventoryOrder();
        iOrder.setOrderDetails(customer);
        iOrder.setType("new");
        iOrder.setWearableID(itemOrder.getItemId());

        String responseBody = util.post(iOrder, "http://localhost:8002/productorder");
        System.out.println("----------------------------------------");
        System.out.println(responseBody);

    }
}
