package com.example;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {

    DataBaseAdapter dataBaseAdapter;
    RecyclerView recyclerView;
    RVAdapter recycleViewAdapter;
    FloatingActionButton addButton;
    boolean isLoading=false;
    String key=null;
    ArrayList<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseAdapter=new DataBaseAdapter();
        addButton=findViewById(R.id.add_button);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        employees=new ArrayList<>();
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

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Employee");
        databaseReference.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                recycleViewAdapter.clearItems();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    Employee emp = data.getValue(Employee.class);
                    emp.setKey(data.getKey());
                    employees.add(emp);
                }

                recycleViewAdapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    protected void onResume() {
        super.onResume();
    }




}

