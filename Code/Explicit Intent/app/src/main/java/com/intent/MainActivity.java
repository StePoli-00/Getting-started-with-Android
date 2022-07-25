package com.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
    }

    public void doSomething(View view){
        /***first way**/
        //specify always .class on the class that we want to call
        //Intent i=new Intent(this,ActivityB.class);
        /***second way**/
        Intent i=new Intent();
        i.setClassName(this,"com.intent.ActivityB");
        startActivity(i);
    }
}