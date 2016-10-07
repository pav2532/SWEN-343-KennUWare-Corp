package Sales;

import java.util.HashMap;

/**
 * Created by benjamin on 10/7/16.
 */
public class ShoppingCart {

    private String customerName;
    private String creditCardNumber;
    private int value = 0;
    private HashMap<Integer, Wearable> currentItems;

    public ShoppingCart(){
        this.currentItems = new HashMap<Integer, Wearable>();
    }

    public void addProducts(int uid, Wearable product, int amount){
        if(!currentItems.containsKey(uid)){
            if(amount < 0){
                currentItems.put(uid, product);
                currentItems.get(uid).addProduct(0);
            }else{
                currentItems.put(uid, product);
                currentItems.get(uid).addProduct(amount);
                value = value + product.calculatePrice();
            }
        }else{
            if((currentItems.get(uid).getQuantity() + amount) < 0){
                value = value - currentItems.get(uid).getQuantity()*product.calculatePrice();
                currentItems.get(uid).setProduct(0);
            }
            currentItems.get(uid).addProduct(amount);
            value = value + product.calculatePrice()*amount;
        }
    }

    public void emptyCart(){
        currentItems.clear();
        value = 0;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCreditCardNumber(String creditCardNumber){
        //implement luhn algorithm
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardNumber(){
        return creditCardNumber;
    }

}
