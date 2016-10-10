/**
 * Created by John King on 09-Oct-16.
 */

package com.kennuware.sales;

import com.kennuware.sales.data.HibernateUtil;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kennuware.sales.domain.Employees.Employee;
import com.kennuware.sales.domain.Employees.EmployeeType;
import com.kennuware.sales.domain.Employees.Region;
import com.kennuware.sales.domain.Store;
import com.kennuware.sales.domain.StoreEmployee;
import com.kennuware.sales.services.EmployeeServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class APIs {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//
//        Employee employee = new Employee();
//        employee.setName("Ryan");
//        employee.setPassword("test");
//        employee.setRegionId(1);
//        employee.setType(EmployeeType.GENERALMANAGER);
//
//        Region region = new Region();
//        region.setName("Northwest");
//
//        session.save(region);
//        session.save(employee);
//
//        Store store = new Store();
//        store.setRegionID(1);
//        store.setAddress("Who really cares");
//        store.setName("Best Buy");
//        session.save(store);
//
//        StoreEmployee storeEmployee = new StoreEmployee();
//        storeEmployee.setEmployeeID(1);
//        storeEmployee.setStoreID(2);
//        session.save(storeEmployee);
//
//        session.getTransaction().commit();
//
//        session.close();

        Gson gson = new Gson();

        post("/login", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").toString();
            String password = json.get("password").toString();
            username = username.substring(1,username.length()-1);
            password = password.replace("\"", "");
            return EmployeeServices.login(username, password, session);
        }, gson::toJson);

        System.out.println("Running login");

        /* Accounting gets revenue from Sales
		 * GET
		 *
		 */
		get("/revenue", (req, res) -> {
			float revenue = 0;
			res.type("text/json");
			return "{\"revenue\":\"" + revenue + "\"}";
		});
		
		/* Customer Support gets wearable object from Sales database
		 * GET
		 *
		 */
		get("/getSale/:pid", (req, res) -> {
			String pid = req.params(":pid"); 
			return pid;
		});
		
		/* Accounting retrieves all general expenses from Silos
		 * GET
		 *
		 */
		get("/sales/expenses", (req, res) -> {
			float expenses = 0;
			res.type("text/json");
			return "{\"expenses\":\"" + expenses + "\"}";
		});
		
		/* HR gets employee ID and value of commissions for salespersons made weekly
		 * GET
		 *
		 */
		get("/commissions/:eid", (req, res) -> {
			String eid = req.params(":eid");
			float commission = 0;
			res.type("text/json");
			return "{\"commission\":\"" + commission + "\"}";
		});


        System.out.println("Ran through all apis");

    }
}
