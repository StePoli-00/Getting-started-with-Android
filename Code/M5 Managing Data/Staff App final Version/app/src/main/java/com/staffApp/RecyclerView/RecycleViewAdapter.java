package com.staffApp.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.staffApp.Database.Employee;
import com.staffApp.R;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private  FragmentManager fragmentManager;
    private ArrayList<Employee> element=new ArrayList<>();

    public RecycleViewAdapter(Context context, FragmentManager fragmentManager){

        this.fragmentManager=fragmentManager;
        this.context=context;
    }
    
    public void setItems(ArrayList<Employee> subElement) {
        Log.d("oggetti settati",String.valueOf(subElement.size()));
        element.addAll(subElement);
        
    }

    public void clearItems(){
        element.clear();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder) holder;
        Employee employee=element.get(position);
        viewHolder.name.setText(employee.getName());
        viewHolder.position.setText(employee.getPosition());
        viewHolder.edit.setOnClickListener(v->{ openUpdateDialog();

          });


    }


   private void openUpdateDialog() {
       Dialog dialog=new Dialog(context);
       dialog.setContentView(R.layout.update_dialog);
       EditText editName=dialog.findViewById(R.id.update_name_dialog);
       EditText editPosition=dialog.findViewById(R.id.update_position_dialog);

    }







    public ArrayList<Employee> getElement() {
        return element;
    }

    public void setElement(ArrayList<Employee> element) {
        this.element = element;
    }



    @Override
    public int getItemCount() {
        return element.size();
    }




}
