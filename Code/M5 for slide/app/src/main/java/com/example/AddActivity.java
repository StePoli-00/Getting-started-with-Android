package com.example;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {


    private EditText editName,editPosition;
    private DataBaseAdapter dataBaseAdapter;
    private TextView addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editName=findViewById(R.id.add_name_dialog);
        editPosition=findViewById(R.id.add_position_dialog);
        addButton=findViewById(R.id.dialog_add_btn);
        addButton.setOnClickListener(view -> {
            addEmployee();
            finish();
        });
        dataBaseAdapter=new DataBaseAdapter();
    }


    public void addEmployee(){
        Employee employee=new Employee(editName.getText().toString(),editPosition.getText().toString());
        dataBaseAdapter.add(employee).addOnSuccessListener(suc-> Toast.makeText(this,"Record inserted",Toast.LENGTH_SHORT).show())
                .addOnFailureListener(er-> Toast.makeText(this,er.getMessage(),Toast.LENGTH_SHORT).show());

    }
}