package com.example.oop_project.Model;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
    public static List<Product> products = new ArrayList<Product>();

    public void addProduct(Product p){
        products.add(p);
    }


    public WareHouse() {
    }

    public WareHouse(List<Product> products) {
        for (Product prod : products) {
            this.products.add(new Product(prod));// deep copy mn ala5er composition
        }
    }
}
