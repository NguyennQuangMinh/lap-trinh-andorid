package com.example.sanpham;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    SanPhamDB sanPhamDB;
    List<SanPham> dsSanPham;
    SanPhamAdapter sanPhamAdapter;
    public static ArrayList<SanPham> arrSP = new ArrayList<>();

    FloatingActionButton btnAdd;
    EditText edtTimKiem;
    ListView lvSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sanPhamDB = new SanPhamDB(this, "db_sanpham", null, 2);
        khoiTaoDuLieuDB();
        anhXa();
        //themSuKien();

        // tim kiem gia thap hon gia hien tren edtTimKiem
//        edtTimKiem.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // Not used
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // Not used
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // Filter invoices based on the entered amount
//                filterVeTauByAmount(editable.toString());
//            }
//        });
    }

//    private void themSuKien() {
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it1 = new Intent(MainActivity.this, ThemActivity.class);
//                startActivity(it1);
//            }
//        });
//
//    }

    private void anhXa() {
        btnAdd = findViewById(R.id.btnAdd);
        edtTimKiem = findViewById(R.id.edtTimKiem);

        lvSanPham = findViewById(R.id.lvSanPham);

        dsSanPham = sanPhamDB.danhSachSanPham();

        //sap xep theo ten  SanPham
        Collections.sort(dsSanPham, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham o1, SanPham o2) {
                // sắp xếp theo tên sản phẩm
                return o2.getTenSP().compareTo(o1.getTenSP());
            }
        });


        sanPhamAdapter = new SanPhamAdapter(dsSanPham, this);

        lvSanPham.setAdapter(sanPhamAdapter);
        //context menu
        registerForContextMenu(lvSanPham);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.lvSanPham) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // xóa
        if (item.getItemId() == R.id.item_xoa) { // xoa 1 phan tu trong danh sach
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final int position = info.position;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");

            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SanPham sp = dsSanPham.get(position);
                    String tenSP = sp.getTenSP();
                    sanPhamDB.xoaSanPham(tenSP);
                    dsSanPham.remove(position);

                    sanPhamAdapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, "Đã xóa vé này", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();

            return true;
        }
        // Sửa
        else if (item.getItemId() == R.id.item_sua) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            Intent it = new Intent(MainActivity.this, UpdateActivity.class);

            Bundle bundle = new Bundle();
            SanPham selectedSanPham = dsSanPham.get(position);
            bundle.putString("KEY_TENSP", selectedSanPham.getTenSP());
            bundle.putDouble("KEY_GIATIEN", selectedSanPham.getGiaTien());
            bundle.putString("KEY_SW", selectedSanPham.getSW());


            it.putExtras(bundle);
            startActivity(it);
            return false;
        }

//        // sắp xếp theo lệnh trong context_menu
//        else if (item.getItemId() == R.id.item_sapxep) {
//            // Sort the list based on txtGaDi field in ascending order
//            Collections.sort(dsVeTau, new Comparator<VeTau>() {
//                @Override
//                public int compare(VeTau o1, VeTau o2) {
//                    return o1.getGaDi().compareToIgnoreCase(o2.getGaDi());
//                }
//            });
//
//            // Notify the adapter about the changes in the dataset
//            veTauAdapter.notifyDataSetChanged();
//
//            // Display a toast message indicating the sorting action
//            Toast.makeText(MainActivity.this, "Đã sắp xếp theo thứ tự tăng dần của Ga Đi", Toast.LENGTH_SHORT).show();
//
//            return true;
//        }


        return super.onContextItemSelected(item);
    }


    private void khoiTaoDuLieuDB() {
        sanPhamDB.themSanPham(new SanPham("Điện thoại Iphone 6", 6700000.0, " "));
        sanPhamDB.themSanPham(new SanPham("Điện thoại SamSung S7", 8300000.0, " "));
        sanPhamDB.themSanPham(new SanPham("Tủ lạnh LG", 14500000.0, "Giảm Giá Còn:"));
        sanPhamDB.themSanPham(new SanPham("Tivi SamSung 14", 8900000.0, "Giảm Giá Còn:"));
        sanPhamDB.themSanPham(new SanPham("Lò vi sóng SunHouse", 1200000.0, "Giảm Giá Còn:"));
    }
//    // ham xu li tim kiem
//    private void filterVeTauByAmount(String amountString) {
//        if (amountString.isEmpty()) {
//            // If the search field is empty, show all invoices
//            veTauAdapter.filterList(dsVeTau);
//        } else {
//            // Filter invoices by amount
//            float searchAmount = Float.parseFloat(amountString);
//            List<VeTau> filteredList = new ArrayList<>();
//
//            for (VeTau veTau : dsVeTau) {
//                if (((veTau.getDonGia()* 2*0.95)  < searchAmount || veTau.getDonGia()<searchAmount)) {
//                    filteredList.add(veTau);
//                }
//            }
//
//            veTauAdapter.filterList(filteredList);
//        }
//    }




}