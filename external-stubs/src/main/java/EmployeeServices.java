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

    public static String getEmployeeRevenue(int eid){
        String responseBody = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {
                HttpGet httpget = new HttpGet("http://localhost:8000/revenue/employee/" + eid);

                System.out.println("Executing request " + httpget.getRequestLine());

                // Create a custom response handler
                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    }
                };
                responseBody = httpclient.execute(httpget, responseHandler);
                System.out.println("----------------------------------------");
                System.out.println(responseBody);
            } finally {
                httpclient.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return responseBody;
    }
}
