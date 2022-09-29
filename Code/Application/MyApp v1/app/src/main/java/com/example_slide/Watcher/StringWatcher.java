package com.example_slide.Watcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

public class StringWatcher implements TextWatcher {

    private Context context;
    public StringWatcher(Context context) {
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
        if (editable != null &&  editable.length() != 0) {

            for (int i = 0; i < editable.length(); ++i) {
                char c = editable.toString().charAt(i);
                if (Character.isAlphabetic(c) == false) {
                    Toast.makeText(context, "insert only letters", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }
}
