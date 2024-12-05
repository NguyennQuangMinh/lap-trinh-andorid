package com.example.bai10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridViewSinhVien;
    SinhVienAdapter adapter;
    TextView textViewHoten, textViewNamSinh;
    ArrayList<SinhVien> arrayListSinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        arrayListSinhVien.add(new SinhVien("Nguyễn Văn A", 1991));
        adapter = new SinhVienAdapter(MainActivity.this,
                R.layout.item, arrayListSinhVien);
        gridViewSinhVien.setAdapter(adapter);
        gridViewSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this,
                        arrayListSinhVien.get(i).getHoten(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void InitWidgets() {
        gridViewSinhVien = (GridView) findViewById(R.id.gridViewSinhVien);
        textViewHoten = (TextView) findViewById(R.id.textViewHoten);
        textViewNamSinh = (TextView) findViewById(R.id.textViewNamSinh);
    }
}