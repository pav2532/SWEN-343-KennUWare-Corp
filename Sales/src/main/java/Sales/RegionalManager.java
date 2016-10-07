package Sales;

/**
 * Created by benjamin on 10/7/16.
 */
public class RegionalManager implements Employee {

    private String name;
    private String id;

    public RegionalManager(String name, String id){
        this.name = name;
        this.id = id;
    }


    //Checks out the current shopping cart
    public void checkout(Double bulkDiscount){

    }

    public String getid(){
        return id;
    }

    public String getName(){
        return name;
    }

}
