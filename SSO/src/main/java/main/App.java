package main;

/**
 * Created by Ryan on 10/24/2016.
 */


import com.google.gson.Gson;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        Gson gson = new Gson();

        port(8004);

        post("/login", (req, res) -> {
            return "Test string";
        }, gson::toJson);
    }
}
