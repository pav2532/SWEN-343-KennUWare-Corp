/**
 * Created by John King on 11-Oct-16.
 */

package com.kennuware.sales.services;

import com.google.gson.Gson;
import com.kennuware.sales.domain.Employees.Employee;
import com.kennuware.sales.domain.ItemOrders;
import com.kennuware.sales.domain.SalesOrder;
import com.kennuware.sales.domain.Employees.Employee;
import junit.framework.Assert;
import org.apache.http.client.methods.HttpPost;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.kennuware.sales.services.EmployeeServices.login;
import static com.kennuware.sales.services.OrderServices.completeSaleOrder;
import static com.kennuware.sales.services.OrderServices.sendOrder;
import static com.kennuware.sales.services.EmployeeServices.login;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServicesTest {
    @Test
    public void sendOrderTest(){
        Gson gson = new Gson();
        Session mockedSession = mock(Session.class);

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setBulkDiscount(1.0);
        salesOrder.setCreditCardNumber("4485355145730911");
        salesOrder.setExpirationDate("03/20");
        salesOrder.setEmployeeID(1);
        salesOrder.setCustomerName("Joey");

        assertEquals(gson.toJson(salesOrder), sendOrder(salesOrder, mockedSession));
        assertEquals(gson.toJson(salesOrder.getOrderid()), gson.toJson(completeSaleOrder("Joey", 1, "4485355145730911", "03/20", 1.0, mockedSession)));
    }

    @Test
    public void testOrderItemsFromInventory(){
        Gson gson = new Gson();
        OrderServices orderService = new OrderServices();
        ItemOrders order = new ItemOrders();
        HttpPost mockedRequest = new HttpPost();
        order.setQuantity(33);
        order.setOrderId(12321);

        orderService.orderItemsFromInventory("123 Whatever", order, "Ben", mockedRequest);

        String inputLine ;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mockedRequest.getEntity().getContent()));
            inputLine = br.readLine();
            br.close();

            assertEquals(gson.toJson(
                    "{\"wearableID\":0,\"type\":\"new\",\"quantity\":33,\"orderDetails\":{\"customerName\":\"Ben\",\"address\":\"123 Whatever\"}}"),
                    gson.toJson(inputLine));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
