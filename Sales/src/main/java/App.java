import static spark.Spark.*;
public class App {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        get("/hello", (req, res) -> "Hello World");
    }

}