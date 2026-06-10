package PracticeJDBC;

import java.sql.*;

public class Practice2 {
    public static void main(String[] args) throws ClassNotFoundException{

        String url = "jdbc:mysql://localhost:3306/practice1";
        String username = "root";
        String password = "jaykum1210";
        String query = "insert into employee(id,name,job_title,salary) VALUES(3,'jaykumawat','Software Developer',25000.0);";

        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
            int rowsaffescted = stmt.executeUpdate(query);
            System.out.println(rowsaffescted);
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String job_title = rs.getString("job_title");
//                Double salary = rs.getDouble("salary");
//
//                System.out.println("Id : " + id);
//                System.out.println("Name : " + name);
//                System.out.println("Job Title : " + job_title);
//                System.out.println("Salary : " + salary);
//                System.out.println("---------------------------------------");
//            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
