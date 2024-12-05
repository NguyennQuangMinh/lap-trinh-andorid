package com.example.bai17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonContact, buttonMessage, buttonShowCaloglog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        getPermission();
        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowContact.class);
                startActivity(intent);
            }
        });
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowMessage.class);
                startActivity(intent);
            }
        });
        buttonShowCaloglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] projection = new String[]{
                        CallLog.Calls.DATE,
                        CallLog.Calls.NUMBER,
                        CallLog.Calls.DURATION
                };

                Cursor c = getContentResolver().query(
                        CallLog.Calls.CONTENT_URI,
                        projection,
                        CallLog.Calls.DURATION + " < ?", new String[]{"30"},
                        CallLog.Calls.DATE + " ASC"
                );
                c.moveToFirst();
                String s = "";
                while (!c.isAfterLast()) {
                    for (int i = 0; i < c.getColumnCount(); i++) {
                        s = s + c.getString(i) + "-";
                    }
                    s = s + "\n";
                    c.moveToNext();
                }
                c.close();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, 999);
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_SMS}, 999);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 999);
        }
    }


    private void InitWidget() {
        buttonContact = (Button) findViewById(R.id.buttonContact);
        buttonMessage = (Button) findViewById(R.id.buttonMessage);
        buttonShowCaloglog = (Button) findViewById(R.id.buttonShowcalogLog);
    }
}