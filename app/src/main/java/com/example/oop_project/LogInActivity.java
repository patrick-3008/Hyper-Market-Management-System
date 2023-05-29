package com.example.oop_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oop_project.Model.Admin;
import com.example.oop_project.Model.Employee;
import com.example.oop_project.Model.EmployeeType;
import com.example.oop_project.Model.User;
import com.example.oop_project.ui.AdminPage;
import com.example.oop_project.ui.EmployeePage;
import com.example.oop_project.ui.UserPage;

import java.util.ArrayList;
import java.util.List;


public class LogInActivity extends AppCompatActivity {
    static public List<Admin> admins = new ArrayList<>();
    static public List<Employee> employees = new ArrayList<>();
    static public List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public EmployeeType chooseEmpType(View v) {
        int buttonId = ((Button) v).getId();
        v.setEnabled(false);

        if (buttonId == R.id.admin_button) {
            findViewById(R.id.employee_button).setEnabled(true);
            findViewById(R.id.user_button).setEnabled(true);
            return EmployeeType.Admin;
        } else if (buttonId == R.id.employee_button) {
            findViewById(R.id.admin_button).setEnabled(true);
            findViewById(R.id.user_button).setEnabled(true);
            return EmployeeType.Employee;
        } else {
            findViewById(R.id.admin_button).setEnabled(true);
            findViewById(R.id.employee_button).setEnabled(true);
            return EmployeeType.User;
        }
    }

    private boolean noEmpSelected() {
        return findViewById(R.id.admin_button).isEnabled() &&
                findViewById(R.id.employee_button).isEnabled() &&
                findViewById(R.id.user_button).isEnabled();
    }

    public void signInButton(View v) {
        String username = ((EditText) findViewById(R.id.username_textfield)).getText().toString();

        if (!findViewById(R.id.admin_button).isEnabled() && checkForAdmin()) {
            Intent i = new Intent(LogInActivity.this, AdminPage.class);
            i.putExtra("email", username);
            startActivity(i);
            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
        } else if (!findViewById(R.id.employee_button).isEnabled() && checkForEmployee()) {
            Intent i = new Intent(LogInActivity.this, EmployeePage.class);
            i.putExtra("email", username);
            startActivity(i);
            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
        } else if (!findViewById(R.id.user_button).isEnabled() && checkForUser()) {
            Intent i = new Intent(LogInActivity.this, UserPage.class);
            i.putExtra("email", username);
            startActivity(i);
            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
        } else if (noEmpSelected()) {
            Toast.makeText(this, "Please select a Type of Employee", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkForUser() {
        String username = ((EditText) findViewById(R.id.username_textfield)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_textfield)).getText().toString();
        if (checkForValid()) {
            for (User user : users) {
                if (username.equals(user.getUsername())) {
                    if (password.equals(user.getPassword())) {
                        return true;
                    } else {
                        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            users.add(new User(username, password));
            Toast.makeText(this, "New User Created", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Enter vaild data to register", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean checkForEmployee() {
        String username = ((EditText) findViewById(R.id.username_textfield)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_textfield)).getText().toString();
        if (checkForValid()) {
            for (Employee employee : employees) {
                if (username.equals(employee.getUsername())) {
                    if (password.equals(employee.getPassword())) {
                        return true;
                    } else {
                        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            employees.add(new Employee(username, password));
            Toast.makeText(this, "New Employee Created", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Enter vaild data to register", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean checkForAdmin() {
        Log.d("Admins Size", "Size is " + admins.size());
        String username = ((EditText) findViewById(R.id.username_textfield)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_textfield)).getText().toString();
        if (checkForValid()) {
            for (Admin admin : admins) {
                if (username.equals(admin.getUsername())) {
                    if (password.equals(admin.getPassword())) {
                        return true;
                    } else {
                        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            admins.add(new Admin(username, password));
            Log.d("Admins Size", "Size is " + admins.size());
            Toast.makeText(this, "New Admin Created", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Enter valid data to register", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean checkForValid() {
        String username = ((EditText) findViewById(R.id.username_textfield)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_textfield)).getText().toString();
        if (!username.contains("@") || !username.contains(".com"))
            return false;
        if (password.length() < 5)
            return false;
        return true;
    }
}