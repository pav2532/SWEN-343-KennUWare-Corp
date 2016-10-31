package com.kennuware.customersupport.domain;

/**
 * Created by Ryan on 10/31/2016.
 */
public class WearableItem {
    private int id;
    private String name;
    private String type;
    private int quantity;
    private String active;

    public WearableItem(int id, String name, String type, int quantity, String active){
        this.id = id;
        this.name=name;
        this.type=type;
        this.quantity = quantity;
        this.active=active;
    }

    public String toString() {
        return "ID: " + id + " Name: " + name + " Type: " + type;
    }
}
