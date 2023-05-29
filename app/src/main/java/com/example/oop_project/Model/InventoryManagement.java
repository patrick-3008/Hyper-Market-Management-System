package com.example.oop_project.Model;
import java.lang.Exception;
import java.util.List;
import java.lang.Throwable;
//hanshoof
public class InventoryManagement extends Employee{

    public static WareHouse warehouse;

    public InventoryManagement() {
        super();
    }

    public InventoryManagement(String username, String password) {
        super(username, password);
    }

    public InventoryManagement(InventoryManagement in) {
        super(in);
    }

//    public void addProduct(Product product ){
//        WareHouse.products.add(product);
//    }
//    public void removeProduct(Product product )
//    {
//        for (Product p:WareHouse.products)
//        {
//            if (p.getId()==product.getId())
//            {
//
//                WareHouse.products.remove(p);
//
//            }
//        }
//    }
//    public void updateProduct(Product product ){
//        for (Product p:WareHouse.products)
//        { // error handling ta7t
//            if (p.getId()==product.getId())
//            {
//
//
//            }
//        }
//
//    }
}
