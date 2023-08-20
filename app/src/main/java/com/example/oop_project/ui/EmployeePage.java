package com.example.oop_project.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
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

import com.example.oop_project.Adaptors.ProductAdaptor;
import com.example.oop_project.Dialogs.AddProductDialog;
import com.example.oop_project.LogInActivity;
import com.example.oop_project.Model.Employee;
import com.example.oop_project.Model.Product;
import com.example.oop_project.Model.WareHouse;
import com.example.oop_project.R;
import com.example.oop_project.Dialogs.UpdateProductDialog;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class EmployeePage extends AppCompatActivity
        implements AddProductDialog.AddProductDialogListener, UpdateProductDialog.UpdateProductDialogListener {

    List<Employee> employees = LogInActivity.employees;
    List<Product> marketProducts = WareHouse.products;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);

        buildDrawer();
        setUsername();
        setName();
        buildProductListview();
    }

    private void buildProductListview() {
        ListView ls = findViewById(R.id.employee_products_listview);
        ProductAdaptor adaptor = new ProductAdaptor(this, marketProducts);
        ls.setAdapter(adaptor);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UpdateProductDialog dialog = new UpdateProductDialog(marketProducts.get(position), position);
                dialog.show(getSupportFragmentManager(), "Update Product Dialog");
                adaptor.notifyDataSetChanged();
            }
        });
    }

    private void buildDrawer() {
        drawerLayout = findViewById(R.id.employee_page);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Toast.makeText(EmployeePage.this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.settings) {
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    Intent i = new Intent(EmployeePage.this, LogInActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
    }

    private void setUsername() {
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        View mHeaderView = mNavigationView.getHeaderView(0);
        TextView usernameTextview = mHeaderView.findViewById(R.id.drawer_header_email_textField);
        Bundle b = getIntent().getExtras();
        String username = b.getString("email");
        usernameTextview.setText(username);
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
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_buttons_employee, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.add_product_dialog) {
            AddProductDialog dialog = new AddProductDialog();
            dialog.show(getSupportFragmentManager(), "Add Product Dialog");
        } else if (item.getItemId() == R.id.update_quantity) {
            Toast.makeText(EmployeePage.this, "Product quantity updated", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.delete_product) {
            Toast.makeText(EmployeePage.this, "Product deleted", Toast.LENGTH_SHORT).show();
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
    public void applyAddTexts(String prod_name, int prod_quantity, String prod_price) {
        marketProducts.add(new Product(prod_quantity, prod_name, prod_price));
    }

    @Override
    public void applyUpdateTexts(String prod_name, int prod_quantity, String prod_price, int position, boolean delete) {
        if (delete) {
            marketProducts.get(position).setQuantity(0);
            buildProductListview();
            return;
        }
        marketProducts.get(position).setName(prod_name);
        marketProducts.get(position).setPrice(prod_price);
        marketProducts.get(position).setQuantity(prod_quantity);
    }

}