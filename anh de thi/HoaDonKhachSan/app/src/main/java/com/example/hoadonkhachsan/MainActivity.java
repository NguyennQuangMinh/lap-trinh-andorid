package com.example.hoadonkhachsan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HoaDonDB hoaDonDB;
    List<HoaDon> dsKhachHang;
    HoaDonAdapter hoaDonAdapter;
    public static ArrayList<HoaDon> arrHD = new ArrayList<>();

    FloatingActionButton btnAdd;

    EditText edtTimKiem;
    ListView lvHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoaDonDB = new HoaDonDB(this, "db_hoadon", null, 2);
        khoiTaoDuLieuDB();
        anhXa();


    }
    // ánh xạ và sắp xếp
    private void anhXa() {
        btnAdd = findViewById(R.id.btnAdd);
        edtTimKiem = findViewById(R.id.edtTimKiem);

        lvHoaDon = findViewById(R.id.lvHoaDon);

        dsKhachHang = hoaDonDB.danhSachKhachHang();

        // sap xep theo tong tien
        Collections.sort(dsKhachHang, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon o1, HoaDon o2) {

                double tongTienPhong1 = o1.getDonGia();
                double tongTienPhong2 = o2.getDonGia();


                return Double.compare(tongTienPhong2, tongTienPhong1);
            }
        });



        hoaDonAdapter = new HoaDonAdapter(dsKhachHang, this);

        lvHoaDon.setAdapter(hoaDonAdapter);
        //context menu
        registerForContextMenu(lvHoaDon);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.lvHoaDon) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_xoa) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final int position = info.position;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa");

            // Lấy thông tin hóa đơn được chọn
            final HoaDon selectedHoaDon = dsKhachHang.get(position);

            // Đếm số lượng hóa đơn lớn hơn hóa đơn được chọn
            int countHoaDonLarger = 0;
            for (HoaDon hd : dsKhachHang) {
                if (hd.getDonGia()*hd.getSoNgayLuuTru() > selectedHoaDon.getDonGia()*selectedHoaDon.getSoNgayLuuTru()) {
                    countHoaDonLarger++;
                }
            }

            // Hiển thị số tiền và số lượng hóa đơn lớn hơn trong thông điệp
            builder.setMessage("Bạn có chắc chắn muốn xóa " + countHoaDonLarger + " hóa đơn có tổng tiền lớn hơn " + selectedHoaDon.getDonGia()*selectedHoaDon.getSoNgayLuuTru());

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    double selectedTongTien = selectedHoaDon.getDonGia()*selectedHoaDon.getSoNgayLuuTru();

                    // Xóa các hóa đơn có tổng tiền lớn hơn hóa đơn được chọn
                    List<HoaDon> hoadonToRemove = new ArrayList<>();
                    for (HoaDon hd : dsKhachHang) {
                        if (hd.getDonGia()*hd.getSoNgayLuuTru() > selectedTongTien) {
                            hoaDonDB.xoaKhachHang(hd.getSoPhong() );
                            hoadonToRemove.add(hd);
                        }
                    }

                    // Xóa khỏi danh sách và cập nhật adapter
                    dsKhachHang.removeAll(hoadonToRemove);
                    hoaDonAdapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, "Đã xóa "  + " hóa đơn", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();

            return true;
        }

        return super.onContextItemSelected(item);
    }


    private void khoiTaoDuLieuDB () {
            hoaDonDB.themKhachHang(new HoaDon("405", "Nguyen Van A", 500000f, 6f));
            hoaDonDB.themKhachHang(new HoaDon("106", "Nguyen Van B", 500000f, 3f));
            hoaDonDB.themKhachHang(new HoaDon("501", "Nguyen Van C", 300000f, 2f));
            hoaDonDB.themKhachHang(new HoaDon("401", "Nguyen Van D", 250000f, 10f));
            hoaDonDB.themKhachHang(new HoaDon("301", "Nguyen Van E", 700000f, 1f));
        }
    }
