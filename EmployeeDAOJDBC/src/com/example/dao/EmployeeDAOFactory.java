package com.example.dao;

public class EmployeeDAOFactory {

    public static EmployeeDAO createEmployeeDAO() {
        return new EmployeeDAOJDBCImpl();
    }

}