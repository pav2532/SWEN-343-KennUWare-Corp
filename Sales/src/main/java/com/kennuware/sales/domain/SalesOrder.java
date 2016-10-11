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
    private Double bulkDiscount;

    public SalesOrder(){}

    public int getOrderid() {
        return orderid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public Double getBulkDiscount() {
        return bulkDiscount;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setBulkDiscount(Double bulkDiscount) {
        this.bulkDiscount = bulkDiscount;
    }
}
