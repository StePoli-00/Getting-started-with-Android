package com.staffApp.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.staffApp.R;


public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView name,position;
    public ImageButton edit;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.textViewName);
        position=itemView.findViewById(R.id.textViewPosition);
        edit=itemView.findViewById(R.id.imageButton_edit);

    }
}
