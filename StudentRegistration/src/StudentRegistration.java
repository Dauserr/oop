import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentRegistration extends JFrame {

    private JTextField txtName, txtMobile, txtCourse;
    private JTable studentTable;
    private DefaultTableModel model;
    private JButton btnAdd, btnEdit, btnDelete;

    private Connection conn;

    public StudentRegistration() {
        setTitle("Student Registration");
        setLayout(new BorderLayout());

        // Создаем соединение с базой данных
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_app", "root", "gaukadauka"); // Проверь данные подключения
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Панель слева с формой для ввода данных
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));

        formPanel.add(new JLabel("Student Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Mobile:"));
        txtMobile = new JTextField();
        formPanel.add(txtMobile);

        formPanel.add(new JLabel("Course:"));
        txtCourse = new JTextField();
        formPanel.add(txtCourse);

        btnAdd = new JButton("Add");
        formPanel.add(btnAdd);

        btnEdit = new JButton("Edit");
        formPanel.add(btnEdit);

        btnDelete = new JButton("Delete");
        formPanel.add(btnDelete);

        add(formPanel, BorderLayout.WEST);

        // Панель справа с таблицей
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Mobile");
        model.addColumn("Course");

        studentTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Загружаем данные из базы в таблицу
        loadData();

        // Слушатель для добавления студента
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        // Слушатель для редактирования данных студента
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        // Слушатель для удаления студента
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        // Слушатель для выбора строки в таблице
        studentTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    txtName.setText(studentTable.getValueAt(selectedRow, 1).toString());
                    txtMobile.setText(studentTable.getValueAt(selectedRow, 2).toString());
                    txtCourse.setText(studentTable.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Загрузка данных из базы данных в таблицу
    private void loadData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students"); // Проверь, как называется таблица

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("mobile"),
                        rs.getString("course")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление студента в базу данных
    // Добавление студента в базу данных
    private void addStudent() {
        try {
            String name = txtName.getText();
            String mobile = txtMobile.getText();
            String course = txtCourse.getText();

            // Запрос для добавления нового студента
            String query = "INSERT INTO students (name, mobile, course) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, mobile);
            stmt.setString(3, course);

            // Выполняем обновление
            stmt.executeUpdate();

            // Получаем сгенерированный ID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);  // Получаем ID новой записи
                model.addRow(new Object[] {generatedId, name, mobile, course}); // Добавляем в таблицу
            }

            // Очищаем поля после добавления
            txtName.setText("");
            txtMobile.setText("");
            txtCourse.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Редактирование данных студента в базе
    private void editStudent() {
        try {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) studentTable.getValueAt(selectedRow, 0);
                String name = txtName.getText();
                String mobile = txtMobile.getText();
                String course = txtCourse.getText();

                String query = "UPDATE students SET name = ?, mobile = ?, course = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, mobile);
                stmt.setString(3, course);
                stmt.setInt(4, id);

                stmt.executeUpdate();
                model.setValueAt(name, selectedRow, 1);
                model.setValueAt(mobile, selectedRow, 2);
                model.setValueAt(course, selectedRow, 3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление студента из базы данных
    private void deleteStudent() {
        try {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) studentTable.getValueAt(selectedRow, 0);

                String query = "DELETE FROM students WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);

                stmt.executeUpdate();
                model.removeRow(selectedRow); // Удаление из таблицы
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentRegistration();
    }
}
