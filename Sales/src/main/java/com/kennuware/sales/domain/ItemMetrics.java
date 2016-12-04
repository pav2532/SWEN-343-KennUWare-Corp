package com.kennuware.sales.domain;

/**
 * Created by Pedro Vega on 12/4/2016.
 */
public class ItemMetrics {
    private String name;
    private int quantity = 0;
    private double revenue = 0;
    public ItemMetrics(String name, int quantity, double revenue){
        this.name = name;
        this.quantity = quantity;
        this.revenue = revenue;
    }
    public ItemMetrics(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
}
