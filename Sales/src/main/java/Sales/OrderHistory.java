package Sales;

import java.util.ArrayList;

/**
 * Created by benjamin on 10/9/16.
 */
public class OrderHistory {

    private ArrayList<Order> history = new ArrayList<Order>();

    public OrderHistory(){}

    public void addOrder(Order newOrder){
        history.add(newOrder);
    }

}
