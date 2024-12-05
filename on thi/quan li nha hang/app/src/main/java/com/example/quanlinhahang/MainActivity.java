package com.example.quanlinhahang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBNhaHang dbNhaHang;
    List<NhaHang> dsNhaHang;
    NhaHangAdapter nhaHangAdapter;

    FloatingActionButton floatThem;
    EditText txtTimKiem;
    ListView lvNhaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        khoiTaoDuLieuDB();
        anhXa();
        themSuKien();
    }

    private void themSuKien() {
        floatThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickThem();
            }
        });

        lvNhaHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Xóa");
                builder.setMessage("Ban co muon xoa " + dsNhaHang.get(position).getTenNhaHang() + "?");

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (dbNhaHang.xoaNhaHang(dsNhaHang.get(position).getMaNhaHang())) {
                            dsNhaHang.remove(position);
                            nhaHangAdapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();

                return false;
            }
        });
    }

    private void clickThem() {
        Intent intent = new Intent(this, ThemActivity.class);
        startActivity(intent);
        finish();
    }

    private void anhXa() {
        floatThem = findViewById(R.id.floatThem);
        txtTimKiem = findViewById(R.id.txtTimKiem);

        lvNhaHang = findViewById(R.id.lvNhaHang);

        dsNhaHang = dbNhaHang.layDanhSach();
        nhaHangAdapter = new NhaHangAdapter(dsNhaHang, this);

        lvNhaHang.setAdapter(nhaHangAdapter);

    }

    private void khoiTaoDuLieuDB() {
        dbNhaHang = new DBNhaHang(this, "db_nhahang", null, 1);
        dbNhaHang.themNhaHang(new NhaHang("1", "Lau ca", "1 Tran Duy Hung", "9.9"));
        dbNhaHang.themNhaHang(new NhaHang("2", "Lau ga", "2 Tran Duy Hung", "9.9"));
        dbNhaHang.themNhaHang(new NhaHang("3", "Lau bo", "3 Tran Duy Hung", "9.9"));
        dbNhaHang.themNhaHang(new NhaHang("4", "Lau de", "4 Tran Duy Hung", "9.9"));
        dbNhaHang.themNhaHang(new NhaHang("5", "Lau vit", "5 Tran Duy Hung", "9.9"));
        dbNhaHang.themNhaHang(new NhaHang("6", "Lau ngan", "6 Tran Duy Hung", "9.9"));
    }
}