
package human_resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class DataMapper {
    
    public void insertEmployee(ArrayList<Employee> employees) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HR_Manufacturing",
                    "postgres", "Lacrosse9$$$");
            if (con != null) {
                System.out.println("Connected");
            }

            Statement st = con.createStatement();
            
            String sql = "insert into employee values(";
           
            for (int i = 0; i < employees.size(); i++) {
                sql += 0 + ",";
                sql += "'" + employees.get(i).getName() + "',";
                sql += employees.get(i).getSalary() + ",";
                sql += "'" + employees.get(i).getAddress() + "',";
                sql += "'" + employees.get(i).getPhone() + "',";
                sql += "'" + employees.get(i).getEmail() + "',";
                sql += "'" + employees.get(i).getTitle() + "',";
                sql += "'" + employees.get(i).getDepartment() + "'";
                sql += ")";
                st.execute(sql);
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

}
