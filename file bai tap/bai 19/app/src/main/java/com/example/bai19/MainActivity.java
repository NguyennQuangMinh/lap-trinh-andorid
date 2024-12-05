package com.example.bai19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ptName;
    CheckBox cbBongda , cbBongro , cbBongchuyen;
    RadioButton rdoNam , rdoNu;
    Button btnOk;
    TextView txtUser;
    int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s = ptName.getText().toString();
                ptName.setText("");
                if(rdoNam.isChecked()){
                    s=s+ "," + rdoNam.getText().toString();
                }
                if(rdoNu.isChecked()){
                    s=s+ "," + rdoNu.getText().toString();
                }
            }
        });
    }
    private void InitWidgets() {
        ptName = (EditText)findViewById(R.id.ptName);
        cbBongda=(CheckBox) findViewById(R.id.cbBongda);
        cbBongro=(CheckBox) findViewById(R.id.cbBongro);
        cbBongchuyen=(CheckBox) findViewById(R.id.cbBongchuyen);
        rdoNam=(RadioButton) findViewById(R.id.rdoNam);
        rdoNu=(RadioButton) findViewById(R.id.rdoNu);
        btnOk=(Button) findViewById(R.id.btnOk);
        txtUser=(TextView) findViewById(R.id.txtUser);
    }
}