package com.App.Fragment;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.App.MainActivity;
import com.App.R;
import com.App.Watchers.AgeWatcher;
import com.App.Watchers.NameWatcher;



public class RegistrationFragment extends Fragment  {

    Context context;
    ImageButton doneBtn;
    EditText editName,editAge,editEmail,editPassword;
    AgeWatcher ageWatcher;
    NameWatcher nameWatcher;
    TextView loginBackBtn;




    public RegistrationFragment(Context context) {
        this.context=context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doneBtn=view.findViewById(R.id.registration_btn_done);
        editName=view.findViewById(R.id.registration_edit_username);
        editAge=view.findViewById(R.id.registration_edit_age);
        editPassword=view.findViewById(R.id.registration_edit_password);
        loginBackBtn=view.findViewById(R.id.registration_back_to_login);
        loginBackBtn.setOnClickListener(v->{ backToLogin();});
        ageWatcher=new AgeWatcher(context);
        nameWatcher=new NameWatcher(context,editName);
        editEmail=view.findViewById(R.id.registration_edit_email);
        doneBtn.setOnClickListener(v->{checkRegistrationUser();});

    }

    private void backToLogin() {
        LoginFragment loginFragment=new LoginFragment(context);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,loginFragment).addToBackStack("Registration").commit();
    }

    public void checkRegistrationUser() {

        String username=editName.getText().toString().trim();
        String age=editAge.getText().toString().trim();
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();
        if(username.isEmpty())
        {
            editName.setError("Name is Required");
            editName.requestFocus();
            return;
        }
        if(age.isEmpty())
        {
            editAge.setError("Age is Required");
            editAge.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Email  is Required");
            editEmail.requestFocus();
            return;
        } else {

        if (password.length()<6) {
                editPassword.setError("Password lenght should be 6 characters at least!");
                editPassword.requestFocus();
                return;
            }
        }
        startActivity(new Intent(context, MainActivity.class));
        return;



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }
}