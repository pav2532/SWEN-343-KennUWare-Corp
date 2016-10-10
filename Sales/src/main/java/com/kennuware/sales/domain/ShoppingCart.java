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

    public String setCreditCardNumber(String creditCardNumber){
        if(creditCardNumber.length() != 16 ){
            return "Invalid Length";
        }

        int sum = 0;
        boolean alternate = false;

        for (int i = creditCardNumber.length() - 1; i >= 0; i--){
            int n = Integer.parseInt(creditCardNumber.substring(i, i + 1));
            if (alternate){
                n = n*2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        if(sum % 10 == 0) {
            this.creditCardNumber = creditCardNumber;
            return "Credit Card Number is Valid";
        }else{
            return "Invalid Credit Card Number";
        }
    }

    public String getCreditCardNumber(){
        return creditCardNumber;
    }

    public Double getValue(){
        return value;
    }

}
