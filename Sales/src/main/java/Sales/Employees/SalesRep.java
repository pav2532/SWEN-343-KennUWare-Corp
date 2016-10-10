package Sales.Employees;

import Sales.Employees.Employee;
import Sales.ShoppingCart;

import Sales.*;

/**
 * Created by benjamin on 10/7/16.
 */
public class SalesRep implements Employee {

    private String name;
    private String id;
    private ShoppingCart currentOrder;
    private Double commission;
    private Double commissionRate;
    private Double salary;
    private OrderHistory history;

    public SalesRep(String name, String id, Double commission, Double commissionRate, Double salary, OrderHistory history){
        this.name = name;
        this.id = id;
        this.currentOrder = new ShoppingCart();
        this.commissionRate = commissionRate;
        this.commission = commission;
        this.salary = salary;
        this.history = history;
    }

    //Checks out the current shopping cart
    public void checkout(){
        commission = commission + (commissionRate * currentOrder.getValue());
        history.addOrder(new Order(currentOrder.getCustomerName(), id, currentOrder.getValue(), currentOrder.getCreditCardNumber()));
        currentOrder.emptyCart();
    }

    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setCommissionRate(Double commissionRate){
        this.commissionRate = commissionRate;
    }

    public Double getCommission(){
        return commission;
    }

    public void setSalary(Double Salary){
        this.salary = salary;
    }

    public ShoppingCart getCurrentOrder(){
        return currentOrder;
    }

    public Double calcSalary(){
        return salary + commission;
    }

}
