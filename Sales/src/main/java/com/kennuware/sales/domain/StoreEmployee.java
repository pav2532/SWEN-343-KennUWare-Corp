/**
 * Created by John King on 10-Oct-16.
 */
package com.kennuware.sales.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = "findEmployeeByStoreId", query = "FROM StoreEmployee e WHERE e.storeID = :storeID")
})

@Entity
@Table
public class StoreEmployee {
    @Id
    @GeneratedValue
    private int id;
    private int employeeID;
    private int storeID;

    public StoreEmployee(){}

    public int getID(){
        return id;
    }

    public int getEmployeeID(){
        return employeeID;
    }
    public void setEmployeeID(int employeeID){
        this.employeeID = employeeID;
    }

    public int getStoreID(){
        return storeID;
    }
    public void setStoreID(int storeID){
        this.storeID = storeID;
    }
}
