package com.example_slide;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private TextView welcome;
    private EditText email,password;
    private Button login,singup;
    private Intent i;
    private FakeDb fakeDb;
    private HashMap<String, String> users=new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users.put("nicola.bicocchi@unimore.it","nicola");
        users.put("267705@studenti.unimore.it","stefano");
        fakeDb=FakeDb.create(users);

        setContentView(R.layout.login_layout);

        //Typeface
        welcome=findViewById(R.id.Welcome);
        Typeface myfont=Typeface.createFromAsset(getAssets(),"font/poppins/Poppins-Black.otf");
        email=findViewById(R.id.email_edit);
        password=findViewById(R.id.pass_edit);
        welcome.setTypeface(myfont);
        checkBox=findViewById(R.id.checkbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked())
                {
                    Log.d("Debug","Remember credential ");
                }
                else
                {
                    Log.d("Debug","Don't remember credential");
                }
            }
        });

        login=findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(fakeDb.verifyCredential(email.getText().toString(),password.getText().toString())) {
                    i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.putExtra("name",email.getText().toString());
                    startActivity(i);

                }else
                {
                    Toast.makeText(LoginActivity.this,"Incorrect Credential",Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        singup=findViewById(R.id.singup);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(LoginActivity.this,SignUpActivity2.class);

                startActivity(i);
            }
        });



    }
}
