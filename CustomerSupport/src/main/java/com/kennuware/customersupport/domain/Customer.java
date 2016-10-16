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
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String address;
    private String name;

    public Customer(){}

    public int getId(){
        return id;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
