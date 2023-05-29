package com.example.oop_project.Model;
// hanshoof lesa
public class User extends Person {
    public User() {
        super();
    }

    public User(String username, String password) {
        super(username, password);
    }

    public User(User u) {
        super(u);
    }
}

