package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.example.model.Employee;
import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDAOFactory;
import java.util.Locale;


public class EmployeeTestInteractive {
    public static void main(String[] args) throws Exception {
        EmployeeDAOFactory factory = new EmployeeDAOFactory();
        EmployeeDAO dao = factory.createEmployeeDAO();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean timeToQuit = false;

        do {
            timeToQuit = executeMenu(in, dao);
        } while (!timeToQuit);
    }

    public static boolean executeMenu(BufferedReader in, EmployeeDAO dao) throws IOException {
        Employee emp;
        String action;
        int id;

        System.out.println("\n\n[C]reate | [R]ead | [U]pdate | [D]elete | [L]ist | [Q]uit: ");
        action = in.readLine();
        if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
            return true;
        }

        switch (action.toUpperCase().charAt(0)) {
            case 'C':
                emp = inputEmployee(in);
                dao.add(emp);
                System.out.println("Successfully added Employee Record: " + emp.getId());
                break;
            case 'R':
                System.out.println("Enter int value for employee id: ");
                id = Integer.parseInt(in.readLine().trim());
                emp = dao.findById(id);
                System.out.println(emp != null ? emp : "\n\nEmployee " + id + " not found");
                break;
            case 'U':
                System.out.println("Enter int value for employee id: ");
                id = Integer.parseInt(in.readLine().trim());
                emp = dao.findById(id);
                if (emp == null) {
                    System.out.println("\n\nEmployee " + id + " not found");
                    break;
                }
                emp = inputEmployee(in, emp);
                dao.update(emp);
                System.out.println("Successfully updated Employee Record: " + emp.getId());
                break;
            case 'D':
                System.out.println("Enter int value for employee id: ");
                id = Integer.parseInt(in.readLine().trim());
                emp = dao.findById(id);
                if (emp == null) {
                    System.out.println("\n\nEmployee " + id + " not found");
                    break;
                }
                dao.delete(id);
                System.out.println("Deleted Employee " + id);
                break;
            case 'L':
                Employee[] allEmps = dao.getAllEmployees();
                for (Employee employee : allEmps) {
                    System.out.println(employee + "\n");
                }
                break;
        }
        return false;
    }

    public static Employee inputEmployee(BufferedReader in) throws IOException {
        return inputEmployee(in, null, true);
    }

    public static Employee inputEmployee(BufferedReader in, Employee empDefaults) throws IOException {
        return inputEmployee(in, empDefaults, false);
    }

    public static Employee inputEmployee(BufferedReader in, Employee empDefaults, boolean newEmployee) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        int id = newEmployee ? getIdFromInput(in) : empDefaults.getId();
        String firstName = getStringInput(in, "first name", empDefaults != null ? empDefaults.getFirstName() : null);
        String lastName = getStringInput(in, "last name", empDefaults != null ? empDefaults.getLastName() : null);
        Date birthDate = getDateInput(in, df, "birth date", empDefaults != null ? empDefaults.getBirthDate() : null);
        float salary = getFloatInput(in, "salary", empDefaults != null ? empDefaults.getSalary() : 0);
        return new Employee(id, firstName, lastName, birthDate, salary);
    }

    private static int getIdFromInput(BufferedReader in) throws IOException {
        int id;
        do {
            System.out.println("Enter int value for employee id: ");
            try {
                id = Integer.parseInt(in.readLine().trim());
                if (id < 0) {
                    System.out.println("Please retry with a valid positive integer id");
                }
            } catch (NumberFormatException e) {
                id = -1;
                System.out.println("Please retry with a valid positive integer id");
            }
        } while (id < 0);
        return id;
    }

    private static String getStringInput(BufferedReader in, String field, String defaultValue) throws IOException {
        String value;
        do {
            System.out.printf("Enter value for employee %s%s: ", field, defaultValue != null ? " [" + defaultValue + "]" : "");
            value = in.readLine().trim();
            if (value.isEmpty() && defaultValue != null) {
                return defaultValue;
            }
            if (value.length() < 1) {
                System.out.println("Please retry with a valid " + field);
            }
        } while (value.length() < 1);
        return value;
    }

    private static Date getDateInput(BufferedReader in, SimpleDateFormat df, String field, Date defaultValue) throws IOException {
        Date value = null;
        do {
            System.out.printf("Enter value for employee %s (%s)%s: ", field, df.toLocalizedPattern(), defaultValue != null ? " [" + df.format(defaultValue) + "]" : "");
            String input = in.readLine().trim();
            if (input.isEmpty() && defaultValue != null) {
                return defaultValue;
            }
            try {
                value = df.parse(input);
            } catch (ParseException e) {
                System.out.println("Please retry with a valid " + field);
            }
        } while (value == null);
        return value;
    }

    private static float getFloatInput(BufferedReader in, String field, float defaultValue) throws IOException {
        float value;
        do {
            System.out.printf("Enter float value for employee %s%s: ", field, defaultValue > 0 ? " [" + defaultValue + "]" : "");
            try {
                String input = in.readLine().trim();
                value = input.isEmpty() ? defaultValue : Float.parseFloat(input);
                if (value < 0) {
                    System.out.println("Please retry with a positive " + field);
                    value = -1;
                }
            } catch (NumberFormatException e) {
                value = -1;
                System.out.println("Please retry with a valid float " + field);
            }
        } while (value < 0);
        return value;
    }
}
