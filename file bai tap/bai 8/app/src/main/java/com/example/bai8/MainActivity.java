package com.example.bai8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<Hinhanh> arrayListImage;
    HinhanhAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        adapter = new HinhanhAdapter(MainActivity.this,
                R.layout.layouthinhanh, arrayListImage);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this,
                        arrayListImage.get(i).getTen(),Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void InitWidgets() {
        gridView = (GridView) findViewById(R.id.gridView);
        arrayListImage = new ArrayList<>();
        arrayListImage.add(new Hinhanh("Hinh so 1", R.drawable.hinh1));
        arrayListImage.add(new Hinhanh("Hinh so 2", R.drawable.hinh2));
        arrayListImage.add(new Hinhanh("Hinh so 3", R.drawable.hinh3));
    }
}