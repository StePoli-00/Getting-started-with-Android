package com.SQLite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {

    DataBaseAdapter dataBaseAdapter;
    RecyclerView recyclerView;
    RVAdapter recycleViewAdapter;
    FloatingActionButton addButton;
    boolean isLoading=false;
    String key=null;
    ArrayList<Employee> employees=new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseAdapter=new DataBaseAdapter(this,employees);
        addButton=findViewById(R.id.add_button);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        employees=dataBaseAdapter.getEmployeeData();
        recycleViewAdapter=new RVAdapter(this,employees);
        recyclerView.setAdapter(recycleViewAdapter);
        loadData();
        addButton.setOnClickListener(view -> {
            Intent intent=new Intent(RVActivity.this, AddActivity.class);
            startActivity(intent);
        });




  }

    private void loadData()
    {

        ArrayList<Employee> emp=dataBaseAdapter.getEmployeeData();
        for (Employee e:emp)
        {
            System.out.println(e.getName());
        }
        recycleViewAdapter.clearItems();
        recycleViewAdapter.setEmployees(emp);

        recycleViewAdapter.notifyDataSetChanged();


    }


}

