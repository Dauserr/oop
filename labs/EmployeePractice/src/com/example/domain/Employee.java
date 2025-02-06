package com.example.domain;

public class Employee {

    public int employeeId;
    public String employeeName;
    public String employeeSSN;
    public double employeeSalary;

    public Employee() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeSSN() {
        return employeeSSN;
    }

    public void setEmployeeSSN(String employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}
