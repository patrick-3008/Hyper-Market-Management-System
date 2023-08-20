package com.example.oop_project.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oop_project.Model.Product;
import com.example.oop_project.R;

import java.util.List;

public class ProductAdaptor extends ArrayAdapter<Product> {
    Context context;
    List<? extends Product> products;

    public ProductAdaptor(Context context, List<? extends Product> products) {
        super(context, R.layout.person_listview_item, (List<Product>) products);
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.cart_item, parent, false);
        TextView quantity = row.findViewById(R.id.product_quantity);
        TextView title = row.findViewById(R.id.product_name);
        TextView price = row.findViewById(R.id.product_price);

        quantity.setText("Qty : " + Integer.toString(products.get(position).getQuantity()));
        title.setText(products.get(position).getName());
        price.setText(products.get(position).getPrice());
        return row;
    }
}
