package com.staffApp;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.staffApp.Database.DataBaseAdapter;
import com.staffApp.Database.Employee;

public class InsertDialog extends Dialog {

    private EditText editName, editPosition;
    private TextView addButton;
    private DataBaseAdapter dataBaseAdapter;
    boolean addResult;

    public boolean getAddResult() {
        return addResult;
    }

    private void setAddResult(boolean addResult) {
        this.addResult = addResult;
    }

    public InsertDialog(@NonNull Context context, DataBaseAdapter dataBaseAdapter) {
        super(context);
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.insert_dialog);
        editName=dialog.findViewById(R.id.add_name_dialog);
        editPosition=dialog.findViewById(R.id.add_position_dialog);
        addButton=dialog.findViewById(R.id.dialog_add_btn);
        dialog.show();
        this.dataBaseAdapter=dataBaseAdapter;
    }

    public void createInsertDialog() {


            addButton.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    String name=editName.getText().toString();
                    String position=editPosition.getText().toString();
                    if(name.length()!=0 && position.length()!=0) {
                        boolean ris=checkInput(name,position);
                        if(ris==true) {
                            dismiss();
                            insertData(name, position);


                        }
                        else
                        {
                            editName.setError("Invalid Input");
                            editPosition.setError("Invalid Input");
                        }
                    }
                    else
                    {
                        editName.setError("Empty field");
                        editPosition.setError("Empty field");

                    }



                }
            });


    }

    private boolean checkInput(String name, String position) {
        return true;
    }


    public void insertData(String username, String position) {

        dismiss();
        Employee employee=new Employee(username,position, FirebaseAuth.getInstance().getCurrentUser().getUid());
        dataBaseAdapter.add(employee)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        setAddResult(true);

                        Toast.makeText(getContext(), "Record inserted", Toast.LENGTH_SHORT).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        setAddResult(false);
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }



}
