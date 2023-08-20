package com.example.oop_project.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.oop_project.FeedReaderDbHelper;

//import com.example.oop_project.ProductDB;

import java.util.ArrayList;
import java.util.List;

public class WareHouse extends AppCompatActivity {
    public static List<Product> products = new ArrayList<>();

    private static WareHouse wareHouse = new WareHouse();

    private WareHouse() {
        products.add(new Product(88, "Mango", "$44.99"));
        products.add(new Product(3, "Apple", "$4.99"));
        products.add(new Product(8, "Watermelon", "$14.99"));
    }
}
