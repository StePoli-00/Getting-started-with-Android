package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    String tag = "test_ciclo_di_vita_activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "completo: onCreate");
    }

    public void onStart(){
        super.onStart();
        Log.d(tag, "visible: onStart");
    }



    public void onStop() {
        super.onStop();
        super.onStart();
        Log.d(tag, "visible: onStop");
    }

    public void onPause() {
        super.onPause();
        super.onStart();
        Log.d(tag, "invisible: onPause");
    }

    public void onResume() {
        super.onResume();
        super.onStart();
        Log.d(tag, "invisible: onResume");
    }

    public void onDestroy() {
        super.onDestroy();
        super.onStart();
        Log.d(tag, "completo: onDestroy");
    }

}