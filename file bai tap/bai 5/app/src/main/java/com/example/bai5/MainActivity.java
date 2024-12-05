package com.example.bai5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView Listview1;
    Spinner spinnerMonhoc;
    EditText editTextMonhoc;
    TextView textViewSelect;
    Button buttonAdd, buttonDelete , buttonUpdate ;
    int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        ArrayList<String> arrayListMonhoc = new ArrayList<String>();
        arrayListMonhoc.add("Android");
        arrayListMonhoc.add("Kien truc may tinh");
        arrayListMonhoc.add("Java");
        arrayListMonhoc.add("Ky thuat vi xu ly");
        arrayListMonhoc.add("Mang may tinh");
        arrayListMonhoc.add("Lap trinh huong doi tuong");
        arrayListMonhoc.add("Lap trinh web");
        arrayListMonhoc.add("xu ly anh");

        // b2 tao adapter
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, arrayListMonhoc);

        //b3 hien thi len listView
        Listview1.setAdapter(adapter);
        Listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                editTextMonhoc.setText(arrayListMonhoc.get(i));
                textViewSelect.setText(arrayListMonhoc.get(i));
                j = i;
                Toast.makeText(MainActivity.this, arrayListMonhoc.get(i),Toast.LENGTH_SHORT);
            }

        });
        Listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                j = position;
                AlertDialog.Builder alerDialog = new AlertDialog.Builder(MainActivity.this);
                alerDialog.setTitle("Thong bao");
                alerDialog.setIcon(R.mipmap.ic_launcher);
                alerDialog.setMessage("Are you sure");
                alerDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        arrayListMonhoc.remove(j);
                        adapter.notifyDataSetChanged();
                    }
                });
                alerDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }

                });
                alerDialog.show();
                return false;
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s = editTextMonhoc.getText().toString();
                arrayListMonhoc.add(s);
                adapter.notifyDataSetChanged();
                editTextMonhoc.setText("");
                editTextMonhoc.requestFocus();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerDialog = new AlertDialog.Builder(MainActivity.this);
                alerDialog.setTitle("Thong bao");
                alerDialog.setIcon(R.mipmap.ic_launcher);
                alerDialog.setMessage("Are you sure");
                alerDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    arrayListMonhoc.remove(j);
                    adapter.notifyDataSetChanged();
                    }
                });
                alerDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }

                });
                alerDialog.show();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editTextMonhoc.getText().toString();
                arrayListMonhoc.remove(j);
                arrayListMonhoc.add(j,s);
                adapter.notifyDataSetChanged();
            }
        });

        // Khai bao adapter cho spinner
        ArrayAdapter adapter1 = new ArrayAdapter( MainActivity.this,
                android.R.layout.simple_spinner_item,arrayListMonhoc);
        spinnerMonhoc.setAdapter(adapter1);

        // cho hai hang cach nhau cho dep
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonhoc.setAdapter(adapter1);

        spinnerMonhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                editTextMonhoc.setText(arrayListMonhoc.get(i));
                textViewSelect.setText(arrayListMonhoc.get(i));
                j = i;
                Toast.makeText(MainActivity.this, arrayListMonhoc.get(i),Toast.LENGTH_SHORT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void InitWidgets() {
        Listview1 = (ListView) findViewById(R.id.ListView1);
        textViewSelect=(TextView)findViewById(R.id.textViewSelect);
        editTextMonhoc=(EditText) findViewById(R.id.editTexMonhoc);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        spinnerMonhoc = (Spinner) findViewById(R.id.spinnerMonhoc);
    }
}