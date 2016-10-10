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
    private Integer eid;

    private String name;
    private EmployeeType type;
    private String password;

    private Integer regionId;

    public Employee() {
    }

    public Integer getEid() {
        return eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

}
