package Sales.Employees;

import Sales.Employees.Employee;
import Sales.Order;
import Sales.OrderHistory;
import Sales.ShoppingCart;

/**
 * Created by benjamin on 10/7/16.
 */

public class GeneralManager implements Employee {

    private String name;
    private String id;
    private ShoppingCart currentOrder;
    private Double commission;
    private Double commissionRate;
    private Double salary;
    private OrderHistory history;

    public GeneralManager(String name, String id){
        this.name = name;
        this.id = id;
    }


    //Checks out the current shopping cart
    public void checkout(Double bulkDiscount){
        commission = commission + (commissionRate * currentOrder.getValue());
        history.addOrder(new Order(currentOrder.getCustomerName(), id, currentOrder.getValue() * bulkDiscount, currentOrder.getCreditCardNumber()));
        currentOrder.emptyCart();
    }

    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Double calcSalary(){
        return salary + commission;
    }

}

