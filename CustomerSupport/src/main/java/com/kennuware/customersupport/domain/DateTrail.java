/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class DateTrail {
    @Id
    @GeneratedValue
    private int id;
    private int returnsID;
    private String requestDate;
    private String approveDenyDate;
    private String receiveDate;
    private String resolveDate;

    public DateTrail(){}

    public int getId() {
        return id;
    }

    public String getRequestDate() {

        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getApproveDenyDate() {
        return approveDenyDate;
    }
    public void setApproveDenyDate(String approveDenyDate) {
        this.approveDenyDate = approveDenyDate;
    }

    public String getResolveDate() {
        return resolveDate;
    }
    public void setResolveDate(String resolveDate) {
        this.resolveDate = resolveDate;
    }

    public int getReturnsID() {
        return returnsID;
    }
    public void setReturnsID(int returnsID) {
        this.returnsID = returnsID;
    }
}
