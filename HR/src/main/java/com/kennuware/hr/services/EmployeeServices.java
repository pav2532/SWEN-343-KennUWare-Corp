package com.kennuware.hr.services;

import com.google.gson.Gson;
import com.kennuware.hr.resources.Employees.Employee;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by austin on 10/12/16.
 */
public class EmployeeServices {
    //Called when someone logs in
    //Search through database for employee, check if password is right
    public static Employee login(String username, String password, Session dbSession){
        Employee employee = new Employee();
        Gson gson = new Gson();

        Query query = dbSession.getNamedQuery("findEmployeeByEmployeeName").setString("name", username);
        List<Employee> results = (List<Employee>)query.list();

        // Should only be one result
        for(Employee e: results) {
            if(e.authenticate(password)) {
                return e;
            }
        }

        // Fine for now, but should eventually use http status codes instead
        return null;
    }
}
