package human_resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Employee {
    
	private String name;
	private Job section;
	private int salary;
	private String address;
	private String phone;
	private String email;
	private String status;
	private ArrayList<Employee> superiors;
	private ArrayList<Employee> subordinates;
	private String title;
	private String department;
	
	public Employee() {
		DataMapper dm = new DataMapper();
                name = "";
		section = new Job();
		salary = 0;
		address = "";
		phone = "";
		email = "";
		status = "";
		superiors = new ArrayList<Employee>();
		subordinates = new ArrayList<Employee>();
		title = "";
		department = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getSection() {
		return section;
	}

	public void setSection(Job section) {
		this.section = section;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Employee> getSuperiors() {
		return superiors;
	}

	public void setSuperiors(ArrayList<Employee> superiors) {
		this.superiors = superiors;
	}

	public ArrayList<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(ArrayList<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	//For Testing
	public String toString() {
		return "Employee [name=" + name + ", section=" + section + ", salary=" + salary + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", status=" + status + ", superiors=" + superiors
				+ ", subordinates=" + subordinates + ", title=" + title + ", department=" + department + "]";
	}
	
	public static void main(String[] args){
		try{
			Class.forName("org.postgresql.Driver");
                        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HR_Manufacturing",
                                "postgres", "Lacrosse9$$$");
                        if(con != null)
                            System.out.println("Connected");
                        
                        Statement st = con.createStatement();
                        String sql = "insert into employee values(1,'Joe',90000,'10 Cool Street','1234567890','johnboy@KennUWARE.com','Developer','Software')";
                        
                        st.execute(sql);
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
	}
	
}
