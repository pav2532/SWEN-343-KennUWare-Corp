/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.domain.Employees.Employee;

public class EmployeeServices {
    //Called when someone logs in
    //Search through database for employee, check if password is right
    public static String login(String username, String password){

        // TODO: get employee from database with matching username
        // call employee.authenticate(password) and return the employee data if it is valid
        Employee employee = new Employee();
        Gson gson = new Gson();
//        if((username.equals("Timmy") & password.equals("password"))) {
//            return gson.toJson(new SalesRep(username));
//        } else if((username.equals("Bobby") & password.equals("rainbows"))) {
//            return gson.toJson(new RegionalManager(username));
//        } else if((username.equals("Felicia") & password.equals("Something"))) {
//            return gson.toJson(new GeneralManager(username));
//        }
//        else{ // fail case
        return null;
        // }
    }

    
}
