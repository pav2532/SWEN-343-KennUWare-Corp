package Sales;

import Sales.Wearables.Wearable;

import java.util.HashMap;

/**
 * Created by benjamin on 10/9/16.
 */
public class Catalog {

    private HashMap<Integer, Wearable> stock;

    public Catalog(){
        stock = new HashMap<Integer, Wearable>();
    }

    public Wearable getWearable(int uid){
        return stock.get(uid);
    }

    public void changeStockAmount(int uid, int change){
        stock.get(uid).addProduct(change);
    }

    public void restock(Wearable newStock, int amount){
        if(stock.containsKey(newStock.getuid())){
            stock.get(newStock.getuid()).addProduct(amount);
        }else{
            stock.put(newStock.getuid(), newStock);
            stock.get(newStock.getuid()).addProduct(amount);
        }
    }

}
