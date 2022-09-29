package com.example_slide.Watcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

public class AgeWatcher implements TextWatcher {

    private Context context;
    public AgeWatcher(Context context) {
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


        try {
            for (int i = 0; i < editable.length(); ++i) {
                char c = editable.toString().charAt(i);
                if (Character.isDigit(c) == false) {
                    Toast.makeText(context, "insert only numbers", Toast.LENGTH_SHORT).show();
                }
            }
            int n = Integer.parseInt(editable.toString());
            System.out.println(n);
            if (n < 0 || n > 110) {
                Toast.makeText(context, "invalid age number", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
