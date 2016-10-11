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
        Customer customer = new Customer();
        Returns returns = new Returns();
        DateTrail dateTrail = new DateTrail();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        customer.setAddress(customerAddress);
        customer.setName(customerName);
        dbSession.save(customer);

        returns.setType(ReturnType.PENDING);
        returns.setStoreID(storeID);
        returns.setReason(reason);
        returns.setItemID(itemID);
        dbSession.save(returns);

        dateTrail.setRequestDate(dateFormat.format(cal.getTime()));
        dateTrail.setReturnsID(returns.getID());
        dbSession.save(dateTrail);

        return returns;
    }

    //Get a list of all the returns
    public static List<Returns> getTickets(Session session){
        Query query = session.getNamedQuery("findAllReturns");
        List<Returns> returns = (List<Returns>)query.list();
        return returns;
    }
}