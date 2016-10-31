package com.kennuware.customersupport.domain.inventory;

/**
 * Created by Ryan on 10/30/2016.
 */
public class InventoryCustomer {
    private String customerName;
    private String address;

    public InventoryCustomer() {
        customerName = "";
        address = "";
    }

    public void setCustomerName(String name) {
        customerName = name;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
