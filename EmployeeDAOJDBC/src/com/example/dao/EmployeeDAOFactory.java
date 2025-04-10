package com.example.dao;

public class EmployeeDAOFactory {

    public EmployeeDAO createEmployeeDAO() {
        return new EmployeeDAOMemoryImpl();
    }

    public static EmployeeDAO getFactory() {
        return new EmployeeDAOJDBCImpl();
    }

}