package com.example.oop_project.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.LogInActivity;
import com.example.oop_project.Model.Product;
import com.example.oop_project.Model.User;
import com.example.oop_project.Model.WareHouse;
import com.example.oop_project.ProductAdaptor;
import com.example.oop_project.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserPage extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<User> users = LogInActivity.users;
    List<Product> marketProducts = WareHouse.products;
    List<Product> userCard = new ArrayList<>();


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

    private void buildProductList() {
        marketProducts.add(new Product(88, "Mango", 2012, "$44.99", true));
        marketProducts.add(new Product(7, "Apple", 2010, "$24.99", false));
        marketProducts.add(new Product(100, "Chips", 2023, "$2.99", false));
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
        name= name.substring(0,name.lastIndexOf("@")) ;
        usernameTextview.setText(name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_buttons_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog d = new AlertDialog.Builder(this)
                .setTitle("Add Product")
                .setMessage("Are you sure you want to add this Product ?")
                .setPositiveButton("Add", (dialog, id1) -> userCard.add(new Product(marketProducts.get(position))))
                .show();
    }
}