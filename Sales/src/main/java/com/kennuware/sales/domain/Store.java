/**
 * Created by John King on 10-Oct-16.
 */
package com.kennuware.sales.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "findStoreByRegionIdAndName", query = "FROM Store s "
    		+ "WHERE s.regionID = :regionID "
			+ "AND s.name = :store")
})

@Entity
@Table
public class Store {
    @Id
    @GeneratedValue
    private int storeID;
    private int regionID;
    private String name;
    private String address;

    public Store(){}

    public int getStoreID(){
        return storeID;
    }

    public int getRegionID(){
        return regionID;
    }
    public void setRegionID(int regionID){
        this.regionID = regionID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}
