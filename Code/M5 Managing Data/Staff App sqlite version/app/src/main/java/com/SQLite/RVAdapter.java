package com.SQLite;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private ArrayList<Employee> employees;



    public RVAdapter(Context context, ArrayList<Employee> element){

        this.context=context;
        this.employees =element;
    }

    public void setItems(ArrayList<Employee> subElement) {

        employees.addAll(subElement);
        Log.d("Settati",String.valueOf(employees.size()));

    }


    public void clearItems(){
        employees.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Employee e=employees.get(position);
        this.onBindViewHolder(holder,position,e);
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Employee model) {
        ViewHolder viewHolder=(ViewHolder) holder;
        viewHolder.name.setText(model.getName());
        viewHolder.position.setText(model.getPosition());
        viewHolder.editButton.setOnClickListener(v -> updateEmployee(holder,position,model));
        viewHolder.deleteButton.setOnClickListener(v-> deleteEmployee(position,model));
    }

    private void updateEmployee(RecyclerView.ViewHolder holder, int position, Employee model) {

        Intent intent=new Intent(context,UpdateActivity.class);
        intent.putExtra("EDIT",model);
        context.startActivity(intent);
    }

    private void deleteEmployee( int position, Employee emp) {
        DataBaseAdapter dataBaseAdapter=new DataBaseAdapter(context);
        dataBaseAdapter.deleteData(emp.getName());
            Toast.makeText(context, "Employee is removed", Toast.LENGTH_SHORT).show();
            notifyItemRemoved(position);
            employees.remove(emp);

    }


    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,position;
        ImageView editButton,deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textViewName);
            position=itemView.findViewById(R.id.textViewPosition);
            editButton=itemView.findViewById(R.id.imageButton_edit);
            deleteButton=itemView.findViewById(R.id.imageButton_delete);

        }
    }


}
