package com.example.bai11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textView1;
    EditText editTextMonhoc;
    Button buttonAdd, buttonDelete, buttonEdit;
    Spinner spinnerMonhoc;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        //B1 tao nguon du lieu
        ArrayList<String> arrayListMonhoc = new ArrayList<String>();
        arrayListMonhoc.add("Android");
        arrayListMonhoc.add("Kien truc may tinh");
        arrayListMonhoc.add("Java");
        arrayListMonhoc.add("Ky thuat VXL");
        arrayListMonhoc.add("Mang may tinh");
        arrayListMonhoc.add("Lap trinh huong doi tuong");

        // B2 tao adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, arrayListMonhoc);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                editTextMonhoc.setText(arrayListMonhoc.get(i));
                textView1.setText(arrayListMonhoc.get(i));
                Toast.makeText(MainActivity.this, arrayListMonhoc.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(MainActivity.this);
                alertDiaLog.setTitle("Thong bao");
                alertDiaLog.setIcon(R.mipmap.ic_launcher);
                alertDiaLog.setMessage("Are you sure");
                alertDiaLog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        position=i;
                        arrayListMonhoc.remove(position);
                        textView1.setText("");
                        editTextMonhoc.setText("");
                        adapter.notifyDataSetChanged();
                    }
                });
                alertDiaLog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDiaLog.show();
                return false;
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s;
                s = editTextMonhoc.getText().toString();
                arrayListMonhoc.add(s);
                adapter.notifyDataSetChanged();
                textView1.setText("");
                editTextMonhoc.setText("");
                editTextMonhoc.requestFocus();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(MainActivity.this);
                alertDiaLog.setTitle("Thong bao");
                alertDiaLog.setIcon(R.mipmap.ic_launcher);
                alertDiaLog.setMessage("Are you sure");
                alertDiaLog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        position=i;
                        arrayListMonhoc.remove(position);
                        textView1.setText("");
                        editTextMonhoc.setText("");
                        adapter.notifyDataSetChanged();
                    }
                });
                alertDiaLog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDiaLog.show();
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayListMonhoc.set(position, editTextMonhoc.getText().toString());
                editTextMonhoc.setText("");
                textView1.setText("");
                adapter.notifyDataSetChanged();
            }
        });
        ArrayAdapter adapter1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, arrayListMonhoc);
        //Cho chu cach ra
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonhoc.setAdapter(adapter1);
        spinnerMonhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                editTextMonhoc.setText(arrayListMonhoc.get(i));
                textView1.setText(arrayListMonhoc.get(i));
                Toast.makeText(MainActivity.this, arrayListMonhoc.get(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void InitWidgets() {
        spinnerMonhoc = (Spinner) findViewById(R.id.spinnerMonhoc);
        listView = (ListView) findViewById(R.id.listView);
        textView1 = (TextView) findViewById(R.id.textView1);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        editTextMonhoc = (EditText) findViewById(R.id.editTextMonhoc);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonEdit = (Button) findViewById(R.id.buttonEdit);
    }
}