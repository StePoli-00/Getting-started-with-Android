package com.SQLite;


import android.content.ContentValues;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    EditText editName, editPosition;
    TextView editButton;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editName =findViewById(R.id.update_name_editText);
        editPosition =findViewById(R.id.update_position_editText);
        editButton=findViewById(R.id.update_btn);
        employee=(Employee) getIntent().getSerializableExtra("EDIT");

        editName.setText(employee.getName());
        editPosition.setText(employee.getPosition());
        editButton.setOnClickListener(v->{
            String name=editName.getText().toString();
            String position=editPosition.getText().toString();
          if(name!=null && position!=null)
          {
              DataBaseAdapter dataBaseAdapter=new DataBaseAdapter(this);
              String values[]={name,position};
              long id=dataBaseAdapter.updateData(values,employee.getId());
              if(id!=-1)
              {

                  Employee udpateemployee=new Employee(employee.getId(), name,position);
                  dataBaseAdapter.getEmployeeData().remove(employee);
                  dataBaseAdapter.getEmployeeData().add(udpateemployee);
                  Toast.makeText(this,"Employee Updated",Toast.LENGTH_SHORT).show();
                  finish();
              }
              else
              {
                  Toast.makeText(this,"Insert failed",Toast.LENGTH_SHORT).show();
              }


          }
        });




    }
}