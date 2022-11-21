package com.staffApp;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.staffApp.RecyclerView.RecycleViewActivity;
import com.staffApp.Registration.RegistrationFragment;


public class LoginFragment extends Fragment {

    ImageButton loginButton;
    ImageView googleLoginButton;
    TextView signupButton;
    Button signinWithButton;
    Context context;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    EditText editUsername,editpassword;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    boolean found=false;

    RegistrationFragment registrationFragment;



    public LoginFragment(Context context) {
        this.context=context;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationFragment=new RegistrationFragment(context);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        firebaseAuth=FirebaseAuth.getInstance();
        gsc=GoogleSignIn.getClient(context,gso);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkAlreadyLogIn();
        editUsername=view.findViewById(R.id.login_edit_username);
        editpassword=view.findViewById(R.id.login_edit_password);
        loginButton=view.findViewById(R.id.login_btn_done);
        loginButton.setOnClickListener(v->{checkLoginCredential();});
        signupButton=view.findViewById(R.id.login_bnt_signup);
        signupButton.setOnClickListener(v->{ getFragmentManager().beginTransaction().replace(R.id.fragment_container,registrationFragment).addToBackStack("Login").commit();});
        googleLoginButton=view.findViewById(R.id.login_btn_google_log);
        googleLoginButton.setOnClickListener(v -> {googleSignIn();});


    }


    private void checkLoginCredential() {
        boolean correct=true;
        String user=editUsername.getText().toString();
        String pass=editpassword.getText().toString();


        if(user.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(user).matches())
        {
            editUsername.setError("insert valid Email");
            editUsername.requestFocus();
            correct=false;

        }
        if(pass.isEmpty()){
            editpassword.setError("Insert Password");
            editpassword.requestFocus();
            correct=false;
        }
        if(correct) {
            signIn(user, pass);
        }

    }

    private void checkAlreadyLogIn() {

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(context);
        if(account!=null)
        {
            goToMainActivity();
        }
    }


    private void googleSignIn() {
        Intent intent=gsc.getSignInIntent();
            startActivityForResult(intent, 1000);


    }


    private void signIn(String email,String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.d(" firebase authentication user",firebaseAuth.getCurrentUser().getEmail());
                startActivity(new Intent(context,RecycleViewActivity.class));
                getActivity().finish();
            }

        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(context);
                //firebaseAuth.createUserWithEmailAndPassword(account.getEmail(),"pass");
                Log.d("google user",account.getEmail());
                goToMainActivity();
            } catch (ApiException e) {
                Toast.makeText(context,"Login Failed",Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void goToMainActivity() {
        Intent intent=new Intent(context, RecycleViewActivity.class);
        startActivity(intent);
    }




}