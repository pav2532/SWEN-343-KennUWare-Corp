package com.kennuware.sales.domain;

import com.kennuware.sales.domain.Wearables.Wearable;

import java.util.HashMap;

public class ShoppingCart {

    private String customerName;
    private String creditCardNumber;
    private Double value = 0.0;
    private HashMap<Integer, Integer> currentItems;
    private Catalog currentCatalog = new Catalog();

    public ShoppingCart(){
        this.currentItems = new HashMap<Integer, Integer>();
    }

    public void addProducts(Wearable product, int amount){

        if(!currentItems.containsKey(product.getuid())){

            if(amount < 0){

                currentItems.put(product.getuid(), 0);

            }else{

                currentItems.put(product.getuid(), amount);
                value = value + product.calculatePrice();

            }
        }else{

            if((currentItems.get(product.getuid()) + amount) < 0){

                value = value - currentItems.get(product.getuid())*product.calculatePrice();
                currentItems.put(product.getuid(), 0);

            }else {

                currentItems.put(product.getuid(), currentItems.get(product.getuid()) + amount );
                value = value + product.calculatePrice() * amount;
            }

        }
    }

    public void emptyCart(){
        currentItems.clear();
        value = 0.0;
        customerName = "None";
        creditCardNumber = "None";
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getCustomerName(){
        return customerName;
    }



    public String getCreditCardNumber(){
        return creditCardNumber;
    }

    public Double getValue(){
        return value;
    }

}
