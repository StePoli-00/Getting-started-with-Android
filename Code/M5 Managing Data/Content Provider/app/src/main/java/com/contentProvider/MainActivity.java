package com.contentProvider;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    private TextView textViewQueryResult;
    private Button buttonLoadData;
    private EditText editText;
    private String[] selectColumns = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Phone.NUMBER};
    private String whereClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?";
    private String orderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
   


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edit_Text);
        textViewQueryResult = findViewById(R.id.textViewQueryResult);
        buttonLoadData = findViewById(R.id.buttonLoadData);
        buttonLoadData.setOnClickListener(v->getContact());

    }

    private void getContact() {
        getPhoneContacts();

    }
    private void getPhoneContacts() {

        Log.d("name",editText.getText().toString());
        String[] whereArguments=new String[]{editText.getText().toString()};

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, selectColumns, whereClause,whereArguments, null);
        StringBuffer queryResult = new StringBuffer();
        if (cursor.getCount() > 0) {
        while (cursor.moveToNext())
        {

            queryResult.append(cursor.getString(0)+" cell:\n"+String.valueOf(cursor.getLong(1)) + "\n");
        }
        textViewQueryResult.setText(queryResult);

        }
    }

}