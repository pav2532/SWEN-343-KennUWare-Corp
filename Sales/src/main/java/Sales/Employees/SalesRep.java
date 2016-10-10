package Sales.Employees;

import Sales.Employees.Employee;
import Sales.ShoppingCart;

import Sales.*;

import java.util.ArrayList;

public class SalesRep implements Employee {

    private String name;
    private String id;
    private ShoppingCart currentOrder;
    private OrderHistory history = new OrderHistory();

    public SalesRep(String name, String id, Double commission, Double commissionRate, Double salary){
        this.name = name;
        this.id = id;
        this.currentOrder = new ShoppingCart();
    }

    public void checkout(){

        history.addOrder(new Order(currentOrder.getCustomerName(), id, currentOrder.getValue(), currentOrder.getCreditCardNumber()));
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
