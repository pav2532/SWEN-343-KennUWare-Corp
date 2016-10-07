package Sales;

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

    public SalesRep(String name, String id, Double commission, Double commissionRate, Double salary){
        this.name = name;
        this.id = id;
        this.currentOrder = new ShoppingCart();
        this.commissionRate = commissionRate;
        this.commission = commission;
        this.salary = salary;
    }

    //Checks out the current shopping cart
    public void checkout(){

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

    public void setSalary(Double Salary){
        this.salary = salary;
    }


}
