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

        post("/refurbished", (req, res) -> {
            res.status(200);
            return res;
        });

        post("/order/refurbished", (req, res) -> {
            res.status(200);
            return res;
        });

        post("/order/warranty", (req, res) -> {
            res.status(200);
            return res;
        });
    }
}
