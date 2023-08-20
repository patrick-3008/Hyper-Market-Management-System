package com.example.oop_project.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.Adaptors.PersonAdaptor;
import com.example.oop_project.Dialogs.UpdateEmployeeDialog;
import com.example.oop_project.Dialogs.UpdateProductDialog;
import com.example.oop_project.LogInActivity;
import com.example.oop_project.Model.Admin;
import com.example.oop_project.Model.Employee;
import com.example.oop_project.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class AdminPage extends AppCompatActivity implements UpdateEmployeeDialog.UpdateEmployeeDialogListener {

    List<Employee> employees = LogInActivity.employees;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        buildDrawer();
        setUsername();
        setName();
    }

    private void buildDrawer() {
        drawerLayout = findViewById(R.id.admin_page);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Toast.makeText(AdminPage.this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.settings) {
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    Intent i = new Intent(AdminPage.this, LogInActivity.class);
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
        menuInflater.inflate(R.menu.action_buttons_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.add_product_dialog) {
            Toast.makeText(AdminPage.this, "Employee added", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.update_employee) {
            Toast.makeText(AdminPage.this, "Employee updated", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.delete_employee) {
            Toast.makeText(AdminPage.this, "Employee deleted", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

//    private void showDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.add_product);
//        dialog.show();
//    }

    public void addEmployee(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Employee details");

        final View customLayout = getLayoutInflater().inflate(R.layout.add_employee, null);
        builder.setView(customLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText username = customLayout.findViewById(R.id.add_employee_username);
                EditText password = customLayout.findViewById(R.id.add_employee_password);
                String empName = username.getText().toString();
                String empPass = password.getText().toString();
                if (checkForValid(empName, empPass)) {
                    employees.add(new Employee(empName, empPass));
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean checkForValid(String name, String pass) {
        if (!name.contains("@") || !name.contains(".com"))
            return false;
        if (pass.length() < 5)
            return false;
        return true;
    }

    public void deleteEmployee(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Employee");
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter(this, android.R.layout.select_dialog_singlechoice);
        for (Employee emp : employees) {
            arrayAdapter.add(emp.getUsername());
        }
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                for (Employee emp : employees) {
                    if (strName.equals(emp.getUsername())) {
                        employees.remove(emp);
                        Toast.makeText(AdminPage.this, "Employee deleted", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });
        builder.show();
    }

    public void updateEmployee(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("List of Employees Names");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        for (Employee emp : employees) {
            arrayAdapter.add(emp.getUsername());
        }
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);

                UpdateEmployeeDialog updateDialog = new UpdateEmployeeDialog(strName);
                updateDialog.show(getSupportFragmentManager(), "Update Employee Dialog");
                Toast.makeText(AdminPage.this, "Employee Data updated", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    public void listEmployees(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("List of Employees Names");
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        for (Employee emp : employees) {
            arrayAdapter.add(emp.getUsername());
        }
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
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
    public void applyEmployeeTexts(String emp_name, String emp_pass, int position) {
        employees.get(position).setUsername(emp_name);
        employees.get(position).setPassword(emp_pass);
    }
}