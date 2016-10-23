/**
 * Created by Ryan on 10-Oct-16.
 */

package com.kennuware.customersupport.domain.Employees;

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

    public void setEid(Integer id) {
        this.eid = id;
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

    public Integer getId() {
        return eid;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

}

