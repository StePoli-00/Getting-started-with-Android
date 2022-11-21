/*
package com.staffApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.staffApp.Database.DataBaseAdapter;
import com.staffApp.Models.Employee;
import com.staffApp.RecyclerView.RecycleViewActivity;
import com.staffApp.RecyclerView.RecycleViewAdapter;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public final int MODIFIED=2;
    private  EditText editName,editPosition;
    private Button submit,open;
    RecycleViewAdapter recycleViewAdapter;

    DataBaseAdapter dataBaseAdapter;
    Employee emp_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName=findViewById(R.id.edit_name);
        editPosition=findViewById(R.id.edit_position);
        open=findViewById(R.id.open_btn);
        submit=findViewById(R.id.submit_btn);
        dataBaseAdapter=new DataBaseAdapter();
        recycleViewAdapter=new RecycleViewAdapter(this);
        open.setOnClickListener(v-> {
            Intent intent=new Intent(MainActivity.this, RecycleViewActivity.class);
            startActivity(intent);
        });



        emp_edit=(Employee) getIntent().getSerializableExtra("EDIT");

        if(emp_edit!=null)
        {
           submit.setText("Update");
           editName.setText(emp_edit.getName());
           editPosition.setText(emp_edit.getPosition());
           open.setVisibility(View.GONE);
        }
        else
        {
            submit.setText("Submit");
            open.setVisibility(View.VISIBLE);
        }


        submit.setOnClickListener(v-> {
            if(emp_edit==null) {

                addEmployee();
            }
            else
            {
                updateEmployee();
            }

        } );


    }



    private void addEmployee() {
      Employee employee=new Employee(editName.getText().toString(),editPosition.getText().toString());
          dataBaseAdapter.add(employee)
                  .addOnSuccessListener(suc -> Toast.makeText(this, "Record inserted", Toast.LENGTH_SHORT).show())
                  .addOnFailureListener(er -> Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show());


    }

    private void updateEmployee() {

        HashMap<String,Object> hashMap; hashMap=new HashMap<>();
        hashMap.put("name",editName.getText().toString());
        hashMap.put("position",editPosition.getText().toString());
        dataBaseAdapter.update(emp_edit.getKey(),hashMap).addOnSuccessListener(suc-> {
            Toast.makeText(this,"Record Updated",Toast.LENGTH_SHORT).show();
                    this.setResult(MODIFIED);
                    finish();
            }).addOnFailureListener(er -> Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show());

    }


    */
/*private void setSwipeToDelete() {
        //set swipe direction to left
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                setDeleteIcon(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            private void setDeleteIcon(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            }

            @Override
            public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
                return 0.7f;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //remove data from recycleview and database
                int position=viewHolder.getAdapterPosition();
                ArrayList<Employee> element=recycleViewAdapter.getElement();
                Employee employee=element.get(position);

                DataBaseAdapter dataBaseAdapter=new DataBaseAdapter();
                dataBaseAdapter.remove(employee.getKey()).addOnSuccessListener(suc-> {
                                    Toast.makeText(MainActivity.this,"Record Removed",Toast.LENGTH_SHORT).show();
                                    recycleViewAdapter.notifyItemRemoved(position);
                                    element.remove(position);
                                }
                        )
                        .addOnFailureListener(er -> Toast.makeText(MainActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show());


            }
        });
    }*//*




}*/
