package Sales.Employees;

import Sales.Employees.Employee;
import Sales.Order;
import Sales.OrderHistory;
import Sales.ShoppingCart;

import java.util.ArrayList;

public class GeneralManager implements Employee {

    private String name;
    private String id;
    private ShoppingCart currentOrder;
    private OrderHistory history = new OrderHistory();

    public GeneralManager(String name, String id){
        this.name = name;
        this.id = id;
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

