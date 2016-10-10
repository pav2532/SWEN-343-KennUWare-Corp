/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport;

import com.kennuware.customersupport.data.HibernateUtil;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kennuware.customersupport.domain.Customer;
import com.kennuware.customersupport.domain.DateTrail;
import com.kennuware.customersupport.domain.Employees.Employee;
import com.kennuware.customersupport.domain.Employees.EmployeeType;
import com.kennuware.customersupport.domain.Returns;
import com.kennuware.customersupport.domain.ReturnType;
import com.kennuware.customersupport.services.EmployeeServices;
import com.kennuware.customersupport.domain.Employees.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class APIs {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("Ryan");
        employee.setPassword("test");
        employee.setRegionId(1);
        employee.setType(EmployeeType.AGENT);

        Customer customer = new Customer();
        customer.setName("George");
        customer.setAddress("FASFDSA");

        DateTrail dateTrail = new DateTrail();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        dateTrail.setApproveDenyDate(dateFormat.format(cal.getTime()));
        dateTrail.setReceiveDate(dateFormat.format(cal.getTime()));
        dateTrail.setRequestDate(dateFormat.format(cal.getTime()));
        dateTrail.setResolveDate(dateFormat.format(cal.getTime()));

        Returns returns = new Returns();
        returns.setStoreID(2);
        returns.setReason("Just Cause");
        returns.setRMA("BASF3245");
        returns.setType(ReturnType.REFUND);

        session.save(returns);
        session.save(dateTrail);
        session.save(employee);
        session.save(customer);

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

