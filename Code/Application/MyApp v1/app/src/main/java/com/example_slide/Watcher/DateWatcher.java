package com.example_slide.Watcher;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;


import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateWatcher implements TextWatcher {

    private Context context;
    public DateWatcher(Context context) {
        this.context=context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


        if (editable != null && editable.length() != 0) {
          /*

            String[] format = editable.toString().split("-");
            int day = Integer.parseInt(format[0]);
            int month = Integer.parseInt(format[1]);
            int year = Integer.parseInt(format[2]);*/


        }
    }
}
