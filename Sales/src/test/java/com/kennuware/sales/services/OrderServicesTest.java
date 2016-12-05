/**
 * Created by John King on 11-Oct-16.
 */

package com.kennuware.sales.services;

import com.google.gson.Gson;
import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.ItemMetrics;
import com.kennuware.sales.domain.ItemOrders;
import com.kennuware.sales.domain.SalesOrder;
import org.apache.http.client.methods.HttpPost;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.kennuware.sales.services.OrderServices.completeSaleOrder;
import static com.kennuware.sales.services.OrderServices.sendOrder;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
        assertEquals(gson.toJson(salesOrder.getOrderid()), gson.toJson(completeSaleOrder("Joey", 1, "4485355145730911", "03/20", 1.0, "123 whatever", "2000-08-02 12:00:00", mockedSession)));
    }

    @Test
    public void testOrderItemsFromInventory(){
        Gson gson = new Gson();
        OrderServices orderService = new OrderServices();
        ItemOrders order = new ItemOrders();
        HttpPost httpPost = new HttpPost();
        order.setQuantity(33);
        order.setOrderId(12321);

        HttpUtils utils = new HttpUtils();

        String inputLine = orderService.orderItemsFromInventory("123 Whatever", order, "Ben", utils);

        assertEquals(gson.toJson(
                "{\"wearableID\":0,\"type\":\"new\",\"quantity\":33,\"orderDetails\":{\"customerName\":\"Ben\",\"address\":\"123 Whatever\"}}"),
                gson.toJson(inputLine));


    }

    @Test
    public void hightestProductSold(){
        Gson gson = new Gson();
        OrderServices orderService = new OrderServices();
        Session mockedSession = mock(Session.class);

        Query mockedOrderIdsQuery1 = mock(Query.class);
        Query mockedOrderIdsQuery2 = mock(Query.class);
        Query mockedOrderIdsQuery3 = mock(Query.class);


        Query mockedItemsQuery = mock(Query.class);

        List orderIds = new ArrayList<Integer>();
        orderIds.add(1);
        orderIds.add(3);
        ArrayList<ItemOrders> itemOrdersEmpty = new ArrayList<>();
        ArrayList<ItemOrders> itemOrdersId1 = new ArrayList<>();
        itemOrdersId1.add(new ItemOrders(1, 1, 3));
        itemOrdersId1.add(new ItemOrders(2, 1, 2));
        ArrayList<ItemOrders> itemOrdersId2 = new ArrayList<>();
        itemOrdersId2.add(new ItemOrders(3, 2, 1));


        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, "F", 10));
        items.add(new Item(2, "H", 20));
        items.add(new Item(3, "W", 40));

        //when(mockedSession.getNamedQuery("findAllItemOrders")).thenReturn(mockedItemOrdersQuery);
        when(mockedSession.getNamedQuery("findItemById")).thenReturn(mockedOrderIdsQuery1, mockedOrderIdsQuery2, mockedOrderIdsQuery3);
        when(mockedOrderIdsQuery1.setString(Mockito.eq("itemId"), Mockito.eq("1"))).thenReturn(mockedOrderIdsQuery1);
        when(mockedOrderIdsQuery1.list()).thenReturn(itemOrdersId1);
        when(mockedOrderIdsQuery2.setString(Mockito.eq("itemId"), Mockito.eq("2"))).thenReturn(mockedOrderIdsQuery2);
        when(mockedOrderIdsQuery2.list()).thenReturn(itemOrdersId2);
        when(mockedOrderIdsQuery3.setString(Mockito.eq("itemId"), Mockito.eq("3"))).thenReturn(mockedOrderIdsQuery3);
        when(mockedOrderIdsQuery3.list()).thenReturn(itemOrdersEmpty);
        when(mockedSession.getNamedQuery("findAllItems")).thenReturn(mockedItemsQuery);
        when(mockedItemsQuery.list()).thenReturn(items);
        //when(mockedItemOrdersQuery.iterate()).thenReturn(itemOrders.iterator());

        assertEquals(orderService.getHighestOrder(mockedSession),new ItemMetrics("F",5));


    }
    @Test
    public void lowestProductSold(){
        Gson gson = new Gson();
        OrderServices orderService = new OrderServices();
        Session mockedSession = mock(Session.class);

        Query mockedOrderIdsQuery1 = mock(Query.class);
        Query mockedOrderIdsQuery2 = mock(Query.class);
        Query mockedOrderIdsQuery3 = mock(Query.class);


        Query mockedItemsQuery = mock(Query.class);

        List orderIds = new ArrayList<Integer>();
        orderIds.add(1);
        orderIds.add(3);
        ArrayList<ItemOrders> itemOrdersEmpty = new ArrayList<>();

        ArrayList<ItemOrders> itemOrdersId1 = new ArrayList<>();
        itemOrdersId1.add(new ItemOrders(1, 1, 3));
        itemOrdersId1.add(new ItemOrders(2, 1, 2));

        ArrayList<ItemOrders> itemOrdersId2 = new ArrayList<>();
        itemOrdersId2.add(new ItemOrders(3, 2, 1));


        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, "F", 10));
        items.add(new Item(2, "H", 20));
        items.add(new Item(3, "W", 40));

        when(mockedSession.getNamedQuery("findItemById")).thenReturn(mockedOrderIdsQuery1, mockedOrderIdsQuery2, mockedOrderIdsQuery3);
        when(mockedOrderIdsQuery1.setString(Mockito.eq("itemId"), Mockito.eq("1"))).thenReturn(mockedOrderIdsQuery1);
        when(mockedOrderIdsQuery1.list()).thenReturn(itemOrdersId1);
        when(mockedOrderIdsQuery2.setString(Mockito.eq("itemId"), Mockito.eq("2"))).thenReturn(mockedOrderIdsQuery2);
        when(mockedOrderIdsQuery2.list()).thenReturn(itemOrdersId2);
        when(mockedOrderIdsQuery3.setString(Mockito.eq("itemId"), Mockito.eq("3"))).thenReturn(mockedOrderIdsQuery3);
        when(mockedOrderIdsQuery3.list()).thenReturn(itemOrdersEmpty);
        when(mockedSession.getNamedQuery("findAllItems")).thenReturn(mockedItemsQuery);
        when(mockedItemsQuery.list()).thenReturn(items);

        assertEquals(orderService.getLowestOrder(mockedSession), new ItemMetrics("W", 0));


    }
    @Test
    public void revenueModelTest(){
        Gson gson = new Gson();
        OrderServices orderService = new OrderServices();
        Session mockedSession = mock(Session.class);

        Query mockedOrderIdsQuery1 = mock(Query.class);
        Query mockedOrderIdsQuery2 = mock(Query.class);
        Query mockedOrderIdsQuery3 = mock(Query.class);


        Query mockedItemsQuery = mock(Query.class);

        List orderIds = new ArrayList<Integer>();
        orderIds.add(1);
        orderIds.add(3);
        ArrayList<ItemOrders> itemOrdersEmpty = new ArrayList<>();

        ArrayList<ItemOrders> itemOrdersId1 = new ArrayList<>();
        itemOrdersId1.add(new ItemOrders(1, 1, 3));
        itemOrdersId1.add(new ItemOrders(2, 1, 2));

        ArrayList<ItemOrders> itemOrdersId2 = new ArrayList<>();
        itemOrdersId2.add(new ItemOrders(3, 2, 1));


        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, "F", 10));
        items.add(new Item(2, "H", 20));
        items.add(new Item(3, "W", 40));
        ArrayList<ItemMetrics> revenue = new ArrayList<>();
        revenue.add(new ItemMetrics("F", 5, 50.0));
        revenue.add(new ItemMetrics("H", 1, 20.0));
        revenue.add(new ItemMetrics("W", 0, 0.0));

        //when(mockedSession.getNamedQuery("findAllItemOrders")).thenReturn(mockedItemOrdersQuery);
        when(mockedSession.getNamedQuery("findItemById")).thenReturn(mockedOrderIdsQuery1, mockedOrderIdsQuery2, mockedOrderIdsQuery3);

        when(mockedOrderIdsQuery1.setString(Mockito.eq("itemId"), Mockito.eq("1"))).thenReturn(mockedOrderIdsQuery1);
        when(mockedOrderIdsQuery1.list()).thenReturn(itemOrdersId1);

        when(mockedOrderIdsQuery2.setString(Mockito.eq("itemId"), Mockito.eq("2"))).thenReturn(mockedOrderIdsQuery2);
        when(mockedOrderIdsQuery2.list()).thenReturn(itemOrdersId2);

        when(mockedOrderIdsQuery3.setString(Mockito.eq("itemId"), Mockito.eq("3"))).thenReturn(mockedOrderIdsQuery3);
        when(mockedOrderIdsQuery3.list()).thenReturn(itemOrdersEmpty);

        when(mockedSession.getNamedQuery("findAllItems")).thenReturn(mockedItemsQuery);
        when(mockedItemsQuery.list()).thenReturn(items);
        //when(mockedItemOrdersQuery.iterate()).thenReturn(itemOrders.iterator());

        assertEquals(orderService.getRevenueByModel(mockedSession), revenue);


    }



}
