/**
 * Created by John King on 07-Oct-16.
 */

package Services;


public class EmployeeServices {
    //Called when someone logs in
    //Change what this returns to a username and an employee type
    public void login(String username, String password){}

    //Called when a list of employees is needed
    //Change to return an array of employees
    public void getEmployees(){}

    ////Called when a list of employees is needed from a region
    //Change to return an array of employees
    public void getEmployees(String region){}

    ////Called when a list of employees is needed from a specific store
    //Change to return an array of employees
    public void getEmployees(String region, String store){}

    //Called when revenue from an employee is needed
    public double getEmployeeRevenue(/*Employee object or employee identifier*/){return 0;}
}