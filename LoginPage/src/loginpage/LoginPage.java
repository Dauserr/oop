package loginpage;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;

    public LoginPage() {
        setTitle("Login Page");
        setSize(320, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Инициализация компонентов
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 20, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(110, 20, 160, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 60, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 60, 160, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(40, 110, 100, 30);
        add(loginButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(160, 110, 100, 30);
        add(resetButton);

        // Логика кнопки "Login"
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                // Подключение к базе данных для проверки логина
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, user);
                    stmt.setString(2, pass);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        // Если пользователь найден, переходим на домашнюю страницу
                        JOptionPane.showMessageDialog(null, "Login successful");
                        dispose(); // Закрытие окна логина
                        new HomePage().setVisible(true); // Открываем HomePage
                    } else {
                        // Если логин или пароль неверные
                        JOptionPane.showMessageDialog(null, "Invalid login or password");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database connection error");
                }
            }
        });

        // Логика кнопки "Reset"
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        // Сделаем окно видимым
        setVisible(true);
    }

    public static void main(String[] args) {
        // Запускаем приложение
        new LoginPage();
    }
}
