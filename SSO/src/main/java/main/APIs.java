package main;

import main.Services.AuthenticationServices;
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

        // Set the port
        // This must be done before any routes are defined
        port(8003);

        Gson gson = new Gson();

        post("/login", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String password = json.get("password").getAsString();
            String toReturn = json.get("fromURL").getAsString();
            System.out.println("toReturn: " + toReturn);
            String sessionID = AuthenticationServices.login(username, password, session);
            if(sessionID.equals("invalid")) {
                res.status(400);
                toReturn = "invalid";
            } else {
                res.cookie("sessionID", sessionID);
                toReturn += "?sessionID=" + sessionID;
                toReturn += "&username=" + username;
            }
            return toReturn;
        }, gson::toJson);

        post("/create-user", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String password = json.get("password").getAsString();

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

            return AuthenticationServices.verifySession(username, sessionID, session, res);
        }, gson::toJson);

        post("/signout", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String sessionID = json.get("sessionID").getAsString();

            res.removeCookie(username);

            return AuthenticationServices.logout(username, sessionID, session, res);
        }, gson::toJson);

        post("/delete-user", (req, res) -> {
            String body = req.body();
            JsonObject json = gson.fromJson(body, JsonObject.class);
            String username = json.get("username").getAsString();
            String sessionID = json.get("sessionID").getAsString();

            res.removeCookie(username);

            return AuthenticationServices.deleteUser(username, sessionID, session);
        }, gson::toJson);

        exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });
    }
}
