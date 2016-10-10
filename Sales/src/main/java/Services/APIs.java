/**
 * Created by John King on 09-Oct-16.
 */

package Services;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;

public class APIs {
    public static void main(String[] args) {
        get("/login", (req, res) -> EmployeeServices.login(new Gson().fromJson(req.body(), JsonObject.class).get("username").toString(),
                new Gson().fromJson(req.body(), JsonObject.class).get("password").toString()));
    }
}
