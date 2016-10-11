/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.domain.DateTrail;
import com.kennuware.customersupport.domain.Employees.Employee;
import com.kennuware.customersupport.domain.Refund;
import com.kennuware.customersupport.domain.ReturnType;
import com.kennuware.customersupport.domain.Returns;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class EmployeeServices {
    //Called when someone logs in
    //Search through database for employee, check if password is right
    public static Employee login(String username, String password, Session dbSession){
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

    public static Returns changeStatus(String returnID, String status, Session session){
        Query query = session.getNamedQuery("findReturn").setString("id", returnID);
        List<Returns> returns = (List<Returns>)query.list();

        for(Returns r: returns){
            if(status.equals("refurbish")){
                r.setType(ReturnType.REFURBISH);
            }
            else if(status.equals("refund")){
                r.setType(ReturnType.REFUND);
            }
            else{
                r.setType(ReturnType.DENIED);
            }
            session.save(r);

            Query q = session.getNamedQuery("findDateTrail").setString("returnsID", returnID);
            List<DateTrail> dateTrails = (List<DateTrail>)q.list();
            for(DateTrail d: dateTrails){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                d.setApproveDenyDate(dateFormat.format(cal.getTime()));
                session.save(d);
            }

            return r;
        }
        return null;
    }

    public static Returns markReceived(String returnID, Session session){
        Query query = session.getNamedQuery("findReturn").setString("id", returnID);
        List<Returns> returns = (List<Returns>)query.list();

        for(Returns r: returns) {
            Query q = session.getNamedQuery("findDateTrail").setString("returnsID", returnID);
            List<DateTrail> dateTrails = (List<DateTrail>)q.list();
            for(DateTrail d: dateTrails){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                d.setReceiveDate(dateFormat.format(cal.getTime()));
                session.save(d);
            }

            return r;
        }
        return null;
    }

    public static Returns resolve(String returnID, Session session){
        Query query = session.getNamedQuery("findReturn").setString("id", returnID);
        List<Returns> returns = (List<Returns>)query.list();

        for(Returns r: returns) {
            if(r.getType()==ReturnType.REFUND){
                Refund refund = new Refund();
                refund.setRMA(r.getItemID());
                refund.setRefund(100.0);//Change this to getting price from sales
                session.save(refund);
            }
            else if(r.getType()==ReturnType.REFURBISH){

            }

            Query q = session.getNamedQuery("findDateTrail").setString("returnsID", returnID);
            List<DateTrail> dateTrails = (List<DateTrail>)q.list();
            for(DateTrail d: dateTrails){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                d.setResolveDate(dateFormat.format(cal.getTime()));
                session.save(d);
            }

            return r;
        }
        return null;
    }
}
