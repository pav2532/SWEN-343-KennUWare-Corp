package Sales.Wearables;

/**
 * Created by benjamin on 10/7/16.
 */
public interface Wearable {

    void addProduct(int amount);
    void setProduct(int amount);
    Double calculatePrice();
    int getQuantity();
    int getuid();
}
