package com.App.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.App.MainActivity;
import com.App.R;


public class LoginFragment extends Fragment {

    ImageButton loginButton;
    ImageView googleLoginButton;
    TextView signupButton;
    Button signinWithButton;
    Context context;
    EditText editEmail, editPasswod;
    boolean found=false;
    RegistrationFragment registrationFragment;


    public LoginFragment(Context context) {
        this.context=context;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editEmail=view.findViewById(R.id.login_edit_username);
        editPasswod =view.findViewById(R.id.login_edit_password);
        loginButton=view.findViewById(R.id.login_btn_done);
        loginButton.setOnClickListener(v->{loginCredential();});
        signupButton=view.findViewById(R.id.login_bnt_signup);
        signupButton.setOnClickListener(v->{ getFragmentManager().beginTransaction().replace(R.id.fragment_container,registrationFragment).addToBackStack("Login").commit();});

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationFragment=new RegistrationFragment(context);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    private void loginCredential() {

        checkLoginCredential();


    }


    private void checkLoginCredential() {



        boolean correct=true;
        String user=editEmail.getText().toString();
        String pass= editPasswod.getText().toString();


        if(user.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(user).matches())
        {
            editEmail.setError("insert valid Email");
            editEmail.requestFocus();
            correct=false;

        }
        if(pass.isEmpty()){
            editPasswod.setError("Insert Password");
            editPasswod.requestFocus();
            correct=false;
        }
        if(correct) {
            signIn(user, pass);
        }

    }


    private void signIn(String email,String password) {
        startActivity(new Intent(context, MainActivity.class));
        getActivity().finish();



    }





}