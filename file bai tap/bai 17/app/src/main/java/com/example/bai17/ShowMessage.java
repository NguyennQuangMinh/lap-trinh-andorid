package com.example.bai17;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMessage extends AppCompatActivity {

    Button buttonMes;
    ArrayList<String> activity_show_mes;
    ArrayAdapter<String> adapter;
    ListView listViewMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);
        buttonMes = findViewById(R.id.buttonMes);
        buttonMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listViewMes = findViewById(R.id.listViewMes);
        activity_show_mes = new ArrayList<String>();
        intView();
    }

    private void intView() {
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        activity_show_mes.clear();
        while (cursor.moveToNext()){
            int index_phonnumber = cursor.getColumnIndex("address");
            int index_date = cursor.getColumnIndex("date");
            int index_body = cursor.getColumnIndex("body");
            String phone_number = cursor.getString(index_phonnumber);
            String date_ = cursor.getString(index_date);
            String body_ = cursor.getString(index_body);
            activity_show_mes.add(phone_number+"/n"+date_+"/"+body_);
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,activity_show_mes);
        listViewMes.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}