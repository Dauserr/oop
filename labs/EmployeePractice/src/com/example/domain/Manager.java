package com.example.domain;

public class Manager extends Employee {
    private Employee[] staff;
    private int employeeCount;
    private String deptName;

    public Manager(int empId, String name, String ssn, double salary, String deptName) {
        super(empId, name, ssn, salary);
        this.employeeCount = 0;
        this.staff = new Employee[20];
        this.deptName = deptName;
    }
    public int findEmployee(Employee emp) {
        for (int i = 0; i < employeeCount; i++) {
            if (staff[i].equals(emp)) {
                return i;
            }
        }
        return -1;
    }
    public boolean addEmployee(Employee emp) {
        if (findEmployee(emp) != -1 || employeeCount >= 20) {
            return false;
        }
        staff[employeeCount++] = emp;
        return true;
    }
    public boolean removeEmployee(Employee emp) {
        int index = findEmployee(emp);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < employeeCount - 1; i++) {
            staff[i] = staff[i + 1];
        }
        staff[--employeeCount] = null;
        return true;
    }
    public void printStaffDetails() {
        System.out.println("Manager: " + getName());
        for (int i = 0; i < employeeCount; i++) {
            System.out.println("Employee ID: " + staff[i].getEmpId() + ", Name: " + staff[i].getName());
        }
    }

    public String getDeptName() {
        return deptName;
    }
    @Override
    public String toString() {
        return super.toString() + "\nDepartment: " + deptName;
    }
}
