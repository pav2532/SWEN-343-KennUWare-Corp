package com.kennuware.sales.domain;

import java.util.ArrayList;

public class OrderHistory {

    private ArrayList<SalesOrder> history = new ArrayList<SalesOrder>();

    public OrderHistory(){}

    public void addOrder(SalesOrder newOrder){
        history.add(newOrder);
    }

    public ArrayList<SalesOrder> getHistory(){
        return history;
    }

}
