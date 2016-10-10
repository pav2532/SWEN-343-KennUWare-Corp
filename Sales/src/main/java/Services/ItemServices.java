/**
 * Created by John King on 07-Oct-16.
 */

package Services;

import Sales.*;
import Sales.Wearables.*;

public class ItemServices {
    //Gets a catalog of all items that can be sold
    public static Catalog getCatalog(){
        return new Catalog();
    }

    //Gets a previously sold wearable for customer support
    //Change to pull a wearable from the database based off idNumber
    public static Wearable getWearable(int idNumber){
        return null;
    }
}
