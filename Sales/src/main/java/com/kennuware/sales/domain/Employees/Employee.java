package com.kennuware.sales.domain.Employees;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String type;
    private String password;

    public Employee(String name, String type, String password) {
        this.name = name;
        this.type = type;
        this.password = password;
    }

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

}
