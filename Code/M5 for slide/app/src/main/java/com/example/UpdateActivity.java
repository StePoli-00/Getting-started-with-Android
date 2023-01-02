package com.example;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    EditText name,position;
    TextView editButton;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name=findViewById(R.id.update_name_editText);
        position=findViewById(R.id.update_position_editText);
        editButton=findViewById(R.id.update_btn);
        employee=(Employee) getIntent().getSerializableExtra("EDIT");
        name.setText(employee.getName());
        position.setText(employee.getPosition());
        editButton.setOnClickListener(v->{
          if(name.getText().toString()!=null && position.getText().toString()!=null)
          {
              HashMap<String, Object> hashMap = new HashMap<>();
              hashMap.put("name", name.getText().toString());
              hashMap.put("position",position.getText().toString());
              DataBaseAdapter dataBaseAdapter=new DataBaseAdapter();
              dataBaseAdapter.update(employee.getKey(), hashMap).addOnSuccessListener(suc ->
              {
                  Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                  finish();
              }).addOnFailureListener(er ->
              {
                  Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
              });
          }
        });




    }
}