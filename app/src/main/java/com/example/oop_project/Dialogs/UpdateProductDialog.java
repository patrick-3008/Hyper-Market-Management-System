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

import com.example.oop_project.Model.Product;
import com.example.oop_project.Model.WareHouse;
import com.example.oop_project.R;

import java.util.List;

public class UpdateProductDialog extends AppCompatDialogFragment {
    private EditText name;
    private EditText quantity;
    private EditText price;

    private Product editedProduct;
    private int position;

    List<Product> products = WareHouse.products;

    private UpdateProductDialog.UpdateProductDialogListener listener;

    public UpdateProductDialog(Product product, int position){
        editedProduct = product;
        this.position= position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_product, null);

        builder.setView(view)
                .setTitle("Update Product")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String prod_name = name.getText().toString();
                        int prod_quantity = Integer.parseInt(quantity.getText().toString());
                        String prod_price = price.getText().toString();
                        listener.applyUpdateTexts(prod_name, prod_quantity,prod_price,  position, false);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: delete item
                        String prod_name = name.getText().toString();
                        int prod_quantity = Integer.parseInt(quantity.getText().toString());
                        String prod_price = price.getText().toString();
                        listener.applyUpdateTexts(prod_name, prod_quantity,prod_price,  position, true);
                    }
                });

        name = view.findViewById(R.id.add_product_name);
        quantity = view.findViewById(R.id.add_product_quantity);
        price = view.findViewById(R.id.add_product_price);

        name.setText(editedProduct.getName());
        quantity.setText(String.valueOf(editedProduct.getQuantity()));
        price.setText(editedProduct.getPrice());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (UpdateProductDialog.UpdateProductDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement UpdateProductDialogListener");
        }
    }

    public interface UpdateProductDialogListener {
        void applyUpdateTexts(String prod_name, int prod_quantity, String prod_price, int position, boolean delete);
    }
}
