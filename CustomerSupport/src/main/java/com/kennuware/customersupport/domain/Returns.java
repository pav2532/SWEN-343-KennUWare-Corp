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
        @NamedQuery(name = "findReturn", query = "select r from Returns r "
        		+ "where r.id = :id"),
        @NamedQuery(name = "findAllReturns", query = "select r from Returns r"),
        @NamedQuery(name = "findReturnsByItemID", query = "select r from Returns r "
        		+ "where r.itemID = :itemID"),
        @NamedQuery(name = "findMostReturned", query = "select count(r) from Returns r "
        		+ "group by r.itemID")
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
    private String itemID;

    public String getItemID() {
        return itemID;
    }
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

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
