package com.kennuware.sales.domain;

import java.util.ArrayList;

public class OrderHistory {

    private ArrayList<Order> history = new ArrayList<Order>();

    public OrderHistory(){}

    public void addOrder(Order newOrder){
        history.add(newOrder);
    }

    public ArrayList<Order> getHistory(){
        return history;
    }

}
