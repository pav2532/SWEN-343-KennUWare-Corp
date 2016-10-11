/**
 * Created by John King on 10-Oct-16.
 */

package com.kennuware.customersupport.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = "findRefunds", query = "select r.refund from Refund r"),
})

@Entity
@Table
public class Refund {
    @Id
    @GeneratedValue
    private int id;
    private String RMA;
    private double refund;

    public Refund(){}

    public double getRefund() {
        return refund;
    }
    public void setRefund(double refund) {
        this.refund = refund;
    }

    public String getRMA() {
        return RMA;
    }
    public void setRMA(String RMA) {
        this.RMA = RMA;
    }

    public int getId() {
        return id;
    }
}
