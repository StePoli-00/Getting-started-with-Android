package com.staffApp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
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
    RegistrationFragment registrationFragment;
    GoogleApiClient googleApiClient;


    public LoginFragment(Context context) {
        this.context=context;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationFragment=new RegistrationFragment(context);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc=GoogleSignIn.getClient(context,gso);
        checkAlreadyLogIn();

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
        loginButton=view.findViewById(R.id.login_btn_done);
        signupButton=view.findViewById(R.id.login_bnt_signup);
        signupButton.setOnClickListener(v->{
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,registrationFragment).commit();

        });
        googleLoginButton=view.findViewById(R.id.login_btn_google_log);
        googleLoginButton.setOnClickListener(v -> {
            signIn();
        });

    }

    private void checkAlreadyLogIn() {
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(context);
        if(account!=null)
        {
            goToMainActivity();
        }
    }

    private void signIn() {
        Intent signInIntent=gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                Log.d("task",task.getResult().getDisplayName());
                task.getResult(ApiException.class);
            } catch (ApiException e) {
                Toast.makeText(context, "Something goes wrong", Toast.LENGTH_SHORT).show();
            }

        }else
        {
            goToMainActivity();
        }
    }

    private void goToMainActivity() {
        Intent intent=new Intent(context, RecycleViewActivity.class);
        startActivity(intent);
    }




}