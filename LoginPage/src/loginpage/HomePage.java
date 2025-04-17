package loginpage;
import javax.swing.*;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Home Page");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to Home Page!");
        welcomeLabel.setBounds(100, 120, 200, 25);
        add(welcomeLabel);

        setVisible(true);
    }
}
