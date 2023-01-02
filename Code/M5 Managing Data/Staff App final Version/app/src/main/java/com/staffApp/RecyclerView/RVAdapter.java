package com.staffApp.RecyclerView;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.staffApp.Database.DataBaseAdapter;
import com.staffApp.Models.Employee;
import com.staffApp.R;
import com.staffApp.Dialog.UpdateDialog;

public class RVAdapter extends  FirebaseRecyclerAdapter<Employee,RVAdapter.ViewHolder>{



    private Context context;
    private DataBaseAdapter dataBaseAdapter;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RVAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
        dataBaseAdapter=new DataBaseAdapter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder,  int position, @NonNull Employee model) {

        //we can choose any view, each of them can return context object
        setContext(holder.email.getContext());
        int index=position;
        ViewHolder viewHolder=(ViewHolder) holder;
        viewHolder.name.setText(model.getName());
        viewHolder.position.setText(model.getPosition());
        viewHolder.email.setText(model.getEmail());
        viewHolder.phone.setText(model.getPhone());
        viewHolder.editButton.setOnClickListener(v -> createUpdateDialog(holder,index,model));
        viewHolder.deleteButton.setOnClickListener(v-> createDeleteDialog(position));
    }

    private void createDeleteDialog(int position) {

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Confirm Delete Action");
                builder.setMessage("Deleted data cannot be retrieved");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //FirebaseDatabase.getInstance().getReference().child("Employee").child(getRef(position).getKey()).removeValue();
                        String path[]= String.valueOf(getRef(position)).split("/Employee/");
                        dataBaseAdapter.remove(path[1]);






                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

    }

   /* public void createUpdateDialog( int position, @NonNull Employee model)
    {
        final DialogPlus dialogPlus= DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.update_popup))
                .setExpanded(false,1400)
                .create();

        //return a reference of element inside  our update pop up
        View view=dialogPlus.getHolderView();
        EditText name=view.findViewById(R.id.update_name);
        EditText pos=view.findViewById(R.id.update_position);
        EditText email=view.findViewById(R.id.update_email);
        EditText phone=view.findViewById(R.id.update_phone);
        Button editButton=view.findViewById(R.id.update_btn);
        name.setText(model.getName());
        pos.setText(model.getPosition());
        email.setText(model.getEmail());
        phone.setText(model.getPhone());
        dialogPlus.show();
        editButton.setOnClickListener(v->{updateItem(dialogPlus,position,name.getText().toString(),pos.getText().toString()
        ,email.getText().toString(),phone.getText().toString());});
    }*/

    public void createUpdateDialog(ViewHolder holder, int position, @NonNull Employee model){
        UpdateDialog updateDialog=new UpdateDialog(context,holder);
        String path[]=String.valueOf(getRef(position)).split("/Employee/");
        updateDialog.setKey(path[1]);

    }







    public class ViewHolder extends  RecyclerView.ViewHolder {

        public TextView name,position,email,phone;
        ImageView imageView;
        ImageView editButton,deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textViewName);
            position=itemView.findViewById(R.id.textViewPosition);
            imageView=itemView.findViewById(R.id.imageView);
            email=itemView.findViewById(R.id.textViewEmail);
            phone=itemView.findViewById(R.id.textViewPhone);
            editButton=itemView.findViewById(R.id.imageButton_edit);
            deleteButton=itemView.findViewById(R.id.imageButton_delete);

        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
