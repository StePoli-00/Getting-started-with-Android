package com.staffApp.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.staffApp.InsertDialog;
import com.staffApp.LandingActivity;
import com.staffApp.Models.Employee;
import com.staffApp.R;

import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    RVAdapter recycleViewAdapter;
    ArrayList<Employee> employees;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {

            switch (item.getItemId()){
                case R.id.logout:
                    startActivity(new Intent(RVActivity.this, LandingActivity.class));
                    finish();
                break;
            }
        return true;
        });
        findViewById(R.id.add_button).setOnClickListener(v -> createInsertDialog());
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database= FirebaseDatabase.getInstance().getReference().child("Employee");
        FirebaseRecyclerOptions<Employee> options = new FirebaseRecyclerOptions.Builder<Employee>().setQuery(database, Employee.class).build();
        recycleViewAdapter=new RVAdapter(options);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    private void createInsertDialog() {
        InsertDialog insertDialog=new InsertDialog(this);
        insertDialog.addOnClickListener();

    }




    @Override
    protected void onStart() {
        super.onStart();
       recycleViewAdapter.startListening();
        Log.d("onStart","");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //recycleViewAdapter.stopListening();
        Log.d("onStop","");
    }
}

