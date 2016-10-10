package com.kennuware.sales.domain.Employees;


import javax.persistence.*;

import com.kennuware.sales.domain.SalesOrder;

import java.util.HashMap;

@Table
@Entity
public class Region {


    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Region() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public Double calculateRevenue() {
//
//        Double revenue = 0.0;
//
//        for (SalesRep s : salesRepresantatives.values()) {
//            for (SalesOrder o : s.getHistory()) {
//                //revenue += o.getValue();
//            }
//        }
//
//        return revenue;
//    }

    public void setName(String name) {
        this.name = name;
    }

}
