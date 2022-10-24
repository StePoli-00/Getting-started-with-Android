package com.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SQLite.DataBase.DataBaseAdapter;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DataBaseAdapter sqlitehelper;
    EditText oldUsername,newUsername;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        sqlitehelper=new DataBaseAdapter(this);
        oldUsername=findViewById(R.id.oldName_edit);
        newUsername=findViewById(R.id.newName_edit);
        update=findViewById(R.id.update_btn_into_updateAct);
        update.setOnClickListener(v-> updateRecord());
    }

    private void updateRecord() {

        ArrayList<String> users=sqlitehelper.getUsersName();
        if(users.contains(oldUsername.getText().toString()))
        {
            sqlitehelper.updateData(oldUsername.getText().toString(),newUsername.getText().toString());
            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this,oldUsername.getText().toString()+" not in database",Toast.LENGTH_SHORT).show();
        }
    }

}