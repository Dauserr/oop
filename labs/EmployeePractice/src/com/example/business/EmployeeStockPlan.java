package com.example.business;
import com.example.domain.Employee;
import com.example.domain.Manager;
import com.example.domain.Director;


public class EmployeeStockPlan {
    private final int employeeShares = 10;
    private final int managerShares = 100;
    private final int directorShares = 1000;

    public int grantStock(Employee emp) {
        if (emp instanceof Director) {
            return directorShares;
        } else if (emp instanceof Manager) {
            return managerShares;
        } else {
            return employeeShares;
        }
    }
}
