package com.kennuware.customersupport.services;

import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.ItemOrders;
import com.kennuware.customersupport.domain.Order;
import com.kennuware.customersupport.domain.inventory.InventoryItem;
import com.kennuware.customersupport.domain.inventory.InventoryOrder;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ryan on 10/30/2016.
 */
public class OrderServiceTest {

    /**
     * This test basically just tests that OrderService.orderRefurbishedItem(order, itemOrder, httpUtil)
     * creates the appropriate object for the /productorder API endpoint.
     *
     * This is done by creating an ArgumentCaptor using Mockito and verifying the fields on
     * the captured object that is being passed to the POST request.
     */
    @Test
    public void testOrderRefurbishedItem() {
        OrderService service = new OrderService();
        int itemId = 1;
        String customerName = "Ryan";
        HttpUtils util = mock(HttpUtils.class);

        Order order = mock(Order.class);
        when(order.getCustomerName()).thenReturn(customerName);

        ItemOrders itemOrder = mock(ItemOrders.class);
        when(itemOrder.getItemId()).thenReturn(itemId);

        ArgumentCaptor<InventoryOrder> argument = ArgumentCaptor.forClass(InventoryOrder.class);
        when(util.post(Mockito.anyObject(), Mockito.anyString())).thenReturn("");

        service.orderRefurbishedItem(order, itemOrder, util);
        verify(util).post(argument.capture(), Mockito.anyString());

        assertEquals("refurbished", argument.getValue().getType());
        assertEquals(itemId, argument.getValue().getWearableID());
        assertEquals(customerName, argument.getValue().getOrderDetails().getCustomerName());
    }

    /**
     * This test basically just tests that OrderService.orderWarrantyItem(order, itemOrder, httpUtil)
     * creates the appropriate object for the /productorder API endpoint.
     *
     * This is done by creating an ArgumentCaptor using Mockito and verifying the fields on
     * the captured object that is being passed to the POST request.
     */
    @Test
    public void testOrderWarranyItem() {
        OrderService service = new OrderService();
        int itemId = 1;
        String customerName = "Ryan";
        HttpUtils util = mock(HttpUtils.class);

        Order order = mock(Order.class);
        when(order.getCustomerName()).thenReturn(customerName);

        ItemOrders itemOrder = mock(ItemOrders.class);
        when(itemOrder.getItemId()).thenReturn(itemId);

        ArgumentCaptor<InventoryOrder> argument = ArgumentCaptor.forClass(InventoryOrder.class);
        when(util.post(Mockito.anyObject(), Mockito.anyString())).thenReturn("");

        service.orderWarrantyItem(order, itemOrder, util);
        verify(util).post(argument.capture(), Mockito.anyString());

        assertEquals("new", argument.getValue().getType());
        assertEquals(itemId, argument.getValue().getWearableID());
        assertEquals(customerName, argument.getValue().getOrderDetails().getCustomerName());
    }
}
