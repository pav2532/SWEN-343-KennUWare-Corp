package com.kennuware.customersupport.domain.inventory;

import com.kennuware.customersupport.services.OrderService;

/**
 * Created by Ryan on 10/30/2016.
 */
public class InventoryOrder {
    private int wearableID;
    private String type;
    private InventoryCustomer orderDetails;

    public InventoryOrder() {}

    public void setWearableID(int id) {
        wearableID = id;
    }

    public int getWearableID() {
        return wearableID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setOrderDetails(InventoryCustomer customer) {
        orderDetails = customer;
    }

    public InventoryCustomer getOrderDetails() {
        return orderDetails;
    }
}
