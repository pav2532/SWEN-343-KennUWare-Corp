package human_resources;

import java.util.ArrayList;

public class SalesPayroll extends Payroll{

	private float commissionPercentage;
	
	public SalesPayroll(ArrayList<Employee> employeeList) {
		super(employeeList);
		commissionPercentage = 0;
	}

	public float calculateComission(){
		//actual calculations
		
		
		return commissionPercentage;
	}
	
}
