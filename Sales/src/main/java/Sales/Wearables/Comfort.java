package Sales.Wearables;

import java.util.HashMap;

public class Comfort {
    private int uid;
    private int quantity;
    private HashMap<String, Double> components = new HashMap<String, Double>();

    public Comfort(){
        this.uid = 878;
        this.quantity = 0;
        components.put("Comfort Band", 25.00);
        components.put("Circuit Board", 50.00);
        components.put("Standard Screen", 20.00);
        components.put("Standard Case", 10.00);
    }

    public Double calculatePrice(){
        Double value = 0.0;

        for(String k : components.keySet()){
            value += components.get(k);
        }

        return value;
    }

    public void addProduct(int amount){
        quantity = quantity + amount;
    }
    public void setProduct(int amount){
        quantity = amount;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getuid(){
        return uid;
    }

}
