package com.example.bai9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button buttonSend;
    EditText editTextMonhoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MainActivity2.class);
                String mh= editTextMonhoc.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("Monhoc",mh);
                intent.putExtra("Cacmonhoc",bundle);
                startActivity(intent);
            }
        });
    }
    private void InitWidgets() {
        buttonSend = (Button) findViewById(R.id.buttonSend);
        editTextMonhoc = (EditText)  findViewById(R.id.editTextMonhoc);
    }
}
