package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendEmail(View view)
    {
        if(view.getId()==R.id.sendEmail)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] receivers={"poli14.sp@gmail.com","267705@studenti.unimore.it"};
            intent.putExtra(Intent.EXTRA_EMAIL,receivers);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Hello this mail is sent from my app");
            intent.putExtra(Intent.EXTRA_TEXT,"hello from my app");
            //specify MIME type for Email
            intent.setType("message/rfc822");
            Intent chooser=Intent.createChooser(intent,"Send Email");
            startActivity(chooser);


        }
    }
}