package PracticeJDBC;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Practice1 {
    public static void main(String[] args) throws ClassNotFoundException{

        String url = "jdbc:mysql://localhost:3306/practice1";
        String username = "root";
        String password = "jaykum1210";
        String query = "select * from employee";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Done");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Done");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                String name  = rs.getString("name");
                String job_title = rs.getString("job_title");
                Double salary = rs.getDouble("salary");

                System.out.println("Id : " + id);
                System.out.println("Name : " + name);
                System.out.println("Job Title : " + job_title);
                System.out.println("Salary : " + salary);
                System.out.println("-----------------");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
