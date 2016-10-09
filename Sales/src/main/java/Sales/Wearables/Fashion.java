package Sales.Wearables;

/**
 * Created by benjamin on 10/9/16.
 */
public class Fashion {
    private int uid;
    private int quantity;

    public Fashion(){
        this.uid = 645;
        this.quantity = 0;
    }

    public Double calculatePrice(){
        return 3.0;
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
