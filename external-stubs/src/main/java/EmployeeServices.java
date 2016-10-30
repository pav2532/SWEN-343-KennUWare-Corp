import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
