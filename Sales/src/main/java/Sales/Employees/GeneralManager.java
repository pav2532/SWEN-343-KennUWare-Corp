package Sales.Employees;

import Sales.Employees.Employee;
import Sales.ShoppingCart;

/**
 * Created by benjamin on 10/7/16.
 */
public class GeneralManager implements Employee {

    private String name;
    private String id;
    private ShoppingCart currentOrder;

    public GeneralManager(String name, String id){
        this.name = name;
        this.id = id;
        this.currentOrder = new ShoppingCart();

    }


    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

}
