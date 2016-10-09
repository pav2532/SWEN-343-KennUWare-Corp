package Sales;

/**
 * Created by benjamin on 10/9/16.
 */
public class Order {

    private String customerName;
    private String employeeID;
    private Double transactionValue;
    private String creditCardNumber;

    public Order(String customerName, String employeeID, Double transactionValue, String creditCardNumber){
        this.customerName = customerName;
        this.employeeID = employeeID;
        this.transactionValue = transactionValue;
        this.creditCardNumber = creditCardNumber;
    }

}
