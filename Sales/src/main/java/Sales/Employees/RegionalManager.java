package Sales.Employees;

import Sales.Employees.Employee;
import Sales.Order;
import Sales.OrderHistory;
import Sales.ShoppingCart;

import java.util.ArrayList;

/**
 * Created by benjamin on 10/7/16.
 */

public class RegionalManager implements Employee {

    private String name;
    private String id;
    private ArrayList<SalesRep> workers = new ArrayList<SalesRep>();
    private Double commissionRate;
    private Double salary;

    public RegionalManager(String name, String id){
        this.name = name;
        this.id = id;
    }

    public Double calcSalary(){
        Double totalCommissions = 0.0;

        for(SalesRep w : workers){
            totalCommissions = totalCommissions + (commissionRate*w.getCommission());
        }
        return salary + totalCommissions;
    }

    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void addWorker(SalesRep newWorker){

        workers.add(newWorker);

    }

}