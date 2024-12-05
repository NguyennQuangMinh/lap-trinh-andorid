package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView2;
    Button buttonTinh;
    Intent intent;
    double tong, toan, ly, hoa, dc;
    String ten, dat, khongdat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        InitWidgets1();
        buttonTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tong = (toan + ly + hoa) + dc;
                intent.putExtra("tong",tong);
                setResult(33, intent);
                finish();
                // danh dau ket qua tra ve
            }
        });
        }



    private void InitWidgets1() {
        textView2 = (TextView) findViewById(R.id.textView2);
        buttonTinh = (Button) findViewById(R.id.buttonTinh);
        intent = getIntent();
        ten = intent.getStringExtra("Ten");
        toan = intent.getDoubleExtra("Toan", -1);
        ly = intent.getDoubleExtra("Ly", -1);
        hoa = intent.getDoubleExtra("Hoa", -1);
        dc = intent.getDoubleExtra("dc", -1);
        textView2.setText(ten +", Diem Toan: "+toan+", Diem Ly: "+ly+", Diem Hoa: "+hoa+", Diem cong: "+dc);
        //a = intent.getIntExtra("a",-1);
        //b = intent.getIntExtra("b", -1);
        //textView1.setText("a = " + a + ", b = " + b + ", del= "+del);
    }
}

