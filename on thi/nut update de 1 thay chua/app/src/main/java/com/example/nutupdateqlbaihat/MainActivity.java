package com.example.nutupdateqlbaihat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtMa, edtTen, edtCaSy, edtTL;
    ListView lsvBH;
    public static ArrayList<BaiHat> arrBH = new ArrayList<>();
    BaiHatAdapter baiHatAdapter;
    DBHelper dbHelper = new DBHelper(MainActivity.this, "", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map();

        // gán dữ liệu cho arrBH

        dbHelper.insert(new BaiHat("Kiep do den", "Duy Manh", (float)4.75));
        dbHelper.insert(new BaiHat("Đêm lao xao", "Phương Thanh", (float)5.52));
        dbHelper.insert(new BaiHat("Yêu xa", "Vũ Cát Tường", (float)5.52));

        arrBH = (ArrayList<BaiHat>) dbHelper.getAll();
        // tạo adapter
        baiHatAdapter = new BaiHatAdapter(MainActivity.this, 1, arrBH);
        lsvBH.setAdapter(baiHatAdapter);
        lsvBH.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // chuyển màn hình
                Intent it = new Intent(MainActivity.this, UpdateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_MABH", arrBH.get(position).getMaBH());
                bundle.putString("KEY_TENBH", arrBH.get(position).getTenBH());
                bundle.putString("KEY_TENCS", arrBH.get(position).getTenCS());
                bundle.putFloat("KEY_TL", arrBH.get(position).getThoiLuong());
                it.putExtras(bundle);
                startActivity(it);
                return false;
            }
        });
    }
    private void map(){
        edtMa = findViewById(R.id.edtMaBH);
        edtTen = findViewById(R.id.edtTenBH);
        edtCaSy = findViewById(R.id.edtTenCS);
        edtTL = findViewById(R.id.edtThoiLuong);
        lsvBH = findViewById(R.id.lsvBaiHat);
    }
}
