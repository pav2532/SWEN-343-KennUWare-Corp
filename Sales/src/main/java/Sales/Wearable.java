package Sales;

/**
 * Created by benjamin on 10/7/16.
 */
public interface Wearable {

    void addProduct(int amount);
    void setProduct(int amount);
    int calculatePrice();
    int getQuantity();
    int getuid();
}
