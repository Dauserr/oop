package com.example;

import com.example.domain.Employee;
import com.example.domain.Engineer;
import com.example.domain.Manager;
import com.example.domain.Admin;
import com.example.domain.Director;
import com.example.business.EmployeeStockPlan;

public class EmployeeTest {
    public static void printEmployee(Employee emp) {
        System.out.println("Employee type: " + emp.getClass().getSimpleName());
        System.out.println(emp);
    }

    public static void printEmployee(Employee emp, EmployeeStockPlan esp) {
        printEmployee(emp);
        System.out.println("Stock Options: " + esp.grantStock(emp));
    }
    public static void main(String[] args) {
        Engineer eng = new Engineer(101, "Jane Smith", "012-34-5678", 120_345.27);
        Manager mgr = new Manager(207, "Barbara Johnson", "054-12-2367", 109_501.36, "US Marketing");
        Admin adm = new Admin(304, "Bill Monroe", "108-23-6509", 75_002.34);
        Director dir = new Director(12, "Susan Wheeler", "099-45-2340", 120_567.36, "Global Marketing", 1_000_000.00);

        EmployeeStockPlan esp = new EmployeeStockPlan();

        printEmployee(eng);
        printEmployee(mgr,esp);
        printEmployee(adm,esp);
        printEmployee(dir,esp);

        mgr.setName("Barbara Johnson-Smythe");
        mgr.raiseSalary(10_000.00);

        // Проверка изменений
        System.out.println("\nAfter changing Manager:");
        printEmployee(mgr);
    }
}

