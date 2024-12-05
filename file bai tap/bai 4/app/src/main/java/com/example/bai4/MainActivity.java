package com.example.bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText editTextHoten, editTextNamsinh , editTextQuequan ;
    Button buttonGui ;
    RadioButton radioButtonNam, radioButtonNu, radioButtonDaihoc, radioButtonTrendaihoc;
    TextView textViewBMI, textViewTinhtrang,textViewKq;
    CheckBox CheckBoxIT, CheckBoxDientu,CheckBoxKinhte ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        buttonGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s ="";
                s=s+ editTextHoten.getText().toString();
                s=s+ editTextNamsinh.getText().toString();
                s=s+ editTextQuequan.getText().toString();
                if(radioButtonNam.isChecked()){
                    s=s+ "" + radioButtonNam.getText().toString();
                }
                if(radioButtonNu.isChecked()){
                    s=s+ "" + radioButtonNu.getText().toString();
                }
                if(radioButtonDaihoc.isChecked()){
                    s=s+ "" + radioButtonDaihoc.getText().toString();
                }
                if(radioButtonTrendaihoc.isChecked()){
                    s=s+ "" + radioButtonTrendaihoc.getText().toString();
                }
                if(CheckBoxIT.isChecked()){
                    s=s + "" + CheckBoxIT.getText().toString();
                }
                if(CheckBoxDientu.isChecked()){
                    s=s + "" + CheckBoxDientu.getText().toString();
                }
                if(CheckBoxKinhte.isChecked()){
                    s=s + "" + CheckBoxKinhte.getText().toString();
                }
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void InitWidgets() {
        editTextHoten=(EditText) findViewById(R.id.editTextHoten);
        editTextNamsinh=(EditText) findViewById(R.id.editTextNamsinh);
        editTextQuequan=(EditText) findViewById(R.id.editTextQuequan);
        radioButtonNam=(RadioButton) findViewById(R.id.radioButtonNam);
        radioButtonNu=(RadioButton) findViewById(R.id.radioButtonNu);
        radioButtonDaihoc=(RadioButton) findViewById(R.id.radioButtonDaihoc);
        radioButtonTrendaihoc=(RadioButton) findViewById(R.id.radioButtonTrendaihoc);
        CheckBoxIT=(CheckBox) findViewById(R.id.checkBoxIT);
        CheckBoxDientu=(CheckBox) findViewById(R.id.checkBoxDientu);
        CheckBoxKinhte=(CheckBox) findViewById(R.id.checkBoxKinhte);
        buttonGui = (Button) findViewById(R.id.buttonGui);
    }
}