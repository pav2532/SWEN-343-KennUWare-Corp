
import java.io.IOException;

/**
 * Created by John King on 30-Oct-16.
 */
public class EmployeeServices {
    public static boolean verifyCustomerSupportEmployee(int eid){
        if(eid == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean verifySalesEmployee(int eid){
        if(eid == 1){
            return true;
        }
        else{
            return false;
        }
    }
}
