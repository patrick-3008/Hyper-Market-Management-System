package com.example.oop_project.Model;
//na2es hetet el password

public abstract class Person {
    protected String username;
    protected String password;

    public Person() {
        username = "username";
        password = "password";
    }

    public Person(Person p) {
        username = p.username;
        password = p.password;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
