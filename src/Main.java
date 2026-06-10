import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // Database URL
        String url = "jdbc:mysql://localhost:3306/jdbctutorial";

        // Database credentials
        String username = "root";
        String password = "jaykum1210";

        // Establish the connection
        try (Connection connection =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("Connected to the database.");

            // Perform database operations here

        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}