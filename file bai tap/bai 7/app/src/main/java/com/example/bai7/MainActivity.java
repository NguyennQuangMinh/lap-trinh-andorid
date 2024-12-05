package com.example.bai7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextHoten , editTextNamsinh, editTextDiemtoan, editTextDiemly,editTextDiemhoa;
    RadioButton radioButtonNam , radioButtonNu;
    Spinner spinnerQuequan;
    Button buttonOk;
    TextView textViewTong , textViewInkq ;
    String Tong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        ArrayList<String> arrayListQuequan = new ArrayList<String>();
        arrayListQuequan.add("Hà Nội");
        arrayListQuequan.add("Hà Nam");
        arrayListQuequan.add("Nam Định");
        arrayListQuequan.add("Hòa Bình");
        ArrayAdapter adapter1 = new ArrayAdapter( MainActivity.this,
                android.R.layout.simple_spinner_item,arrayListQuequan);
        spinnerQuequan.setAdapter(adapter1);


    buttonOk.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s ="";
            s=s+ editTextHoten.getText().toString();
            s=s+"," + editTextNamsinh.getText().toString();
            s=s+ spinnerQuequan.getSelectedItem().toString();
            if(radioButtonNam.isChecked()){
                s=s+ "," + radioButtonNam.getText().toString();
            }
            if(radioButtonNu.isChecked()){
                s=s+ "," + radioButtonNu.getText().toString();
            }
            int a = Integer.parseInt(editTextDiemtoan.getText().toString());
            int b = Integer.parseInt(editTextDiemly.getText().toString());
            int c = Integer.parseInt(editTextDiemhoa.getText().toString());
            double d=0;
            d = a + b + c;
            textViewTong.setText(String.valueOf(d));
            s= s + ", Tong : " + String.valueOf(d);
            if(d>=15){
                Tong = "Dat";
            }
            else{
                Tong = "Tach";
            }
            s = s + " , " + Tong;
            textViewInkq.setText(s);
        }

    });
    }
    private void InitWidgets(){
        editTextHoten=(EditText) findViewById(R.id.editTextHoten);
        editTextNamsinh=(EditText) findViewById(R.id.editTextNamsinh);
        editTextDiemtoan=(EditText) findViewById(R.id.editTextDiemtoan);
        editTextDiemly=(EditText) findViewById(R.id.editTextDiemly);
        editTextDiemhoa=(EditText) findViewById(R.id.editTextDiemhoa);
        radioButtonNam=(RadioButton) findViewById(R.id.radioButtonNam);
        radioButtonNu=(RadioButton) findViewById(R.id.radioButtonNu);
        spinnerQuequan=(Spinner) findViewById(R.id.spinnerQuequan);
        buttonOk=(Button) findViewById(R.id.buttonOk);
        textViewTong=(TextView) findViewById(R.id.textViewTong);
        textViewInkq=(TextView) findViewById(R.id.textViewInkq);
    }
}