/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = "findEmployeeByEmployeeId", query = "select e from Employee e where e.eid = :eid"),
        @NamedQuery(name = "findEmployeeByEmployeeName", query = "select e from Employee e where e.name = :name"),
        @NamedQuery(name = "findAllEmployees", query = "select e from Employee e")
})

@Entity
@Table
public class Returns {
    @Id
    @GeneratedValue
    private int id;
    private String reason;
    private int storeID;
    private ReturnType type;

    public Returns(){}

    public int getID(){
        return id;
    }

    public String getReason(){
        return reason;
    }
    public void setReason(String reason){
        this.reason = reason;
    }

    public int getStoreID(){
        return storeID;
    }
    public void setStoreID(int storeID){
        this.storeID = storeID;
    }

    public ReturnType getType(){return type;}

    public void setType(ReturnType type){
        this.type = type;
    }
}
