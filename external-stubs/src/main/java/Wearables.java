/**
 * Created by Pedro Vega on 10/30/2016.
 */
import java.util.*;

public class Wearables {
    private ArrayList <Items> listOfWearable;
    public Wearables() {
        listOfWearable = new ArrayList<Items>();
        listOfWearable.add(new Items(1, "343", "Act", 45, "yes"));
        listOfWearable.add(new Items(2, "232", "All", 5, "no"));
        listOfWearable.add(new Items(3, "344", "Simple", 30, "yes"));
    }

    public ArrayList getList(){
            return listOfWearable;
    }

    class Items{
        private int id;
        private String name;
        private String type;
        private int quantity;
        private String active;

        public Items(int id, String name, String type, int quantity, String active){
            this.id = id;
            this.name=name;
            this.type=type;
            this.active=active;
        }

    }


}
