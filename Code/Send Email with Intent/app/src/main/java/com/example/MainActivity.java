package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast=new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.Linear));
        toast.setView(view);
        toast.show();

    }

    public void sendEmail(View view)
    {
        boolean is=false;
        if(view.getId()==R.id.sendEmail)
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] receivers={"nbicocchi@unimore.it","267705@studenti.unimore.it"};
            intent.putExtra(Intent.EXTRA_EMAIL,receivers);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Hello this mail is sent from my app");
            intent.putExtra(Intent.EXTRA_TEXT,"hello from my app");
            //specify MIME type for Email
            intent.setType("message/rfc822");
            Intent chooser=Intent.createChooser(intent,"Send Email");
            startActivity(chooser);


        }
        if(is)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:19.076,72.877"));
            Intent chooser=Intent.createChooser(intent,"Launch Mapp");
            startActivity(chooser);
        }
    }
}