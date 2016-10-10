package Sales.Employees;

import Sales.Employees.Employee;
import Sales.ShoppingCart;

import Sales.*;

import java.util.ArrayList;

public class SalesRep implements Employee {

    private String name;
    private String id;
    private ShoppingCart currentOrder;
    private Double commission;
    private Double commissionRate;
    private Double salary;
    private OrderHistory history = new OrderHistory();

    public SalesRep(String name, String id, Double commission, Double commissionRate, Double salary){
        this.name = name;
        this.id = id;
        this.currentOrder = new ShoppingCart();
        this.commissionRate = commissionRate;
        this.commission = commission;
        this.salary = salary;
    }

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

    public ArrayList<Order> getHistory(){
        return history.getHistory();
    }

}
