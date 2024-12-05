package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    EditText editTextHoten;
    Button buttonOk;
    TextView textViewDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String s = editTextHoten.getText().toString();
                //s = "xin chao " + s;
                //textViewDisplay.setText(s);
                textViewDisplay.setText("xin chao " + editTextHoten.getText().toString());
            }
        });
    }
    private void InitWidgets() {
        editTextHoten = (EditText) findViewById(R.id.editTextHoten);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        textViewDisplay = (TextView) findViewById(R.id.textViewDisplay);
    }
}