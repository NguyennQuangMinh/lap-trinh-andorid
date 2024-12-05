package com.example.quanlinhahang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThemActivity extends AppCompatActivity {

    EditText edtMaNhaHang, edtTenNhaHang, edtDiaChi, edtDanhGia;
    Button btnThem, btnHuy;

    DBNhaHang dbNhaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        dbNhaHang = new DBNhaHang(this, "db_nhahang", null, 1);

        anhXa();
        themSuKien();
    }

    private void themSuKien() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickThem();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHuy();
            }
        });
    }

    private void clickThem() {
        String maNH = edtMaNhaHang.getText().toString() + "";
        String tenNH = edtTenNhaHang.getText().toString() + "";
        String diaChi = edtDiaChi.getText().toString() + "";
        String danhGia = edtDanhGia.getText().toString() + "";

        NhaHang nhaHang = new NhaHang(maNH, tenNH, diaChi, danhGia);

        if (dbNhaHang.themNhaHang(nhaHang)) {
            Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
            clickHuy();
            return;
        }

        Toast.makeText(this, "Ma nha hang da ton tai", Toast.LENGTH_SHORT).show();
    }

    private void clickHuy() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void anhXa() {
        edtMaNhaHang = findViewById(R.id.edtMaNhaHang);
        edtTenNhaHang = findViewById(R.id.edtTenNhaHang);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtDanhGia = findViewById(R.id.edtDanhGia);
        btnThem = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);
    }
}