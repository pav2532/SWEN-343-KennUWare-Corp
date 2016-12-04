/**
 * Created by John King on 09-Oct-16.
 */

package com.kennuware.sales;

import com.google.gson.JsonArray;
import com.kennuware.sales.Utilities.HttpUtils;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.ItemOrders;
import com.kennuware.sales.services.EmployeeService;
import com.kennuware.sales.services.OrderServices;
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

		// Set the port
		// This must be done before any routes are defined
		port(8000);
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
        
        /* Gets revenue from a region
		 * GET
		 *
		 */
		get("/revenue/region/:rid", (req, res) -> {
			double revenue = 0;
			String rid = req.params(":rid");
			revenue = EmployeeService.getRegionRevenue(rid, session);
			res.type("text/json");
			return "{\"revenue\":\"" + revenue + "\"}";
		});

		get("/revenue/store/:sid", (req, res) -> {
			double revenue = 0;
			String sid = req.params(":sid");
			revenue = EmployeeService.getStoreRevenue(sid, session);
			res.type("text/json");
			return "{\"revenue\":\"" + revenue + "\"}";
		});

		/* Gets revenue from a salesperson
		 * HR gets revenue to calculate commissions weekly
		 * GET
		 *
		 */
		get("/revenue/employee/:eid", (req, res) -> {
			double revenue = 0;
			String eid = req.params(":eid");
			if(EmployeeService.verifyEmployee(utils, Integer.parseInt(eid))){
				revenue = EmployeeService.getEmployeeRevenue(eid, session);
				res.type("text/json");
				return "{\"revenue\":\"" + revenue + "\"}";
			}else{
				return "{\"revenue\":\"" + -1.0 + "\"}";
			}
		});
		
		/* Accounting gets revenue from Sales
		 * GET
		 *
		 */
		get("/totalSales", (req, res) -> {
			double revenue = 0;
			try{
				revenue = EmployeeService.getTotalRevenue(session);
			} catch(Exception e){
				e.printStackTrace();
			}
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
			double expenses = 0;
			res.type("text/json");
			return "{\"expenses\":\"" + expenses + "\"}";
		});

		post("/order", (req, res) -> {
			String body = req.body();
			JsonObject json = gson.fromJson(body, JsonObject.class);
			int employeeID = json.get("employeeID").getAsInt();
			String customerName = json.get("custName").getAsString();
			String creditCardNumber = json.get("creditCardNumber").getAsString();
			String expirationDate = json.get("expirationDate").getAsString();
			String address = json.get("custAddress").getAsString();
			String date = json.get("date").getAsString();
			Double bulkDiscount = json.get("bulkDiscount").getAsDouble();
			JsonArray requestedProducts = json.get("requestedProducts").getAsJsonArray();
			int check = OrderServices.completeSaleOrder(customerName, employeeID, creditCardNumber,
					expirationDate, bulkDiscount, address, date, session);

			if (check != -1) {
				JsonObject item;
				for (int i = 0; i < requestedProducts.size(); i++) {
					item = requestedProducts.get(i).getAsJsonObject();

					OrderServices os = new OrderServices();

					OrderServices.addItemOrders(check, item.get("itemID").getAsInt(), item.get("quantity").getAsInt(), session);

					ItemOrders order = new ItemOrders();
					order.setQuantity(item.get("quantity").getAsInt());
					order.setOrderId(item.get("itemID").getAsInt());
					order.setOrderId(item.get("orderID").getAsInt());

					os.orderItemsFromInventory(address, order, customerName, utils);
				}
			}

			return check;
		}, gson::toJson);

		post("/getPrice", (req, res) -> {
			String body = req.body();
			JsonObject json = gson.fromJson(body, JsonObject.class);

			int id = json.get("id").getAsInt();

			List<Item> list = session.createCriteria(Item.class).list();
			System.out.println(id);
			for (Item i : list) {
				if (i.getId() == id) {
					return i.getUnitPrice();
				}
			}
			return 0.0;
		}, gson::toJson);

		get("/getAllItems", (req, res) -> {
			return session.createCriteria(Item.class).list();
		}, gson::toJson);
		get("/highestseller",(req, res) -> {
			return OrderServices.getHighestOrder(session);
		}, gson::toJson);
		get("/lowestseller",(req, res) -> {
			return OrderServices.getLowestOrder(session);
		}, gson::toJson);
		get("/revenuByModel",(req, res) -> {
			return OrderServices.getRevenueByModel(session);
		}, gson::toJson);

    }
}
