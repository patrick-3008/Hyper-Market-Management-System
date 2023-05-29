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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.LogInActivity;
import com.example.oop_project.Model.Employee;
import com.example.oop_project.PersonAdaptor;
import com.example.oop_project.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class EmployeePage extends AppCompatActivity {
    List<Employee> employees = LogInActivity.employees;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);

        buildDrawer();
        setUsername();

        ListView ls = findViewById(R.id.employee_listview);
        PersonAdaptor adaptor = new PersonAdaptor(this, employees);
        ls.setAdapter(adaptor);
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
        if (item.getItemId() == R.id.add_product) {
            Toast.makeText(EmployeePage.this, "Product added", Toast.LENGTH_SHORT).show();
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
        }
        else {
            super.onBackPressed();
        }
    }
}