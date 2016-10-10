/**
 * Created by John King on 09-Oct-16.
 */

package com.kennuware.sales;

import com.kennuware.sales.data.HibernateUtil;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kennuware.sales.domain.Employees.Employee;
import com.kennuware.sales.domain.Employees.SalesRep;
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

        Employee employee = new Employee("Ryan", "Associate", "test");
        session.save(employee);

        Store store = new Store();
        store.setRegionID(1);
        store.setAddress("Who really cares");
        store.setName("Best Buy");
        session.save(store);

        StoreEmployee storeEmployee = new StoreEmployee();
        storeEmployee.setEmployeeID(1);
        storeEmployee.setStoreID(2);
        session.save(storeEmployee);

        session.getTransaction().commit();

        session.close();

        Gson gson = new Gson();

        post("/login", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").toString();
            String password = json.get("password").toString();
            username = username.substring(1,username.length()-1);
            password = password.replace("\"", "");
            return EmployeeServices.login(username, password);
        }, gson::toJson);
    }
}
