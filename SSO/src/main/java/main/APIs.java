package main;

import main.Services.AuthenticationServices;
import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class APIs {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<String> sessionIDs = new ArrayList<>();

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
            String sessionID = AuthenticationServices.login(username, password, req, session, sessionIDs);
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
            String username = "";
            String sessionID = "";
            if(json.has("username")) {
                username = json.get("username").getAsString();
            }
            if(json.has("sessionID")) {
                sessionID = json.get("sessionID").getAsString();
            }
            if (sessionIDs.contains(sessionID)) {
                System.out.println("Returning valid");
                return "valid";
            } else {
                return "127.0.0.1:3000/kennuware/sso/login";
            }
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
