package com.staffApp.Fragment;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.staffApp.Models.User;
import com.staffApp.R;
import com.staffApp.RecyclerView.RVActivity;


public class LoginFragment extends Fragment {

    ImageButton loginButton;
    ImageView googleLoginButton;
    TextView signupButton;
    Button signinWithButton;
    Context context;
    EditText editEmail, editPasswod;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
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
        firebaseAuth=FirebaseAuth.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    private void loginCredential() {

        String email=editEmail.getText().toString();
        String password= editPasswod.getText().toString();
        FirebaseDatabase.getInstance().getReference().child("User")
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User user = snapshot.getValue(User.class);
                            if(email.equals(user.getEmail()) && password.equals(user.getPassword()))
                            {
                                Log.d("entrato"," entrato");
                                startActivity(new Intent(context,RVActivity.class));


                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

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
        startActivity(new Intent(context, RVActivity.class));
        getActivity().finish();
        /*firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.d(" firebase authentication user",firebaseAuth.getCurrentUser().getEmail());
                startActivity(new Intent(context,RecycleViewActivity.class));
                getActivity().finish();
            }

        });*/


    }


   /* private void goToMainActivity() {
        Intent intent=new Intent(context, RecycleViewActivity.class);
        startActivity(intent);
    }
*/



}