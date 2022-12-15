package com.staffApp;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.staffApp.Database.DataBaseAdapter;
import com.staffApp.RecyclerView.RVAdapter;

import java.util.HashMap;

public class UpdateDialog extends android.app.Dialog {

    private EditText editName,editPosition,editEmail,editPhone;
    private TextView updateButton;
    private android.app.Dialog dialog;
    boolean addResult;
    DataBaseAdapter dataBaseAdapter;
    private  String key;






    public boolean getAddResult() {
        return addResult;
    }

    private void setAddResult(boolean addResult) {
        this.addResult = addResult;
    }

    public UpdateDialog(@NonNull Context context, RVAdapter.ViewHolder holder) {
        super(context);
        this.dialog=new android.app.Dialog(getContext());
        dataBaseAdapter=new DataBaseAdapter();
        dialog.setContentView(R.layout.update_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.curve_shape);
        editName=dialog.findViewById(R.id.update_name_dialog);
        editPosition=dialog.findViewById(R.id.update_position_dialog);
        editEmail=dialog.findViewById(R.id.update_email_dialog);
        editPhone=dialog.findViewById(R.id.udpate_phone_dialog);
        updateButton =dialog.findViewById(R.id.dialog_update_btn);

        editName.setText(holder.name.getText());
        editPosition.setText(holder.position.getText());
        editEmail.setText(holder.email.getText());
        editPhone.setText(holder.phone.getText());

        updateButton.setOnClickListener(v->{ addOnClickListener();
        });
        dialog.show();

    }

    /***
     * add onClickListener to addbutton, when user try to insert new data
     */
    public void addOnClickListener() {

        updateButton.setOnClickListener(v -> {
               updateData();
            });

    }

    private boolean checkInput() {
        return true;
    }


    public void updateData() {

        HashMap<String,Object> map=new HashMap<>();
        map.put("name",editName.getText().toString());
        map.put("position",editPosition.getText().toString());
        map.put("email",editEmail.getText().toString());
        map.put("phone",editPhone.getText().toString());
        dataBaseAdapter.update(getKey(),map).addOnSuccessListener(unused -> {
            dialog.dismiss();
            Toast.makeText(getContext(),"Data updated",Toast.LENGTH_SHORT);
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(getContext(),"Update Failed",Toast.LENGTH_SHORT);
            }
        });


    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



}
