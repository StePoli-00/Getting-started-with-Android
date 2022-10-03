package com.filestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ActivityB extends AppCompatActivity {


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
                Intent i=new Intent(ActivityB.this, ActivityA.class);
                startActivity(i);
            }
        });


    }

    public void load(RelativeLayout relativeLayout, EditText username, EditText password)
    {
        FileInputStream input=null;
        try {

            input=openFileInput("data.txt");
            StringBuffer buffer=new StringBuffer();
            int c;
            while((c=input.read())!=-1){

                buffer.append(((char) c));
            }

            Log.d("buffer",buffer.toString());
            if(buffer.length()!=0) {
                username.setText(buffer.substring(0, buffer.indexOf(" ")));
                password.setText(buffer.substring(buffer.indexOf(" ")+1, buffer.length()));
                Snackbar.make(relativeLayout,"Data loaded successfully",Snackbar.LENGTH_SHORT).show();
            }



        } catch (FileNotFoundException e) {
            //if file doesn't exists or we can't read beacuse we don't have the permission
            e.printStackTrace();
        } catch (IOException e) {
            //to handle read error
            e.printStackTrace();
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
}