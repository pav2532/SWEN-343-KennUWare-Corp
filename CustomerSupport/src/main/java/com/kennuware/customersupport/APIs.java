/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.*;
import com.kennuware.customersupport.domain.Employees.CredentialDTO;
import com.kennuware.customersupport.services.EmployeeService;
import com.kennuware.customersupport.services.ItemService;
import com.kennuware.customersupport.services.OrderService;
import com.kennuware.customersupport.services.ReturnTicketService;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;





import java.util.Iterator;
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

		before((req, res) -> {
			String user = req.cookie("user");
			String sessionID = req.cookie("sessionID");
			String useAuth = req.cookie("using");
			System.out.println("Authenticating user: " + user + "  with sessinID=" + sessionID);
			String result = verifyUser(user, sessionID);
			System.out.println("Result from verify: " + result);
			System.out.println("Result equals valid: " + result.equals("valid"));
			System.out.println("Using auth: " + useAuth);
			if (useAuth != null && !result.equals("valid")) {
				result = "\"" + result + "\"";
				halt(400, result);
			}
		});

        post("/login", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").toString();
            String password = json.get("password").toString();
            username = username.substring(1,username.length()-1);
            password = password.replace("\"", "");
            return EmployeeService.login(username, session);
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
        
        get("/getMostReturnedModel/wDenied", (req, res) -> {
        	res.type("text/json");
        	return ReturnTicketService.getMostReturnedModel(session, true);
        });
        
        get("/getMostReturnedModel", (req, res) -> {
        	res.type("text/json");
        	return ReturnTicketService.getMostReturnedModel(session, false);
        });
        
        get("/getLeastReturnedModel/wDenied", (req, res) -> {
        	res.type("text/json");
        	return ReturnTicketService.getLeastReturnedModel(session, true);
        });

        get("/getLeastReturnedModel", (req, res) -> {
        	res.type("text/json");
        	return ReturnTicketService.getLeastReturnedModel(session, false);
        });
        
        get("/getHighestReturnsReason", (req, res) -> {
        	res.type("text/json");
        	return ReturnTicketService.getHighestReturnsReason(session);
        });
        
        get("/getReturnsWith/:type", (req, res) -> {
        	int type = Integer.parseInt(req.params(":type"));
        	return ReturnTicketService.getReturnsWith(session, type);
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

	private static String verifyUser(String username, String sessionID) {

		CredentialDTO cred = new CredentialDTO(username, sessionID);
		HttpUtils util = new HttpUtils();
		String result = util.post(cred, "http://127.0.0.1:8003/verify-session");
		result = result.replaceAll("\"", "");
		return result;
	}
}

