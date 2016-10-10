package com.kennuware.sales.domain.Employees;

import com.kennuware.sales.domain.Order;

import java.util.HashMap;

public class Region {

    private HashMap<Integer, SalesRep> salesRepresantatives = new HashMap<Integer, SalesRep>();
    private RegionalManager currentManager;

    public Region(RegionalManager currentManager){
        this.currentManager = currentManager;
    }

    public void setRegionalManager(RegionalManager newRegionalManager){
        this.currentManager = newRegionalManager;
    }

    public void addSalesRep(SalesRep newSalesRep){
        salesRepresantatives.put(newSalesRep.getId(), newSalesRep);
    }

    public void removeSalesRep(String repID){
        salesRepresantatives.remove(repID);
    }

    public Double calculateRevenue(){

        Double revenue = 0.0;

        for(SalesRep s : salesRepresantatives.values()){
            for(Order o : s.getHistory()){
                revenue += o.getValue();
            }
        }

        return revenue;

    }

}
