/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.customersupport.services;

import com.kennuware.customersupport.domain.Employees.EmployeeType;
import com.kennuware.customersupport.domain.Returns;
import org.hibernate.Session;

public class ReturnTicketServices {
    //Amount of money refunded to customers
    public static double returnLosses(){return 0;}

    public static Returns returnRequest(String customerName, String customerAddress, String RMA, String type, String reason, int storeID, Session dbSession){

        return null;
    }

    //Get a fixed wearable from Manufacturing
    //Change to return a wearable, maybe.  Not sure about implementation yet
    public static void receiveWearable(){}
}
