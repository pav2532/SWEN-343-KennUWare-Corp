/**
 * Created by Ryan on 10/24/2016.
 */


import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class App {

    public static void main(String[] args) {

        Gson gson = new Gson();

        port(8002);

        System.out.println("\nEmployee Revenue Tests");
        EmployeeServices.getEmployeeRevenue(0);
        EmployeeServices.getEmployeeRevenue(4);

        get("/test", (req, res) -> {
            return new TestObject();
        }, gson::toJson);

        get("verifySalesEID/:eid/", (req, res) -> {
            boolean exists;
            String eid = req.params(":eid");
            exists = EmployeeServices.verifySalesEmployee(Integer.parseInt(eid));
            res.type("text/json");
            return "{\"exists\":\"" + exists + "\"}";
        });

        get("verifyCustomerSupportEID/:eid/", (req, res) -> {
            boolean exists;
            String eid = req.params(":eid");
            exists = EmployeeServices.verifyCustomerSupportEmployee(Integer.parseInt(eid));
            res.type("text/json");
            return "{\"exists\":\"" + exists + "\"}";
        });
    }
}
