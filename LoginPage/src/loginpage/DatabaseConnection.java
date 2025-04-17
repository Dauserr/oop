package loginpage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/login_app";
        String user = "root"; // если ты вводил root
        String password = "gaukadauka"; // поставь свой пароль

        return DriverManager.getConnection(url, user, password);
    }

}
