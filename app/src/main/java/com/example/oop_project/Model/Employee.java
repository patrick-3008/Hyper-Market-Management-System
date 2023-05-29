package com.example.oop_project.Model;

//meya meya
public class Employee extends Person {

    private int id;
    private static int counter = 2000;
    private double salary;

    public int getId() {
        return id;
    }

    public Employee() {
        super();
        id = counter++;
    }

    public Employee(String username, String password) {
        super(username, password);
        id = counter++;
    }

    public Employee(Employee emp) {
        super(emp);
        id = counter++;
    }
}
