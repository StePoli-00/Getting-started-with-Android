package com.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.app.FragmentManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class FragmentActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Fragment1 f1=new Fragment1();
        Fragment2 f2=new Fragment2();
        //set default fragment
        Button bt1=findViewById(R.id.btnFragment1);
        Button bt2=findViewById(R.id.btnFragment2);
        /*ImageView dog=findViewById(R.id.imageDog);
        ImageView cat=findViewById(R.id.imageCat);
        dog.setImageResource(R.drawable.prova);
        cat.setImageResource(R.drawable.prova);*/

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,f1).commit();
                getSupportFragmentManager().beginTransaction().ad


            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,f2).commit();



            }
        });
    }


}




