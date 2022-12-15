package com.example.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;



import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recycleViewAdapter;
    ArrayList<Employee> employees=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView=findViewById(R.id.recyclerview);
        employees.add(new Employee("Adam Whitemore","CEO"));
        employees.add(new Employee("Margaret GrandWild","HR"));
        employees.add(new Employee("Adam Salter","CEO"));
        employees.add(new Employee("Margaret While","HR"));
        employees.add(new Employee("Mary Green","Founder"));
        employees.add(new Employee("Margaret Stant","HR"));
        employees.add(new Employee("David Borwin","Web Designer"));
        employees.add(new Employee("Daniel Dann","Project Manager"));
        employees.add(new Employee("Philip Waston","HR"));
        employees.add(new Employee("Adam Salter","CEO"));
        //...
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recycleViewAdapter=new RecyclerViewAdapter(this);
        recycleViewAdapter.setItems(employees);
        recyclerView.setAdapter(recycleViewAdapter);

    }

}