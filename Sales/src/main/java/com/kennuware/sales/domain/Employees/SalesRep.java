package com.kennuware.sales.domain.Employees;

import com.kennuware.sales.domain.Order;
import com.kennuware.sales.domain.OrderHistory;
import com.kennuware.sales.domain.ShoppingCart;

import java.util.ArrayList;

public class SalesRep extends Employee {

    private String name;
    private Integer id;
    private String type;
    private ShoppingCart currentOrder;
    private OrderHistory history = new OrderHistory();

    public SalesRep(String name) {
        super(name, "Associate");
        this.name = name;

        this.id = id;
        this.currentOrder = new ShoppingCart();

        this.type = "Associate";
    }

    public void checkout(){

        // history.addOrder(new Order(currentOrder.getCustomerName(), id, currentOrder.getValue(), currentOrder.getCreditCardNumber()));
        currentOrder.emptyCart();
    }
//    public String getid(){
//        return id;
//    }

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
