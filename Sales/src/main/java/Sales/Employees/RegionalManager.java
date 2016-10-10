package Sales.Employees;

import Sales.Employees.Employee;
import Sales.Order;
import Sales.OrderHistory;
import Sales.ShoppingCart;

import java.util.ArrayList;


public class RegionalManager implements Employee {

    private String name;
    private String id;
    private Double commissionRate;
    private Double salary;

    public RegionalManager(String name, String id){
        this.name = name;
        this.id = id;
    }


    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

}