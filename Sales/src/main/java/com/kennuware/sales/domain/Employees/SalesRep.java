package com.kennuware.sales.domain.Employees;

import com.kennuware.sales.domain.Order;
import com.kennuware.sales.domain.OrderHistory;
import com.kennuware.sales.domain.ShoppingCart;

import java.util.ArrayList;

public class SalesRep {

    private String name;
    private String id;
    private String type;
    private ShoppingCart currentOrder;
    private OrderHistory history = new OrderHistory();

    public SalesRep(String name) {
        this.name = name;

        // TODO: this should be loaded from database. Should never manually set id
        this.id = "1";
        this.currentOrder = new ShoppingCart();

        this.type = "Associate";
    }

    public void checkout(){

        // history.addOrder(new Order(currentOrder.getCustomerName(), id, currentOrder.getValue(), currentOrder.getCreditCardNumber()));
        currentOrder.emptyCart();
    }
    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

    public ShoppingCart getCurrentOrder(){
        return currentOrder;
    }

    public ArrayList<Order> getHistory(){
        return history.getHistory();
    }

}
