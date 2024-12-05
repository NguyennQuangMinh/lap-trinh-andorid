package com.example.de1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityThem extends AppCompatActivity {
    Button buttonAdd,buttonBack;
    EditText editTextBaiHat, editTextThoiGian, editTextTacGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them);
        Init();
        buttonAdd.setOnClickListener(v -> {
            String song=editTextBaiHat.getText().toString();
            String time=editTextThoiGian.getText().toString();
            String author=editTextTacGia.getText().toString();

            Intent it1=new Intent(MainActivityThem.this, MainActivity.class);
            it1.putExtra("BAIHAT", song);
            it1.putExtra("THOIGIAN", time);
            it1.putExtra("TACGIA", author);
            //Toast.makeText(MainActivityCreate.this, name, Toast.LENGTH_LONG).show();
            setResult(201, it1); // set result Code với màn hình create
            finish();
        });
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
    void Init()
    {
        buttonAdd=(Button) findViewById(R.id.buttonAdd);
        buttonBack=(Button) findViewById(R.id.buttonBack);
        editTextBaiHat=(EditText)findViewById(R.id.editTextBaiHat);
        editTextThoiGian=(EditText)findViewById(R.id.editTextThoiGian);
        editTextTacGia=(EditText)findViewById(R.id.editTextTacGia);
    }
}