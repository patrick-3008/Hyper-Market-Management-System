package com.example.oop_project.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.LogInActivity;
import com.example.oop_project.Model.Product;
import com.example.oop_project.Model.User;
import com.example.oop_project.Model.WareHouse;
import com.example.oop_project.Adaptors.ProductAdaptor;
//import com.example.oop_project.ProductDB;
import com.example.oop_project.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserPage extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<User> users = LogInActivity.users;
    List<Product> marketProducts = WareHouse.products;
    List<Product> editedProducts = new ArrayList<>();
    public static List<Product> userCart = new ArrayList<>();

//    ProductDB product_db = new ProductDB(this);


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        buildDrawer();
        setName();
        setEmail();
        buildProductList();
        searchProducts();
    }

    private void searchProducts() {
        EditText ed = findViewById(R.id.search_products);
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editedProducts.clear();
                String editedText = ed.getText().toString();
                for (Product product : marketProducts) {
                    if (product.getName().startsWith(editedText)) {
                        editedProducts.add(new Product(product));
                    }
                    showFilteredList();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showFilteredList() {
        ListView ls = findViewById(R.id.products_list);
        ProductAdaptor adaptor = new ProductAdaptor(this, editedProducts);
        ls.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();
        Log.i("editedProducts", editedProducts.toString());
        ls.setOnItemClickListener(this);
    }

    private void buildProductList() {
//        products = product_db.loadHandler();
        ListView ls = findViewById(R.id.products_list);
        ProductAdaptor adaptor = new ProductAdaptor(this, marketProducts);
        ls.setAdapter(adaptor);
        ls.setOnItemClickListener(this);
    }

    private void setEmail() {
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        View mHeaderView = mNavigationView.getHeaderView(0);
        TextView emailTextview = mHeaderView.findViewById(R.id.drawer_header_email_textField);
        Bundle b = getIntent().getExtras();
        String email = b.getString("email");
        emailTextview.setText(email);
    }

    private void setName() {
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        View mHeaderView = mNavigationView.getHeaderView(0);
        TextView usernameTextview = mHeaderView.findViewById(R.id.drawer_header_name_textfield);
        Bundle b = getIntent().getExtras();
        String name = b.getString("email");
        name = name.substring(0, name.lastIndexOf("@"));
        usernameTextview.setText(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //drawer
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_buttons_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        //drawer
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void buildDrawer() {
        drawerLayout = findViewById(R.id.user_page);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(UserPage.this, CartPage.class));
                    Toast.makeText(UserPage.this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.settings) {
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    Intent i = new Intent(UserPage.this, LogInActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //action buttons
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.add_item) {
            Toast.makeText(UserPage.this, "Item added", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.delete_item) {
            Toast.makeText(UserPage.this, "Item deleted", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //listview
        AlertDialog d = new AlertDialog.Builder(this)
                .setTitle("Add Product")
                .setMessage("Are you sure you want to add this Product ?")
                .setPositiveButton("Add", (dialog, id1) -> {
                    if (marketProducts.get(position).getQuantity() > 0) {
                        addProduct(marketProducts.get(position));
                        reBuildProductList();
                        marketProducts.get(position).updateQuantity(marketProducts.get(position));
                    } else {
                        Toast.makeText(this, "This product is out of stock !", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }

    private void addProduct(Product prod) {

        if (userCart.size() == 0) {
            Product pro = new Product(prod);
            pro.setQuantity(1);
            userCart.add(pro);
            return;
        }
        for (Product p : userCart) {
            if (p.getName().equals(prod.getName())) {
                p.setQuantity(p.getQuantity() + 1);
                return;
            }
        }
        Product pro = new Product(prod);
        pro.setQuantity(1);
        userCart.add(pro);

    }

    private void reBuildProductList() {
        ListView ls = findViewById(R.id.products_list);
        ProductAdaptor adaptor = new ProductAdaptor(this, marketProducts);
        ls.setAdapter(adaptor);
        ls.setOnItemClickListener(this);
    }
}