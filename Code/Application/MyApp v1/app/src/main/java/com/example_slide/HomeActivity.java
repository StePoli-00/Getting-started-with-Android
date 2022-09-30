package com.example_slide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

        TextView textView;
        ImageButton mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //change font Example

        setContentView(R.layout.home_layout);
        textView=findViewById(R.id.home_name);
        Intent i=getIntent();
        String text=i.getStringExtra("name");
        textView.setText(text);
        mail=findViewById(R.id.mail_button);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });









    }
}