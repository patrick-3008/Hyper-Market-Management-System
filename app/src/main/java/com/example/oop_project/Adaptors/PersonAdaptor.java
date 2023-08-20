package com.example.oop_project.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oop_project.Model.Person;
import com.example.oop_project.R;

import java.util.List;

public class PersonAdaptor extends ArrayAdapter<Person> {
    Context context;
    List<? extends Person> persons;

    public PersonAdaptor(Context context, List<? extends Person> persons) {
        super(context, R.layout.person_listview_item, (List<Person>) persons);
        this.context = context;
        this.persons = persons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.person_listview_item, parent, false);
        TextView title = row.findViewById(R.id.person_name);

        Person p = (Person) this.persons.get(position);
        title.setText(p.getUsername());
        return row;
    }
}
