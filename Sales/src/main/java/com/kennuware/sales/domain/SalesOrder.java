package com.kennuware.sales.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class SalesOrder {

    @Id
    @GeneratedValue
    private int orderid;

    private String customerName;
    private int employeeID;
    private String creditCardNumber;
    private String expirationDate;

    public SalesOrder(){}

    public SalesOrder(String customerName, int employeeID, String creditCardNumber, String expirationDate){
        this.customerName = customerName;
        this.employeeID = employeeID;
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
    }
}
