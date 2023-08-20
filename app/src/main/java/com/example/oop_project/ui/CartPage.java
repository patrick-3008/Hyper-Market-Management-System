package com.example.oop_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oop_project.Adaptors.CartAdaptor;
import com.example.oop_project.Adaptors.ProductAdaptor;
import com.example.oop_project.Model.Product;
import com.example.oop_project.Model.WareHouse;
import com.example.oop_project.R;
import com.example.oop_project.ui.UserPage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity {

    List<Product> products = UserPage.userCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        buildProductList();
        calTotal();
    }

    private void buildProductList() {
        ListView ls = findViewById(R.id.card_listview);
        CartAdaptor adaptor = new CartAdaptor(this, products);
        ls.setAdapter(adaptor);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartPage.this);
                builder.setTitle("Are you sure you want to delete this product ?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        products.remove(products.get(position));
                        calTotal();
                        adaptor.notifyDataSetChanged();
                    }
                })
                        .show();
            }
        });
    }

    public void doneButton(View v) {
        finish();
    }

    @SuppressLint("SetTextI18n")
    public void calTotal() {
        final DecimalFormat df = new DecimalFormat("0.00");

        double sum = 0;
        for (Product prod : products) {
            sum +=  Double.parseDouble(prod.getPrice().substring(1))* prod.getQuantity();
        }
        TextView sumTextview = findViewById(R.id.sum_value);
        sumTextview.setText("Total: $" + df.format(sum));
    }
}