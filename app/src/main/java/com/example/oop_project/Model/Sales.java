package com.example.oop_project.Model;

public class Sales extends Employee {

    public Sales() {
        super();
    }


    public Sales(String username, String password) {
        super(username, password);
    }
    public Sales(Sales s) {
        super(s);
    }

}
