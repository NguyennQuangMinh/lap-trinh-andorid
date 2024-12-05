package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button buttonTinh;
    TextView textViewReceive;
    Intent intent;
    int a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        InitWidget1();
        buttonTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = a+b;
                intent.putExtra("tong",c);
                //danh dau ket qua tra ve
                setResult(33,intent);
                finish();
            }
        });
    }
    private void InitWidget1(){
        textViewReceive=(TextView) findViewById(R.id.textViewReceive);
        buttonTinh=(Button)  findViewById(R.id.buttonTinh);
        intent = getIntent();
        a=intent.getIntExtra("a",-1);
        b=intent.getIntExtra("b",-1);
        textViewReceive.setText("a=" + a + "b="+b);
    }
}