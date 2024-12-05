package com.example.de1thaychua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    EditText editTextName, editTextSinger, editTextTime;
    Button btnAdd, btnBack;
    private Database dataBase = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTextName=(EditText) findViewById(R.id.edName);
        editTextSinger=findViewById(R.id.edSinger);
        editTextTime=findViewById(R.id.edTime);
        btnAdd=findViewById(R.id.btnAdd);
        btnBack=findViewById(R.id.btnBack);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.addSong(new Baihat(editTextName.getText().toString(), editTextSinger.getText().toString(), Float.parseFloat(editTextTime.getText().toString())));
                Intent it=new Intent(AddActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(AddActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

}
