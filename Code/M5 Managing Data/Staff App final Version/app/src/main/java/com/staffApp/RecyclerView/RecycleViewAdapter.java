package com.staffApp.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.staffApp.Database.Employee;
import com.staffApp.MainActivity;
import com.staffApp.R;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<Employee> element=new ArrayList<>();

    public RecycleViewAdapter(Context context){
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
        viewHolder.edit.setOnClickListener(v->{
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("EDIT",employee);
            context.startActivity(intent);
        });

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
