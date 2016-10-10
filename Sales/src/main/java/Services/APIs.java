/**
 * Created by John King on 09-Oct-16.
 */

package Services;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class APIs {
    public static void main(String[] args) {
        post("/login", (req, res) -> {
            Gson gson = new Gson();
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").toString();
            String password = json.get("password").toString();
            username = username.substring(1,username.length()-1);
            password = password.replace("\"", "");
            res.body(EmployeeServices.login(username, password));
            return res;});
    }
}
