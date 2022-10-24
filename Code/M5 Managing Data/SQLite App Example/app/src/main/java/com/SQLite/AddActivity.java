package com.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SQLite.DataBase.DataBaseAdapter;

public class AddActivity extends AppCompatActivity {


    DataBaseAdapter sqlitehelper;
    EditText username,password;
    Button addButton,getButton,deleteButton,updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlitehelper=new DataBaseAdapter(this);
        username=findViewById(R.id.username_edit);
        password=findViewById(R.id.pass_edit);
        addButton=findViewById(R.id.add_userBtn);
        getButton=findViewById(R.id.get_userBtn);
        deleteButton=findViewById(R.id.delete_userBtn);
        updateButton=findViewById(R.id.update_userBtn);
        addButton.setOnClickListener(v-> addUser());
        getButton.setOnClickListener(v->{
            {

                Intent intent=new Intent(AddActivity.this,GetDataActivity.class);
                startActivity(intent);
            }
        });
        deleteButton.setOnClickListener(v->{
            Intent intent=new Intent(AddActivity.this,DeleteActivity.class);
            startActivity(intent);
        });

        updateButton.setOnClickListener(v->{
            Intent intent=new Intent(AddActivity.this,UpdateActivity.class);
            startActivity(intent);
        });


    }




    public void addUser()
    {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (username.length() != 0 && password.length() != 0) {
                long id = sqlitehelper.insertData(user, pass);
                Log.d("value receieved", String.valueOf(id));
                if (id < 0) {
                    Toast.makeText(getApplicationContext(), "User insert failed", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), " insert success", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "failed: fill all field", Toast.LENGTH_SHORT).show();
            }

    }
}