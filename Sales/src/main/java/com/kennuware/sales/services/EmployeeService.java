/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.data.HibernateUtil;
import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.ItemOrders;
import com.kennuware.sales.domain.Employees.*;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class EmployeeService {
	
	private static SessionFactory factory;
	
    //Called when someone logs in
    //Search through database for employee, check if password is right
    public static Employee login(String username, Session dbSession){
        Employee employee = new Employee();
        Gson gson = new Gson();

        Query query = dbSession.getNamedQuery("findEmployeeByEmployeeName").setString("name", username);
        List<Employee> results = (List<Employee>)query.list();
        // Should only be one result
        if(results.size() > 0) {
            return results.get(0);
        }

        // Fine for now, but should eventually use http status codes instead
        return null;
    }

    //Called when a list of employees is needed
    //Get all employees and return an Arraylist of them
    public static ArrayList<Employee> getEmployees(Session session){
        
    	
        ArrayList<Employee> employees = null;
        
        	
        employees = (ArrayList<Employee>) session.getNamedQuery("findAllEmployees").list();
        	
        
        for (Iterator iterator = 
                employees.iterator(); iterator.hasNext();){
        	Employee employee = (Employee) iterator.next(); 
        	System.out.print("Name: " + employee.getName()); 
        	System.out.println("  Eid: " + employee.getEid()); 
        }
    	return employees;
    }

    ////Called when a list of employees is needed from a region
    //Get all employees and return an Arraylist of them
    public static ArrayList<Employee> getEmployees(String region, Session session){
    	        
        ArrayList<Employee> employees = new ArrayList<>();
        Integer regionID = Integer.parseInt(region);
        
//        regionID = ((Region) session.createQuery("FROM Region r"
//        		+ "WHERE r.name='" + region + "'").list().get(0)).getId();
        
        employees = (ArrayList<Employee>) session.getNamedQuery("findEmployeeByRegionId")
        		.setString("regionID", region).list();
        
    	return employees;
    }

    ////Called when a list of employees is needed from a specific store
    //Get all employees and return an Arraylist of them
    public static ArrayList<Employee> getEmployees(String region, String store, Session session){
    	        
        ArrayList<Employee> employees = new ArrayList<>();
        Integer regionID = Integer.parseInt(region);
        
//        regionID = ((Region) session.createQuery("FROM Region r"
//        		+ "WHERE r.name='" + region + "'").list().get(0)).getId();
        
        employees = (ArrayList<Employee>) session.getNamedQuery("findStoreByRegionIdAndName")
        		.setString("regionID", region).setString("store", store).list();
        
    	return employees;
    }

    //Called when revenue from an employee is needed
    //Search through database for specific employee, and return their revenue
    public static double getEmployeeRevenue(String id, Session session){
        double result = 0;
        ArrayList<Integer> orderIds = null;
        Query itemOrders = null;
        ArrayList<Item> items = null;
        int eid = Integer.parseInt(id);
        
    	
        itemOrders = session.getNamedQuery("findAllItemOrders");
        
        orderIds = (ArrayList<Integer>) session.getNamedQuery("findOrderIdsByEmployeeID")
        		.setString("eid", id).list();
        items = (ArrayList<Item>)session.getNamedQuery("findAllItems").list();
        
        int quantity = 0;
        int itemid = 0;
        double unitPrice = 0;
        for(Iterator it = itemOrders.iterate();it.hasNext();){
        	ItemOrders itemorder = (ItemOrders) it.next();
        	itemid = itemorder.getItemId();
        	if( orderIds.contains(itemorder.getOrderId())){
        		quantity = itemorder.getQuantity();
        		Iterator iterator = items.iterator();
        		
        		while(iterator.hasNext()){
        			Item item = (Item)iterator.next();
        			if( item.getId() == itemid){
        				unitPrice = item.getUnitPrice();
        				result += quantity * unitPrice;
        			}
        		}
        	}
        }
        
    	return result;
    }
    
    public static double getRegionRevenue(String region, Session session){
    	double result = 0;
    	for(Employee eid: getEmployees(region, session)){
    		result += getEmployeeRevenue(Integer.toString(eid.getEid()), session);
    		
    	}
    	return result;
    }
    
    public static double getTotalRevenue(Session session){
    	double result = 0;
    	for(Employee eid: getEmployees(session)){
    		result += getEmployeeRevenue(Integer.toString(eid.getEid()), session);
    		
    	}
    	return result;
    }

    public static Boolean verifyEmployee(HttpUtils utils, int eid){
        String responseBody = "";

        responseBody = utils.get("http://localhost:8002/verifySalesEID/" + eid);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);
        if(responseBody.length() > 1){
            return true;
        }else {
            return false;
        }

    }
}