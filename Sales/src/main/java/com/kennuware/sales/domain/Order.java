package com.kennuware.sales.domain;

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

    public Double getValue(){
        return transactionValue;
    }

}
