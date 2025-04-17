package com.example.dao;

import com.example.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class EmployeeDAOJDBCImpl implements EmployeeDAO {

    private Connection con = null;

    EmployeeDAOJDBCImpl() {
        String url = "jdbc:derby://localhost:1527/C:\\db\\EmployeeDB";
        String username = "public";
        String password = "tiger";
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            System.exit(-1);
        }
    }

    @Override
    public void add(Employee emp) throws DAOException {
        String query = "INSERT INTO EMPLOYEE VALUES (" + emp.getId()
                + ", '" + emp.getFirstName() + "', '"
                + emp.getLastName() + "', '"
                + new java.sql.Date(emp.getBirthDate().getTime()) + "', "
                + emp.getSalary() + ")";
        try (Statement stmt = con.createStatement()) {
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error adding employee");
            }
        } catch (SQLException se) {
            throw new DAOException("Error adding employee in DAO", se);
        }
    }


    @Override
    public void update(Employee emp) throws DAOException {
        String query = "UPDATE EMPLOYEE SET FIRSTNAME='" + emp.getFirstName() + "',"
                + "LASTNAME='" + emp.getLastName() + "',"
                + "BIRTHDATE='" + new java.sql.Date(emp.getBirthDate().getTime()) + "',"
                + "SALARY=" + emp.getSalary() + " WHERE ID=" + emp.getId();
        try (Statement stmt = con.createStatement()) {
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error updating employee");
            }
        } catch (SQLException se) {
            throw new DAOException("Error updating employee in DAO", se);
        }
    }


    @Override
    public void delete(int id) throws DAOException {
        Employee emp = findById(id);
        if (emp == null) {
            throw new DAOException("Employee id: " + id + " does not exist to delete.");
        }
        String query = "DELETE FROM EMPLOYEE WHERE ID=" + id;
        try (Statement stmt = con.createStatement()) {
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error deleting employee");
            }
        } catch (SQLException se) {
            throw new DAOException("Error deleting employee in DAO", se);
        }
    }


    @Override
    public Employee findById(int id) throws DAOException {
        String query = "SELECT * FROM EMPLOYEE WHERE ID=" + id;
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return new Employee(rs.getInt("ID"), rs.getString("FIRSTNAME"),
                    rs.getString("LASTNAME"), rs.getDate("BIRTHDATE"),
                    rs.getFloat("SALARY"));
        } catch (SQLException se) {
            throw new DAOException("Error finding employee in DAO", se);
        }
    }


    @Override
    public Employee[] getAllEmployees() throws DAOException {
        ArrayList<Employee> emps = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEE";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                emps.add(new Employee(rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getDate("BIRTHDATE"),
                        rs.getFloat("SALARY")));
            }
        } catch (SQLException se) {
            throw new DAOException("Error getting all employees in DAO", se);
        }
        return emps.toArray(new Employee[0]);
    }


    @Override
    public void close() {
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println("Exception closing Connection: " + se);
        }
    }

}
