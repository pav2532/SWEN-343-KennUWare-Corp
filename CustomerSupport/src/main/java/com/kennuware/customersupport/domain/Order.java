package com.kennuware.customersupport.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "findOrderIdsByEmployeeID", query = "SELECT s.orderid FROM Order s "
    		+ "WHERE s.employeeID=:eid")
})

@Entity
@Table
public class Order {

    @Id
    @GeneratedValue
    private int orderid;

    private String customerName;
    private int employeeID;
    private String creditCardNumber;
    private String expirationDate;
    private Double bulkDiscount;

    public Order(){}

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
