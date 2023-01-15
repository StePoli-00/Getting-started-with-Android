package com.SQLite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.SQLite.DataBase.old.MainActivity;

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
        dataBaseAdapter=new DataBaseAdapter(this);
    }


    public void addEmployee(){
        String name=editName.getText().toString();
        String position=editPosition.getText().toString();
        long id=dataBaseAdapter.insertData(name,position);
        if(id!=-1)
        {
            String idEmp=dataBaseAdapter.getEmployeeId(name,position);
            Employee employee=new Employee(idEmp,name,position);
            dataBaseAdapter.getEmployeeData().add(employee);
            Toast.makeText(this,employee.getName()+" inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Insert failed",Toast.LENGTH_SHORT).show();
        }


    }
}