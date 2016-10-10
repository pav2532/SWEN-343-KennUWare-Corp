package com.kennuware.sales.domain.Employees;


public class RegionalManager {

    private String name;
    private String id;
    private String type;

    public RegionalManager(String name){
        this.name = name;
        // this.id = id;
        this.type = "RegionalManager";
    }


    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

}