package com.filestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActivityA extends AppCompatActivity {



    Button buttonB,savebtn;
    RelativeLayout relativeLayout;
    private  EditText username,password;
    private SharedPreferences sharedPreferences;
    private File file=null;
    boolean insert=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        username=findViewById(R.id.username_edit);
        password=findViewById(R.id.pass_edit);
        buttonB=findViewById(R.id.buttonB);
        savebtn=findViewById(R.id.save_button);
        relativeLayout=findViewById(R.id.relative_layoutA);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert=saveData(username, password);

            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(insert==true) {
                    Intent i = new Intent(ActivityA.this, ActivityB2.class);
                    i.putExtra("path",file.toString()+"/data.txt");
                    startActivity(i);
                }

                else
                {
                    Snackbar.make(relativeLayout,"You must first insert data",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean saveData(EditText username, EditText password) {

        String us=username.getText().toString();
        String pass=password.getText().toString();


        if(us.length()!=0 &&  pass.length()!=0) {

            FileOutputStream out=null;
            try {

                us+="\n";
                pass+="\n";
                file=getFilesDir();
                out=openFileOutput("data.txt",MODE_PRIVATE);
                out.write(us.getBytes());
                out.write(pass.getBytes());



            }catch(FileNotFoundException e) {
                //to handle if file doesn't exists or we can't read beacuse we don't have the permission
                e.printStackTrace();
            }

            catch (IOException e) {
                //to handle write operation on file
                e.printStackTrace();
            }
            finally {
                //no matter what exception is raise we'ill close the file
                try {
                    out.close();
                } catch (IOException e) {
                    //even close file can raise exception so we must handle it
                    e.printStackTrace();
                }
            }
            Snackbar.make(relativeLayout, "Data saved successfully in: "+file.toString(), Snackbar.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            username.setError("mandatory");
            password.setError("mandatory");
            return false;
        }

    }


}