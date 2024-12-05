package com.example.bai20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonThem , buttonXoa ;
    EditText editTextThietbi;
    ListView Listview1;
    int j ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        ArrayList<String> arrayListThietbi = new ArrayList<String>();
        arrayListThietbi.add("Tivi");
        arrayListThietbi.add("Tủ lạnh");
        arrayListThietbi.add("Điều hòa");
        arrayListThietbi.add("Máy giặt");


        // b2 tao adapter
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, arrayListThietbi);

        //b3 hien thi len listView
        Listview1.setAdapter(adapter);
        Listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                editTextThietbi.setText(arrayListThietbi.get(i));
                j = i;
                Toast.makeText(MainActivity.this, arrayListThietbi.get(i),Toast.LENGTH_SHORT);
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
                        arrayListThietbi.remove(j);
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
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, MainActivity2.class);
                //2.truyen du lieu sang subActivity bang bundle neu can

                //3. Mo subAc bang cach goi ham
                startActivityForResult(intent, 100);
            }
        });
        buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerDialog = new AlertDialog.Builder(MainActivity.this);
                alerDialog.setTitle("Thong bao");
                alerDialog.setIcon(R.mipmap.ic_launcher);
                alerDialog.setMessage("Are you sure");
                alerDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        arrayListThietbi.remove(j);
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
    }
    private void InitWidgets() {
        editTextThietbi=(EditText) findViewById(R.id.editTextThietbi);
        buttonThem=(Button) findViewById(R.id.buttonThem);
        buttonXoa=(Button) findViewById(R.id.buttonXoa);
        Listview1 = (ListView) findViewById(R.id.Listview1);
    }

}