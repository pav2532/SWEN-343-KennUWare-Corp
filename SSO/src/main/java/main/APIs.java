package main;

import main.Services.AuthenticationServices;
import main.Utilities.HttpUtils;
import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class APIs {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        HttpUtils utils = new HttpUtils();

        // Set the port
        // This must be done before any routes are defined
        port(8003);

        Gson gson = new Gson();

        post("/login", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String password = json.get("password").getAsString();
            //username = username.substring(1,username.length()-1);
            //password = password.replace("\"", "");
            String sessionID = AuthenticationServices.login(username, password, session);
            res.cookie(username, sessionID);
            return req.cookie(username);
        }, gson::toJson);

        post("/create-user", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String password = json.get("password").getAsString();
            //username = username.substring(1,username.length()-1);
            //password = password.replace("\"", "");
            return AuthenticationServices.createUser(username, password, session);
        }, gson::toJson);

        post("/edit-user", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String newUsername = json.get("newUsername").getAsString();
            String newPassword = json.get("newPassword").getAsString();
            String sessionID = json.get("sessionID").getAsString();

            return AuthenticationServices.editUser(username, newUsername, newPassword, sessionID, session);
        }, gson::toJson);


        post("/verify-session", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String sessionID = json.get("sessionID").getAsString();

            return AuthenticationServices.verifySession(username, sessionID, session);
        }, gson::toJson);

        post("/signout", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String sessionID = json.get("sessionID").getAsString();

            res.removeCookie(username);

            return AuthenticationServices.logout(username, sessionID, session);
        }, gson::toJson);

        post("/delete-user", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String sessionID = json.get("sessionID").getAsString();

            res.removeCookie(username);

            return AuthenticationServices.deleteUser(username, sessionID, session);
        }, gson::toJson);

    }
}
