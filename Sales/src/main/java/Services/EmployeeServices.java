/**
 * Created by John King on 07-Oct-16.
 */

package Services;

import Sales.Employees.*;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EmployeeServices {
    //Called when someone logs in
    //Search through database for employee, check if password is right
    public static String login(String username, String password){
        Gson gson = new Gson();
        if((username.equals("Timmy") & password.equals("password"))) {
            return gson.toJson(new SalesRep(username, "1", 6.8, .5, 1000.0));
        } else if((username.equals("Bobby") & password.equals("rainbows"))) {
            return gson.toJson(new RegionalManager(username, "2"));
        } else if((username.equals("Felicia") & password.equals("Something"))) {
            return gson.toJson(new GeneralManager(username, "3"));
        }
        else{ // fail case
            return null;
        }
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