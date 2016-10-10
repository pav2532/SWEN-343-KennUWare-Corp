package com.kennuware.sales.domain.Employees;

import javax.persistence.*;

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

    public void setName(String name) {
        this.name = name;
    }

}
