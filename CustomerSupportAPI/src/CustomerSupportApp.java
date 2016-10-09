import static spark.Spark.get;


public class CustomerSupportApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		get("/goodbye", (req, res) -> "Goodbye World");
	}

}
