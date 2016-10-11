/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Returns {
    @Id
    @GeneratedValue
    private int id;
    private String RMA;
    private String reason;
    private int storeID;
    private ReturnType type;

    public Returns(){}

    public int getID(){
        return id;
    }

    public String getRMA(){
        return RMA;
    }
    public void setRMA(String RMA){
        this.RMA = RMA;
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

    public ReturnType getType(){
        return type;
    }
    public void setType(ReturnType type){
        this.type = type;
    }
}
