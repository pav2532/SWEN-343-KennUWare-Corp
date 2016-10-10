package com.kennuware.sales.domain.Employees;

import com.kennuware.sales.domain.Order;
import com.kennuware.sales.domain.OrderHistory;
import com.kennuware.sales.domain.ShoppingCart;

import java.util.ArrayList;

public class GeneralManager extends Employee {

    private String name;
    private String id;
    private String type;
    private ShoppingCart currentOrder;
    private OrderHistory history = new OrderHistory();

    public GeneralManager(String name){
        super(name, "GeneralManager");
        this.name = name;
        // this.id = id;

        this.type = "GeneralManager";
    }


    //Checks out the current shopping cart
    public void checkout(Double bulkDiscount){
        history.addOrder(new Order(currentOrder.getCustomerName(), id, currentOrder.getValue() * bulkDiscount, currentOrder.getCreditCardNumber()));
        currentOrder.emptyCart();
    }

    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Order> getHistory(){
        return history.getHistory();
    }

}

