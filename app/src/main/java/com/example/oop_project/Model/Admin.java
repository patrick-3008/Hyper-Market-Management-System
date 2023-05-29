package com.example.oop_project.Model;

import java.util.List;

public class Admin extends Person {
    List<Employee> employees;

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(List<Employee> employees) {
        this.employees = employees;
    }

    public Admin(Admin admin) {
        employees = admin.employees;
    }

    //TODO: add edit unique id for employee

    public void addEmployee(Employee newEmployee) {
        employees.add(newEmployee);
    }

    public void removeEmployee(int id) {
        for (Employee emp : employees) {
            if (id == emp.getId()) {
                employees.remove(emp);
                break;
            }
        }
        System.out.println("cannot delete employee, cannot found him/her");
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int searchEmployee(int id) {
        for (Employee emp : employees)
            if (id == emp.getId())
                return id;

        return -1;
    }

    public void updateEmployee(Employee emp) {
        //TODO: after ui
    }

}
