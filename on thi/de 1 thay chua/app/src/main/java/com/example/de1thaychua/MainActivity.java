package com.example.de1thaychua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Database dataBase = new Database(this);
    private ListView lsvSong;
    private ArrayList<Baihat> arrayListBaiHat;
    private BaiHatAdapter adapterBaihat;

    private Button btnAdd;
    private EditText edTimkiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map();
        //dataBase.xoaToanBo();
        add(); // add dữ liệu vào sqlite
        arrayListBaiHat= (ArrayList<Baihat>) dataBase.getAll();
        Collections.sort(arrayListBaiHat);
        adapterBaihat = new BaiHatAdapter(MainActivity.this, R.layout.item_song, arrayListBaiHat);
        lsvSong.setAdapter(adapterBaihat);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(MainActivity.this, AddActivity.class);
                startActivity(it);
            }
        });

    }
    private void add(){
        dataBase.addSong(new Baihat("Phút cuối", "Bằng Kiều", (float) 6.27));
        dataBase.addSong(new Baihat("Bà tôi", "Tùng Dương", (float) 4.23));
        dataBase.addSong(new Baihat("Tình cha", "Ngọc Sơn", (float) 2.13));
        dataBase.addSong(new Baihat("Tình mẹ", "Ngọc Sơn", (float) 5.73));
        dataBase.addSong(new Baihat("Giả vờ yêu", "Ngô Kiến Huy", (float) 3.23));

    }

    private void Map() {
        edTimkiem = (EditText)findViewById(R.id.edTimKiem);
        arrayListBaiHat = new ArrayList<>();
        lsvSong = (ListView) findViewById(R.id.lsvBaiHat);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        //dataBase.xoaToanBo();
    }
}