package com.example.dao;

import com.example.model.Employee;

public interface EmployeeDAO {
    void add(Employee emp);
    void update(Employee emp);
    void delete(int id);
    Employee findById(int id);
    Employee[] getAllEmployees();
}