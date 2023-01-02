package com.staffApp.Dialog;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.staffApp.Database.DataBaseAdapter;
import com.staffApp.Models.Employee;
import com.staffApp.R;
import com.staffApp.Watchers.NameWatcher;

public class InsertDialog extends android.app.Dialog {

    private EditText editName,editPosition,editEmail,editPhone;
    private TextView addButton;
    private android.app.Dialog dialog;
    boolean addResult;
    DataBaseAdapter dataBaseAdapter;
    String allCountryRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
    String patternName="[A-Za-z]";
    NameWatcher nameWatcher;

    public boolean getAddResult() {
        return addResult;
    }

    private void setAddResult(boolean addResult) {
        this.addResult = addResult;
    }

    public InsertDialog(@NonNull Context context) {
        super(context);
        this.dialog=new android.app.Dialog(getContext());
        dataBaseAdapter=new DataBaseAdapter();
        dialog.setContentView(R.layout.insert_dialog);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.curve_shape);
        editName=dialog.findViewById(R.id.add_name_dialog);
        editPosition=dialog.findViewById(R.id.add_position_dialog);
        editEmail=dialog.findViewById(R.id.add_email_dialog);
        editPhone=dialog.findViewById(R.id.add_phone_dialog);
        addButton=dialog.findViewById(R.id.dialog_add_btn);
        addButton.setOnClickListener(v->{ addOnClickListener();});
        dialog.show();
        nameWatcher=new NameWatcher(context,editPosition);
    }

    /***
     * add onClickListener to addbutton, when user try to insert new data
     */
    public void addOnClickListener() {


            addButton.setOnClickListener(v -> {

                if(checkInput()) {
                        dialog.dismiss();
                        insertData();

                    }

            });


    }

    private boolean checkInput() {
        String name=editName.getText().toString().trim();
        String postion=editPosition.getText().toString().trim();
        String email=editEmail.getText().toString().trim();
        String phone=editPhone.getText().toString().trim();


        if(name.isEmpty())
        {

            editName.setError("Name is Required");
            editName.requestFocus();
            return false;
        }
      /*  else if(!Pattern.compile(patternName).matcher(name).matches()){

                editName.setError("Name must contains only letters");
                editName.requestFocus();
                return false;

        }*/
        if(postion.isEmpty())
        {
            editPosition.setError("position is Required");
            editPosition.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Email  is Required");
            editEmail.requestFocus();
            return false;
        }


           /* if (!Patterns.PHONE.matcher(phone).matches()) {
                editPhone.setError("");
                editPhone.requestFocus();
                return false;
            }*/


        return true;


    }


    public void insertData() {

        Employee employee=new Employee();
        employee.setName(editName.getText().toString());
        employee.setPosition(editPosition.getText().toString());
        employee.setEmail(editEmail.getText().toString());
        employee.setPhone(editPhone.getText().toString());
        dataBaseAdapter.add(employee).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dialog.dismiss();
                Toast.makeText(getContext(),"Insert Data Successfully",Toast.LENGTH_SHORT);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(getContext(),"Insert Failed",Toast.LENGTH_SHORT);
            }
        });


    }



}
