package com.example_slide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

        TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change font Example

        setContentView(R.layout.home_layout);
        textView=findViewById(R.id.home_name);
        Intent i=getIntent();
        String text=i.getStringExtra("name");
        textView.setText(text);









    }
}