package com.example.bai9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button buttonBack;
    TextView textViewKq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        InitWidgets();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent1= new Intent();
        Bundle bundle = intent1.getBundleExtra("Cacmonhoc");
        String mh1 = bundle.getString("Monhoc");
        textViewKq.setText(mh1);
    }
    private void InitWidgets() {
        buttonBack = (Button) findViewById(R.id.buttonBack);
        textViewKq = (TextView) findViewById(R.id.textViewKq);
    }
}