package com.example.oop_project.Model;

public class Product {

    private String price;
    private int quantity;
    private String name;
    private int id;
    private static int counter = 1000;
    private int year;
    private boolean damaged;

    public Product() {
        quantity = 1;
        name = "name";
        id = counter++;
        year = 2023;
        damaged = false;
        price = "$0.0";
    }

    public Product(int quantity, String name, int year, String price, boolean damaged) {
        this.quantity = quantity;
        this.name = name;
        this.id = counter++;
        this.year = year;
        this.price = price;
        this.damaged = damaged;
    }

    public Product(Product product) {
        quantity = product.quantity;
        name = product.name;
        id = counter++;
        year = product.year;
        price = product.price;
        damaged = product.damaged;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Product.counter = counter;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }
}

