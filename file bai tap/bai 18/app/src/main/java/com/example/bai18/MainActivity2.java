package com.example.bai18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button buttonBack;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        InitWidgets();
        Intent intent1 = getIntent();
        Bundle bundle = getIntent().getBundleExtra("Mypacket");
        String a = bundle.getString("Hoten");
        String b = bundle.getString("Namsinh");
        String c = bundle.getString("Quequan");
        String d = bundle.getString("Gioitinh");
        String e = bundle.getString("Phuongtien");
        String s = a + ", " + b + ", " + c + ", " + d + ", " + e;
        textView1.setText(s);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitWidgets() {
        buttonBack = (Button) findViewById(R.id.buttonBack);
        textView1 = (TextView) findViewById(R.id.textView1);
    }
}
