package com.examplee;

import com.example.domain.Employee;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee employee = new Employee();

        employee.setEmployeeId(101);
        employee.setEmployeeName("Jane Smith");
        employee.setEmployeeSSN("012-34-4567");
        employee.setEmployeeSalary(120_345.27);

        System.out.println(employee.getEmployeeId());
        System.out.println(employee.getEmployeeName());
        System.out.println(employee.getEmployeeSSN());
        System.out.println(employee.getEmployeeSalary());
    }
}
