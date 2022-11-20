package com.staffApp.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.staffApp.Database.DataBaseAdapter;
import com.staffApp.Database.Employee;
import com.staffApp.InsertDialog;
import com.staffApp.R;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity implements InsertDialog.InsertDialogListener {

    SwipeRefreshLayout swipeRefleshLayout;
    RecyclerView recyclerView;
    RecycleViewAdapter recycleViewAdapter;
    DataBaseAdapter dataBaseAdapter;
    FloatingActionButton addbutton;
    boolean isLoading=false;
   // String key=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recycle_activity);
      /*  swipeRefleshLayout=findViewById(R.id.swipe);*/
        addbutton=findViewById(R.id.add_button);
        addbutton.setOnClickListener(v->{ openDialog();});
        recyclerView=findViewById(R.id.recycleView);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recycleViewAdapter=new RecycleViewAdapter(this);
        recyclerView.setAdapter(recycleViewAdapter);
        dataBaseAdapter=new DataBaseAdapter();

        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
                int itemNum=linearLayoutManager.getItemCount();
                int lastVisible=linearLayoutManager.findLastVisibleItemPosition();
                if(itemNum <lastVisible+3)
                {
                    if(!isLoading)
                    {
                        isLoading=true;
                        loadData();
                    }
                }
            }
        });

        addSwipeTodelete();


    }

    private void openDialog() {
        InsertDialog insertDialog=new InsertDialog();
        insertDialog.show(getSupportFragmentManager(),"Insert Dialog");

    }


    private void addSwipeTodelete() {

        // on below line we are creating a method to create item touch helper
        // method for adding swipe to delete functionality.
        // in this we are specifying drag direction and position to right
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                return false;
            }

            @Override
            public float getMoveThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
                return 0.7f;
            }


            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                ArrayList<Employee> employees=recycleViewAdapter.getElement();
                Employee deletedEmployee = employees.get(viewHolder.getAdapterPosition());

                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                employees.remove(viewHolder.getAdapterPosition());
                dataBaseAdapter.remove(deletedEmployee.getKey());
                // below line is to notify our item is removed from adapter.
                recycleViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                // below line is to display our snackbar with action.
                Snackbar.make(recyclerView, deletedEmployee.getName()+" Deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        dataBaseAdapter.add(deletedEmployee);
                        employees.add(position, deletedEmployee);

                        // below line is to notify item is
                        // added to our adapter class.
                        recycleViewAdapter.notifyItemInserted(position);
                    }
                }).show();
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView);


    }


    private void loadData() {
           /* swipeRefleshLayout.setRefreshing(true);*/

            dataBaseAdapter.get().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Employee> employeeArrayList=new ArrayList<>();
                        //return list of node
                        for(DataSnapshot ds:snapshot.getChildren())
                        {

                            Employee employee=ds.getValue(Employee.class);
                            employee.setKey(ds.getKey());
                            employeeArrayList.add(employee);
                           // key=ds.getKey();
                        }
                        Log.d("lista employee", String.valueOf(employeeArrayList.size()));
                        recycleViewAdapter.clearItems();

                        recycleViewAdapter.setItems(employeeArrayList);
                        recycleViewAdapter.notifyDataSetChanged();
                        isLoading=false;
                       /* swipeRefleshLayout.setRefreshing(false);*/
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    /*swipeRefleshLayout.setRefreshing(false);*/
                }
            });


    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.d("entrato","entrato in onResume ");
        loadData();
    }

    @Override
    public void insertData(String username, String position) {

        Employee employee=new Employee(username,position);
        dataBaseAdapter.add(employee)
                .addOnSuccessListener(suc -> Toast.makeText(this, "Record inserted", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(er -> Toast.makeText(this, er.getMessage(), Toast.LENGTH_SHORT).show());
        loadData();

    }
}
