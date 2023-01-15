package com.App;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {


    private EditText editName,editPosition;;
    private TextView addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editName=findViewById(R.id.add_name_dialog);
        editPosition=findViewById(R.id.add_position_dialog);
        addButton=findViewById(R.id.dialog_add_btn);
        addButton.setOnClickListener(view -> {
            finish();
        });

    }


}