/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.kennuware.sales.data.HibernateUtil;
import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.ItemOrders;
import com.kennuware.sales.domain.Employees.*;
import com.google.gson.Gson;
import org.hibernate.Query;
import org.hibernate.Session;

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

public class EmployeeServices {
	
	private static SessionFactory factory;
	
    //Called when someone logs in
    //Search through database for employee, check if password is right
    public static Employee login(String username, String password, Session dbSession){

        // TODO: get employee from database with matching username
        // call employee.authenticate(password) and return the employee data if it is valid
        Employee employee = new Employee();
        Gson gson = new Gson();

        Query query = dbSession.getNamedQuery("findEmployeeByEmployeeName").setString("name", username);
        List<Employee> results = (List<Employee>)query.list();
        // Should only be one result
        for(Employee e: results) {
            if(e.authenticate(password)) {
                return e;
            }
        }

        // Fine for now, but should eventually use http status codes instead
        return null;
    }

    //Called when a list of employees is needed
    //Get all employees and return an Arraylist of them
    public static ArrayList<Employee> getEmployees(){
        try{
        	Configuration conf = (new Configuration()).configure();
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    conf.getProperties()). build();
        	factory = conf.buildSessionFactory(serviceRegistry);
        } catch(Throwable ex){
        	System.out.println("Failed to create sessionFactory object." + ex);
        	throw new ExceptionInInitializerError(ex);
        }
    	Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<Employee> employees = null;
        try{
        	tx = session.beginTransaction();
        	employees = (ArrayList<Employee>) session.getNamedQuery("findAllEmployees").list();
        	tx.commit();
        }catch(HibernateException e){
        	if(tx != null) tx.rollback();
        	e.printStackTrace();
        }finally{
        	session.close();
        }
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
    public static ArrayList<Employee> getEmployees(String region){
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        
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
    public static ArrayList<Employee> getEmployees(String region, String store){
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        
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
    public static double getEmployeeRevenue(String id){
        double result = 0;
        ArrayList<Integer> orderIds = null;
        Query itemOrders = null;
        Query items = null;
        int eid = Integer.parseInt(id);
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
    	
        
        
        	
        	
        itemOrders = session.createQuery("FROM ItemOrders");
        	
        
        orderIds = (ArrayList<Integer>) session.getNamedQuery("findOrderIdsByEmployeeID")
        		.setString("eid", id).list();
        items = session.createQuery("FROM Item");
        
        
        int quantity = 0;
        int itemid = 0;
        double unitPrice = 0;
        for(Iterator it = itemOrders.iterate();it.hasNext();){
        	ItemOrders itemorder = (ItemOrders) it.next();
        	itemid = itemorder.getItemId();
        	if( orderIds.contains(itemorder.getOrderId())){
        		quantity = itemorder.getQuantity();
        		for(Iterator iterator = items.iterate();iterator.hasNext();){
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
    
    public static double getRegionRevenue(String region){
    	double result = 0;
    	for(Employee eid: getEmployees(region)){
    		result += getEmployeeRevenue(Integer.toString(eid.getEid()));
    		
    	}
    	return result;
    }
    
    public static double getTotalRevenue(){
    	double result = 0;
    	for(Employee eid: getEmployees()){
    		result += getEmployeeRevenue(Integer.toString(eid.getEid()));
    		
    	}
    	return result;
    }
    
    
    
    
}