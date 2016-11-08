/**
 * Created by Pedro Vega on 10/30/2016.
 */
import java.util.*;

public class Wearables {
    private ArrayList <Item> wearables;
    public Wearables() {
        wearables = new ArrayList<Item>();
        wearables.add(new Item(1, "343", "Act", 45, "yes"));
        wearables.add(new Item(2, "232", "All", 5, "no"));
        wearables.add(new Item(3, "344", "Simple", 30, "yes"));
    }

    public ArrayList getList(){
            return wearables;
    }

    class Item {
        private int id;
        private String name;
        private String type;
        private int quantity;
        private String active;

        public Item(int id, String name, String type, int quantity, String active){
            this.id = id;
            this.name=name;
            this.type=type;
            this.quantity = quantity;
            this.active=active;
        }

    }


}
