package com.example.oop_project.Model;

public class Marketing extends Employee{
    public Marketing() {
        super();
    }

    public Marketing(String username, String password) {
        super(username, password);
    }

    public Marketing(Marketing mark) {
        super(mark);
    }
}
