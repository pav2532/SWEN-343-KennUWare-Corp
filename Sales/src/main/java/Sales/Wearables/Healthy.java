package Sales.Wearables;

/**
 * Created by benjamin on 10/9/16.
 */
public class Healthy {
    private int uid;
    private int quantity;

    public Healthy(){
        this.uid = 321;
        this.quantity = 0;
    }

    public Double calculatePrice(){
        return 2.0;
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
