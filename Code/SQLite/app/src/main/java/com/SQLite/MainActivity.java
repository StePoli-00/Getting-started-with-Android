package com.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText username,password, revealedUsername;
    DataBaseAdapter sqlitehelper;
    TextView result;
    Button addButton,retrieveButton,revealButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   sqLiteHelper=new SQLiteHelper(this);
        SQLiteDatabase database=sqLiteHelper.getWritableDatabase();*/

        sqlitehelper=new DataBaseAdapter(this);
        username=findViewById(R.id.username_edit);
        password=findViewById(R.id.pass_edit);
        revealedUsername=findViewById(R.id.UsernameRevealdedEditText);
        result=findViewById(R.id.textResult);
        retrieveButton=findViewById(R.id.retrieve_button);
        revealButton=findViewById(R.id.reveal_button);
        addButton=findViewById(R.id.add_button);
        addButton.setOnClickListener( v -> addUser());
        retrieveButton.setOnClickListener(v -> viewData() );
        revealButton.setOnClickListener(v -> revealPassword());


    }

    public void viewData()
    {
        String data=sqlitehelper.getAllData();
        Log.d("values",data);
        result.setText(data);

    }
    public void revealPassword()
    {
        if(revealedUsername.getText().toString().length()!=0) {
            result.setText(sqlitehelper.getData(revealedUsername.getText().toString()));
        }
    }


    public void addUser()
    {
        if(username.getText().toString().length()!=0 && password.getText().toString().length()!=0) {
            long id = sqlitehelper.insertData(username.getText().toString(), password.getText().toString());
            Log.d("value receieved", String.valueOf(id));
            if (id < 0) {
                Toast.makeText(getApplicationContext(), "User insert failed", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), " insert success", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "failed: fill all field", Toast.LENGTH_SHORT).show();
        }
    }





}