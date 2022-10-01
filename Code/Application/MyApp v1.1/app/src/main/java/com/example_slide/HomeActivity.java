package com.example_slide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example_slide.Fragment.EmailSenderFragment;
import com.example_slide.Fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {


        ImageButton mail;
        ImageButton home;
        TextView title;
        EmailSenderFragment fragmentEmailSender=new EmailSenderFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        //change font Example
        HomeFragment homeFragment=new HomeFragment(getIntent().getStringExtra("username"));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
        mail=findViewById(R.id.mail_button);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSupportFragmentManager().beginTransaction().remove(homeFragment);


                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,fragmentEmailSender).commit();
            }
        });

        home=findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
            }
        });


    }
}