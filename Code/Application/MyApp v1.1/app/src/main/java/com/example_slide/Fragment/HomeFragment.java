package com.example_slide.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example_slide.R;


public class HomeFragment extends Fragment {


    TextView textName,textTitle;
    private String username;

    public HomeFragment(String username) {
        this.username = username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        textTitle=view.findViewById(R.id.welcome_homeText);
        textName=view.findViewById(R.id.home_name);
        textName.setText(username);

       /* Typeface myfont=Typeface.createFromFile("font/poppins/Poppins-Black.otf");
        textTitle.setTypeface(myfont);*/
     /*   Intent i=getIntent();
        String text=i.getStringExtra("name");*/


        return  view;



    }
}