/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.customersupport.services;

import com.kennuware.customersupport.domain.Customer;
import com.kennuware.customersupport.domain.DateTrail;
import com.kennuware.customersupport.domain.Employees.EmployeeType;
import com.kennuware.customersupport.domain.ReturnType;
import com.kennuware.customersupport.domain.Returns;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ReturnTicketServices {
    //Amount of money refunded to customers
    public static double returnLosses(){return 0;}

    public static Returns returnRequest(String customerName, String customerAddress, String reason, int storeID, String itemID, Session dbSession){
        Returns returns = new Returns();

        returns.setType(ReturnType.PENDING);
        returns.setStoreID(storeID);
        returns.setReason(reason);
        returns.setItemID(itemID);
        dbSession.save(returns);

        makeCustomer(customerName, customerAddress, dbSession);
        makeDateTrail(returns.getID(), dbSession);

        return returns;
    }

    public static Customer makeCustomer(String customerName, String customerAddress, Session session){
        Customer customer = new Customer();
        customer.setAddress(customerAddress);
        customer.setName(customerName);
        session.save(customer);
        return customer;
    }

    public static DateTrail makeDateTrail(int id, Session dbSession){
        DateTrail dateTrail = new DateTrail();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        dateTrail.setRequestDate(dateFormat.format(cal.getTime()));
        dateTrail.setReturnsID(id);
        dbSession.save(dateTrail);
        return dateTrail;
    }

    //Get a list of all the returns
    public static List<Returns> getTickets(Session session){
        Query query = session.getNamedQuery("findAllReturns");
        List<Returns> returns = (List<Returns>)query.list();
        return returns;
    }
}