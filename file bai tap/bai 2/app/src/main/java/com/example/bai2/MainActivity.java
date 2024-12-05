package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    EditText editTextNhapa,editTextNhapb,editTextPheptoan;
    Button buttonCalculate;
    TextView textViewKq;
    ImageView imageViewHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(editTextNhapa.getText().toString());
                int b = Integer.parseInt(editTextNhapb.getText().toString());
                double c =0;
                String s = editTextPheptoan.getText().toString();
                if (s.equals("+")){
                    c=a+b;
                }
                if (s.equals("-")){
                    c=a-b;
                }
                if (s.equals("*")){
                    c=a*b;
                }
                if (s.equals("/")){
                    c=a/b;
                }
                textViewKq.setText(String.valueOf(c));
                s = String.valueOf(c);
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void InitWidgets() {
        editTextNhapa=(EditText)findViewById(R.id.editTextNhapa);
        editTextNhapb=(EditText)findViewById(R.id.editTextNhapb);
        editTextPheptoan=(EditText)findViewById(R.id.editTextPheptoan);
        textViewKq=(TextView)findViewById(R.id.textViewKq);
        buttonCalculate=(Button)findViewById(R.id.buttonCalculate);
        imageViewHigh = (ImageView)findViewById(R.id.imageViewHigh);
    }
}