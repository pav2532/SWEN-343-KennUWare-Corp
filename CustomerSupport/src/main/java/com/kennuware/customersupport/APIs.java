/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport;

import com.kennuware.customersupport.data.HibernateUtil;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kennuware.customersupport.domain.*;
import com.kennuware.customersupport.domain.Employees.Employee;
import com.kennuware.customersupport.domain.Employees.EmployeeType;
import com.kennuware.customersupport.services.EmployeeServices;
import com.kennuware.customersupport.domain.Employees.Region;
import org.hibernate.Query;
import com.kennuware.customersupport.services.ReturnTicketServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class APIs {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Set the port number
        // This must be run before any routes are defined
        port(8001);

//        Employee employee = new Employee();
//        employee.setName("Ryan");
//        employee.setPassword("test");
//        employee.setRegionId(1);
//        employee.setType(EmployeeType.AGENT);
//
//        Customer customer = new Customer();
//        customer.setName("George");
//        customer.setAddress("FASFDSA");
//
//        DateTrail dateTrail = new DateTrail();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        dateTrail.setApproveDenyDate(dateFormat.format(cal.getTime()));
//        dateTrail.setReceiveDate(dateFormat.format(cal.getTime()));
//        dateTrail.setRequestDate(dateFormat.format(cal.getTime()));
//        dateTrail.setReturnsID(4);
//
//        Returns returns = new Returns();
//        returns.setStoreID(2);
//        returns.setReason("Just Cause");
//        returns.setRMA("BASF3245");
//        returns.setType(ReturnType.REFUND);
//
//        Refund refund = new Refund();
//        refund.setRMA("TMDSGF");
//        refund.setRefund(52.3);
//
//        session.save(refund);
//        session.save(returns);
//        session.save(dateTrail);
//        session.save(employee);
//        session.save(customer);
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

        post("/requestReturn", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String customerName = json.get("customerName").toString();
            String customerAddress = json.get("customerAddress").toString();
            String reason = json.get("reason").toString();
            // int storeID = json.get("storeID").getAsInt();
            int storeID = 1;
            String itemID = json.get("itemId").toString();
            customerName = customerName.substring(1,customerName.length()-1);
            customerAddress = customerAddress.substring(1,customerAddress.length()-1);
            reason = reason.substring(1,reason.length()-1);
            itemID = itemID.substring(1,itemID.length()-1);
            System.out.println("Calling service");
            return ReturnTicketServices.returnRequest(customerName, customerAddress, reason, storeID, itemID, session);
        }, gson::toJson);

        post("/requestStatus", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String returnID = json.get("returnID").toString();
            String status = json.get("status").toString();
            returnID = returnID.substring(1,returnID.length()-1);
            status = status.substring(1,status.length()-1);
            return EmployeeServices.changeStatus(returnID, status, session);
        }, gson::toJson);

        get("/getTotalRefunds", (req, res) -> {
            Double refunds = 0.0;
            List<Refund> list = session.createCriteria(Refund.class).list();
            for(Refund r:list){
                refunds += r.getRefund();
            }
            return refunds;
        }, gson::toJson);

        post("/markReceived", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String returnID = json.get("returnID").toString();
            returnID = returnID.substring(1,returnID.length()-1);
            return EmployeeServices.markReceived(returnID, session);
        }, gson::toJson);

        post("/resolve", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String returnID = json.get("returnID").toString();
            returnID = returnID.substring(1,returnID.length()-1);
            return EmployeeServices.resolve(returnID, session);
        }, gson::toJson);

        get("/getReturns", (req, res) -> {
           return ReturnTicketServices.getTickets(session);
        }, gson::toJson);
    }
}