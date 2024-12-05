package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editTextSoA, editTextSoB;
    Button buttonGui;
    TextView textViewKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        buttonGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyintent();
            }

            private void xulyintent() {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("a", Integer.parseInt(editTextSoA.getText().toString()));
                intent.putExtra("b", Integer.parseInt(editTextSoB.getText().toString()));
                mActivityResultLauncher.launch(intent);
            }

        });
    }

    private  ActivityResultLauncher<Intent> mActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode()==33){
                                Intent intent = result.getData();
                                int t = intent.getIntExtra("tong",-1);
                                textViewKQ.setText("Tong = " + t);
                            }
                        }
                    });
    private void InitWidget(){
            editTextSoA = (EditText) findViewById(R.id.editTextSoA);
            editTextSoB = (EditText) findViewById((R.id.editTextSoB));
            buttonGui = (Button) findViewById(R.id.buttonGui);
            textViewKQ = (TextView) findViewById(R.id.textViewKQ);
        }
    }
