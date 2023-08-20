package com.example.oop_project.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.oop_project.LogInActivity;
import com.example.oop_project.Model.Employee;
import com.example.oop_project.Model.Person;
import com.example.oop_project.R;

import java.util.List;

public class UpdateEmployeeDialog extends AppCompatDialogFragment {
    private EditText empName;
    private EditText empPass;

    private Person editedPerson;
    private int position;

    List<Employee> employees = LogInActivity.employees;

    private UpdateEmployeeDialogListener listener;

    public UpdateEmployeeDialog(String empName) {
        for (int i = 0; i < employees.size(); i++) {
            if (empName.equals(employees.get(i).getUsername())) {
                editedPerson = employees.get(i);
                position = i;
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_employee, null);

        builder.setView(view)
                .setTitle("Update Employee")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String empName = UpdateEmployeeDialog.this.empName.getText().toString();
                        String empPass = UpdateEmployeeDialog.this.empPass.getText().toString();
                        listener.applyEmployeeTexts(empName, empPass, position);
                    }
                });

        empName = view.findViewById(R.id.add_employee_username);
        empPass = view.findViewById(R.id.add_employee_password);

        empName.setText(editedPerson.getUsername());
        empPass.setText(editedPerson.getPassword());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (UpdateEmployeeDialog.UpdateEmployeeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement UpdateProductDialogListener");
        }
    }

    public interface UpdateEmployeeDialogListener {
        void applyEmployeeTexts(String emp_name, String emp_pass, int position);
    }
}

