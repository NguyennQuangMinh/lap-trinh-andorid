package com.example.bai18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextHoten, editTextNamsinh;
    Button button1;
    Spinner spinner;
    RadioButton radioButton1, radioButton2;
    CheckBox checkBox1, checkBox2;
    int position;
    String gt, pt, qq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> arrayListQuequan = new ArrayList<String>();
        arrayListQuequan.add("Hanoi");
        arrayListQuequan.add("TP.Hochiminh");
        arrayListQuequan.add("Namdinh");
        arrayListQuequan.add("Bacninh");
        InitWidgets();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, arrayListQuequan);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                qq = arrayListQuequan.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                String ht = editTextHoten.getText().toString();
                String ns = editTextNamsinh.getText().toString();
                if(radioButton1.isChecked()){
                    gt = radioButton1.getText().toString();
                }
                if(radioButton2.isChecked()){
                    gt = radioButton2.getText().toString();
                }
                if(checkBox1.isChecked()){
                    pt = checkBox1.getText().toString();
                }
                if(checkBox2.isChecked()){
                    pt = checkBox2.getText().toString();
                }
                Bundle bundle = new Bundle();
                bundle.putString("Hoten",ht);
                bundle.putString("Namsinh",ns);
                bundle.putString("Quequan",qq);
                bundle.putString("Gioitinh",gt);
                bundle.putString("Phuongtien",pt);
                intent.putExtra("Mypacket",bundle);
                startActivity(intent);
            }
        });
    }

    private void InitWidgets() {
        editTextHoten=(EditText) findViewById(R.id.editTextHoten);
        editTextNamsinh=(EditText) findViewById(R.id.editTextNamsinh);
        button1=(Button) findViewById(R.id.button1);
        spinner=(Spinner) findViewById(R.id.spinner);
        radioButton1=(RadioButton) findViewById(R.id.radioButton1);
        radioButton2=(RadioButton) findViewById(R.id.radioButton2);
        checkBox1=(CheckBox) findViewById(R.id.checkBox1);
        checkBox2=(CheckBox) findViewById(R.id.checkBox2);
    }
}