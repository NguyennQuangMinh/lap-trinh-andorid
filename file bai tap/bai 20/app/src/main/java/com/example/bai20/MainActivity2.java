package com.example.bai20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity2 extends AppCompatActivity {

    private Button btnConfirm, btnCancel;
    private EditText editTextID, editTextName, editTextMota;

    boolean check = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        editTextName = findViewById(R.id.editTextName);




        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                //boolean staus = SwStatus.setChecked();

                Intent intent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putString("Name", name);
                intent.putExtras(bundle);
                setResult(150, intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}