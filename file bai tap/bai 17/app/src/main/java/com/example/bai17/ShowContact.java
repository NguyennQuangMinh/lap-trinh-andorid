package com.example.bai17;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowContact extends AppCompatActivity {
    Button buttonBack;
    ListView listViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        buttonBack =(Button) findViewById(R.id.buttonBack);
        listViewContact =(ListView) findViewById(R.id.listViewContact);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        ShowContact();
    }
    public void ShowContact(){
        listViewContact = (ListView) findViewById(R.id.listViewContact);
        // lay URL
        Uri uri = Uri.parse("content://contacts/people");
        ArrayList<String> List = new ArrayList<String>();
        // cursor
        Cursor c1 = getContentResolver().query(uri, null, null, null, null);
        c1.moveToFirst();
        while (!c1.isAfterLast()){
            String s = "";
            // lay id
            String idColumnName = ContactsContract.Contacts._ID;
            int idIndex = c1.getColumnIndex(idColumnName);
            s = c1.getString(idIndex) + "-";
            //Lay name
            String nameColumnName = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = c1.getColumnIndex(nameColumnName);
            s = s + c1.getString(nameIndex);
            List.add(s);
            c1.moveToNext();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List);
            listViewContact.setAdapter(adapter);
        }
    }
}