package human_resources;

import java.util.ArrayList;

public class HRController extends Employee{

	private ArrayList<Employee> employeeList;
	private ArrayList<Employee> openings;
	
	public HRController(){
		super();
		employeeList = new ArrayList<Employee>();
		openings = new ArrayList<Employee>();
	}

	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public ArrayList<Employee> getOpenings() {
		return employeeList;
	}

	public void setOpenings(ArrayList<Employee> openings) {
		this.openings = openings;
	}
	
	public Employee getEmployee(String name){
		return employeeList.get(searchEmployees(name));
	}
	
	public void hire(String name, String address, String phone, String email, String title){
		int i = 0;
		
		while(i < openings.size()){
			if(openings.get(i).getTitle().equals(title)){
				Employee newE = openings.get(i);
				newE.setName(name);
				newE.setAddress(address);
				newE.setPhone(phone);
				newE.setEmail(email);
				newE.setStatus("Recently Hired");
				employeeList.add(newE);
				sortEmployees();
				openings.remove(i);
				return;
			}
		}
		
		System.out.println("No Openings were Found");
	}
	
	public void fire(String name){
		Employee e = employeeList.get(searchEmployees(name));
		e.setStatus("Let Go");
		e.setSalary(0);
		e.setDepartment("None");
		e.setTitle("None");
	}
	
	public void createOpening(String title, int salary, String department){
		Employee newOpening = new Employee();
		newOpening.setTitle(title);
		newOpening.setSalary(salary);
		newOpening.setDepartment(department);
		openings.add(newOpening);
	}
	
	public void generateSalary(String name){
		int i = 50000;
		i = i + ((int)(Math.random() * 950) * 1000);
		employeeList.get(searchEmployees(name)).setSalary(i);
	}
	
	public void changeSalary(String name, int newSalary){
		employeeList.get(searchEmployees(name)).setSalary(newSalary);
	}
	
	public void removeEmployee(String name){
		int i = searchEmployees(name);
		Employee e = employeeList.get(searchEmployees(name));
		if(e.getStatus() == "Let Go" || e.getStatus() == "Quit"){
			employeeList.remove(i);
		}
	}
	
	public void changePosition(String name, String newPosition){
		employeeList.get(searchEmployees(name)).setTitle(newPosition);;
	}
	
	public void changeStatus(String name, String status){
		employeeList.get(searchEmployees(name)).setStatus(status);;
	}
        
        public void Update(){
            DataMapper dm = new DataMapper();
            dm.insertEmployee(employeeList);
        }
	
	private void sortEmployees(){
		sortEmployees(employeeList);
	}
	
	private ArrayList<Employee> sortEmployees(ArrayList<Employee> sublist){
		if(sublist.size() <= 1) return sublist;
		
		ArrayList<Employee> list1 = new ArrayList<Employee>();
		ArrayList<Employee> list2 = new ArrayList<Employee>();
		ArrayList<Employee> list3 = new ArrayList<Employee>();
		int center = sublist.size() / 2;
		
		for(int i = 0; i < center; i++){
			list1.add(sublist.get(i));
		}
		
		for(int i = center + 1; i < sublist.size(); i++){
			list2.add(sublist.get(i));
		}
		
		list1 = sortEmployees(list1);
		list2 = sortEmployees(list2);
		
		for(int i = 0; i < sublist.size(); i++){
			if(list1.size() < 1){
				list3.add(list2.get(0));
				list2.remove(0);
			}
			else if(list2.size() < 1){
				list3.add(list1.get(0));
				list1.remove(0);
			}
			else {
				if(Integer.parseInt(list1.get(0).getName()) <= Integer.parseInt(list2.get(0).getName())){
					list3.add(list1.get(0));
					list1.remove(0);
				}
				else{
					list3.add(list2.get(0));
					list2.remove(0);
				}
			}
		}
		return list3;
	}
	

	private int searchEmployees(String name){
		int low = 0;
		int high = employeeList.size() - 1;

		while(high >= low) {
			int middle = (low + high) / 2;
			if(employeeList.get(middle).getName().equals(name)) {
				return middle;
			}
			if(Integer.parseInt(employeeList.get(middle).getName()) < Integer.parseInt(name)) {
				low = middle + 1;
			}
			if(Integer.parseInt(employeeList.get(middle).getName()) > Integer.parseInt(name)) {
				high = middle - 1;
			}
		}
		System.out.println("The Employee was not found");
		return -1;
	}

}
