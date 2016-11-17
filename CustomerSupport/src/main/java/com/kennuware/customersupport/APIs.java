/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.*;
import com.kennuware.customersupport.services.EmployeeService;
import com.kennuware.customersupport.services.ItemService;
import com.kennuware.customersupport.services.OrderService;
import com.kennuware.customersupport.services.ReturnTicketService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class APIs {
    public static void main(String[] args) {

    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();

        HttpUtils utils = new HttpUtils();

        // Set the port number
        // This must be run before any routes are defined
        port(8001);

        Gson gson = new Gson();

        post("/login", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").toString();
            String password = json.get("password").toString();
            username = username.substring(1,username.length()-1);
            password = password.replace("\"", "");
            return EmployeeService.login(username, password, session);
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
            return ReturnTicketService.returnRequest(customerName, customerAddress, reason, storeID, itemID, session);
        }, gson::toJson);

        post("/requestStatus", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String returnID = json.get("returnID").toString();
            String status = json.get("status").toString();
            returnID = returnID.substring(1,returnID.length()-1);
            status = status.substring(1,status.length()-1);
            return EmployeeService.changeStatus(returnID, status, session);
        }, gson::toJson);

        get("/getTotalRefunds", (req, res) -> {
            Double refunds = 0.0;
            List<Refund> list = session.createCriteria(Refund.class).list();
            for(Refund r:list){
                refunds += r.getRefund();
            }
            res.type("text/json");
            return "{\"totalRefunded\":\"" + Math.round(refunds*100.00)/100.00 + "\"}";
        });
        
        get("/getMostRefundedModel/wDenied", (req, res) -> {
        	
        	return "";
        });
        
        get("/getMostRefundedModel", (req, res) -> {
        	
        	return "";
        });
        
        get("/getLeastRefundedModel/wDenied", (req, res) -> {
        	
        	return "";
        });

        get("/getLeastRefundedModel", (req, res) -> {
        	
        	return "";
        });
        
        get("/getMostRefundedReason", (req, res) -> {
        	
        	return "";
        });
        
        get("/getRefundsWith/:status", (req, res) -> {
        	
        	return "";
        });

        post("/markReceived", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String returnID = json.get("returnID").toString();
            returnID = returnID.substring(1,returnID.length()-1);
            return EmployeeService.markReceived(returnID, session);
        }, gson::toJson);

        post("/resolve", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String returnID = json.get("returnID").toString();
            String itemID = json.get("itemID").toString();
            returnID = returnID.substring(1,returnID.length()-1);
            return EmployeeService.resolve(returnID, itemID, session);
        }, gson::toJson);

        get("/getReturns", (req, res) -> {
           return ReturnTicketService.getTickets(session);
        }, gson::toJson);
    }
}
