package com.kennuware.sales.domain;

/**
 * Created by John King on 04-Dec-16.
 */
public class StoreRev {
    final Store store;
    final double revenue;

    public StoreRev(Store store, double revenue){
        this.store = store;
        this.revenue = revenue;
    }

    public Store getStore() {
        return store;
    }

    public double getRevenue() {
        return revenue;
    }
}
