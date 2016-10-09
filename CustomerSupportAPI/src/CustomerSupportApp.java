import static spark.Spark.*;


public class CustomerSupportApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* Accounting gets return refunds from customer support
		 * GET
		 *
		 */
		get("/refund", (req, res) -> {
			float refund = 0;
			res.type("text/json");
			return "{\"refund\":\"" + refund + "\"}";
		});
		
		/* Manufacturing sends Customer Support a repaired wearable
		 * POST
		 *
		 */
		post("/sendrepair", (req, res) -> {
			return "";
		});
		
		/* Accounting retrieves all general expenses from Silos
		 * GET
		 *
		 */
		get("/customerSupport/expenses", (req, res) -> {
			float expenses = 0;
			res.type("text/json");
			return "{\"expenses\":\"" + expenses + "\"}";
		});
	}

}
