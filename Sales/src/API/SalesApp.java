import static spark.Spark.*;
public class SalesApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* Accounting gets revenue from Sales
		 * GET
		 *
		 */
		get("/revenue", (req, res) -> {
			float revenue = 0;
			res.type("text/json");
			return "{\"revenue\":\"" + revenue + "\"}";
		});
		
		/* Customer Support gets wearable object from Sales database
		 * GET
		 *
		 */
		get("/getSale/:pid", (req, res) -> {
			String pid = req.params(":pid"); 
			return pid;
		});
		
		/* Accounting retrieves all general expenses from Silos
		 * GET
		 *
		 */
		get("/sales/expenses", (req, res) -> {
			float expenses = 0;
			res.type("text/json");
			return "{\"expenses\":\"" + expenses + "\"}";
		});
		
		/* HR gets employee ID and value of commissions for salespersons made weekly
		 * GET
		 *
		 */
		get("/commissions/:eid", (req, res) -> {
			String eid = req.params(":eid");
			float commission = 0;
			res.type("text/json");
			return "{\"commission\":\"" + commission + "\"}";
		});
		
		get("/login", (req, res) -> {
			String username;
			String empType;
			res.type("text/json");
			return "{\"username\":\"" + username + "\", \"employee type\":\""
			+ empType + "\"}";
		});
	}

}
