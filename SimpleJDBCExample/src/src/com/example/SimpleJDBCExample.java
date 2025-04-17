package src.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class SimpleJDBCExample {

    public static void main(String[] args) {
        // Create the "url"
        // assume database server is running on the localhost
        String url = "jdbc:derby://localhost:1527/C:\\db\\EmployeeDB";
        String username = "public";
        String password = "tiger";


        // A try-with-resources example
        // Connection and Statement implement java.lan.AutoCloseable
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            Statement stmt = con.createStatement();

            /*insertQuery = "INSERT INTO Employee VALUES (234, 'Dowl', 'Asderay', '1954-09-21', 150400)";
            int rowsAffected = stmt.executeUpdate(insertQuery); // выполняет INSERT
            if (rowsAffected != 1) {
                System.out.println("Failed to add a new employee record");
            } else {
                System.out.println("Successfully inserted 1 row.");
            }*/

            String selectQuery = "SELECT * FROM EMPLOYEE";
            ResultSet rs = stmt.executeQuery(selectQuery);

            while (rs.next()) {
                int empID = rs.getInt("ID");
                String first = rs.getString("FIRSTNAME");
                String last = rs.getString("LASTNAME");
                Date birth_date = rs.getDate("BIRTHDATE");
                float salary = rs.getFloat("SALARY");
                System.out.println("Employee ID:   " + empID + "\n"
                        + "Employee Name: " + first.trim() + " " + last.trim() + "\n"
                        + "Birth Date:    " + birth_date + "\n"
                        + "Salary:        " + salary + "\n");

            }
        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }
        // No need to close the Connection and Statement objects, the compiler
        // will generate these for us and call the close() statement on this
        // objects in the order we obtained them in the try
    }
}