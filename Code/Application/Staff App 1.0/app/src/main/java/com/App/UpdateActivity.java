package com.App;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText name,position;
    TextView editButton;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = findViewById(R.id.update_name_editText);
        position = findViewById(R.id.update_position_editText);
        editButton = findViewById(R.id.update_btn);
        employee = (Employee) getIntent().getSerializableExtra("EDIT");
        name.setText(employee.getName());
        position.setText(employee.getPosition());
        editButton.setOnClickListener(v -> {
        });
    }
}