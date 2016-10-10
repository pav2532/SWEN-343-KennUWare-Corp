/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.kennuware.sales.domain.Employees.*;
import com.google.gson.Gson;

import java.util.ArrayList;

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

    //Called when a list of employees is needed
    //Get all employees and return an Arraylist of them
    public static ArrayList<Employee> getEmployees(){
        return null;
    }

    ////Called when a list of employees is needed from a region
    //Get all employees and return an Arraylist of them
    public static ArrayList<Employee> getEmployees(String region){
        return null;
    }

    ////Called when a list of employees is needed from a specific store
    //Get all employees and return an Arraylist of them
    public static ArrayList<Employee> getEmployees(String region, String store){
        return null;
    }

    //Called when revenue from an employee is needed
    //Search through database for specific employee, and return their revenue
    public static double getEmployeeRevenue(String id){
        return 0;
    }
}