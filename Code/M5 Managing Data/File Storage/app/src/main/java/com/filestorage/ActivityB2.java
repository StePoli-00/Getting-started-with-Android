package com.filestorage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActivityB2 extends AppCompatActivity {


    Button backBtn,load;
    //SharedPreferences sharedPreferences;
    EditText username,password;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        username=findViewById(R.id.username_edit);
        password=findViewById(R.id.pass_edit);
        backBtn=findViewById(R.id.back_button);
        load=findViewById(R.id.load_button);
        relativeLayout=findViewById(R.id.relative_layoutB);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                load(relativeLayout,username,password);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ActivityB2.this, ActivityA.class);
                startActivity(i);
            }
        });


    }

    public void load(RelativeLayout relativeLayout, EditText username, EditText password)
    {
        FileInputStream file=null;
        try {
            Intent i=getIntent();
            Log.d("path",i.getStringExtra("path"));
            file=new FileInputStream(i.getStringExtra("path"));
            InputStreamReader inputStreamReader=new InputStreamReader(file);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            username.setText(bufferedReader.readLine());
            password.setText(bufferedReader.readLine());
            if(username.getText().toString().length()!=0 && password.getText().toString().length()!=0) {

                Snackbar.make(relativeLayout,"Data loaded successfully",Snackbar.LENGTH_SHORT).show();
            }
            bufferedReader.close();



        } catch (FileNotFoundException e) {
            //if file doesn't exists or we can't read beacuse we don't have the permission
            e.printStackTrace();
        } catch (IOException e) {
            //to handle read error
            e.printStackTrace();
        }





    }
}