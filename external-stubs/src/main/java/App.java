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

        get("/test", (req, res) -> {
            return new TestObject();
        }, gson::toJson);

        get("verifySalesEID/" + eid + "/", (req, res) -> {
            double revenue = 0;
            String rid = req.params(":rid");
            revenue = EmployeeServices.verifySalesEmployee();
            res.type("text/json");
            return "{\"revenue\":\"" + revenue + "\"}";
        });

        get("verifyCustomerSupportEID/" + eid + "/", (req, res) -> {
            double revenue = 0;
            String rid = req.params(":rid");
            revenue = EmployeeServices.verifyCustomerSupportEmployee();
            res.type("text/json");
            return "{\"revenue\":\"" + revenue + "\"}";
        });
    }
}
