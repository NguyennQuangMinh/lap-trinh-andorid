package com.example.hoadontaxi;

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

        // them

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it1 =new Intent(MainActivity.this, ThemActivity.class);
                startActivity(it1);
                lvHoaDon.setAdapter(hoaDonAdapter);
            }
        });


        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Filter invoices based on the entered amount
                filterHoaDonByAmount(editable.toString());
            }
        });

        lvHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon selectedHoaDon = dsKhachHang.get(position);

                // Lọc danh sách HoaDon có tổng giá lớn hơn HoaDon được chọn
                List<HoaDon> filteredList = filterHoaDonByTotalPrice(selectedHoaDon, dsKhachHang);

                // Hiển thị AlertDialog với thông tin chi tiết và danh sách
                showHoaDonDetailsDialog(selectedHoaDon, filteredList);
                return false;
            }
        });


    }


    // hàm xử lí của edtTimKiem
    private void filterHoaDonByAmount(String amountString) {
        if (amountString.isEmpty()) {
            // If the search field is empty, show all invoices
            hoaDonAdapter.filterList(dsKhachHang);
        } else {
            // Filter invoices by amount
            float searchAmount = Float.parseFloat(amountString);
            List<HoaDon> filteredList = new ArrayList<>();

            for (HoaDon hoaDon : dsKhachHang) {
                if ((hoaDon.getDonGia() * hoaDon.getQuangDuong()*(100- hoaDon.getKhuyenMai())/100) < searchAmount) {
                    filteredList.add(hoaDon);
                }
            }

            hoaDonAdapter.filterList(filteredList);
        }
    }

    // hàm xử lí của itemLongOnclick
    private List<HoaDon> filterHoaDonByTotalPrice(HoaDon selectedHoaDon, List<HoaDon> hoaDonList) {
        List<HoaDon> filteredList = new ArrayList<>();

        // Lọc danh sách HoaDon có tổng giá lớn hơn HoaDon được chọn
        for (HoaDon hoaDon : hoaDonList) {
            if ((hoaDon.getDonGia() * hoaDon.getQuangDuong()*((100- hoaDon.getKhuyenMai())/100)) < (selectedHoaDon.getDonGia() * selectedHoaDon.getQuangDuong()*((100- selectedHoaDon.getKhuyenMai())/100))) {
                filteredList.add(hoaDon);
            }
        }

        return filteredList;
    }
    private void showHoaDonDetailsDialog(HoaDon selectedHoaDon, List<HoaDon> hoaDonList) {
        // Hiển thị thông tin chi tiết và danh sách HoaDon có tổng giá lớn hơn HoaDon được chọn trong AlertDialog

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin chi tiết");

        // Tạo chuỗi thông tin chi tiết cho HoaDon được chọn
        StringBuilder detailsBuilder = new StringBuilder();
        detailsBuilder.append("").append(selectedHoaDon.getSoXe())
                .append("\nQuãng Đường: ").append(selectedHoaDon.getQuangDuong())
                .append("\nĐơn giá: ").append(selectedHoaDon.getDonGia())
                .append("\nKhuyến Mãi(%): ").append(selectedHoaDon.getKhuyenMai())
                .append("\nTổng giá: ").append(selectedHoaDon.getDonGia() * selectedHoaDon.getQuangDuong()*((100- selectedHoaDon.getKhuyenMai())/100))
                .append("\n\n");

        // Tạo chuỗi thông tin danh sách HoaDon có tổng giá lớn hơn HoaDon được chọn
        if (!hoaDonList.isEmpty()) {
            detailsBuilder.append("Danh sách có tổng giá nhỏ hơn:\n");
            for (HoaDon hoaDon : hoaDonList) {
                detailsBuilder.append("").append(hoaDon.getSoXe())
                        .append("\nQuãng Đường: ").append(hoaDon.getQuangDuong())
                        .append("\nĐơn giá: ").append(selectedHoaDon.getDonGia())
                        .append("\nKhuyễn Mãi(%): ").append(selectedHoaDon.getKhuyenMai())
                        .append("\nTổng giá: ").append(hoaDon.getDonGia() * hoaDon.getQuangDuong()*((100- hoaDon.getKhuyenMai())/100))
                        .append("\n\n");
            }
        } else {
            detailsBuilder.append("Không có khách hàng có tổng giá nhỏ hơn.");
        }

        builder.setMessage(detailsBuilder.toString());

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Đóng dialog nếu người dùng nhấn OK
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }


    // ánh xạ và sắp xếp
    private void anhXa() {
        btnAdd = findViewById(R.id.btnAdd);
        edtTimKiem = findViewById(R.id.edtTimKiem);

        lvHoaDon = findViewById(R.id.lvHoaDon);

        dsKhachHang = hoaDonDB.danhSachKhachHang();


        //sap xep dsKhachHang
        Collections.sort(dsKhachHang, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon o1, HoaDon o2) {
                // So sánh theo giá trị double (giảm dần)
                return Double.compare(o2.getQuangDuong(), o1.getQuangDuong());

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


        if (item.getItemId() == R.id.item_xoa) { // xoa 1 phan tu trong danh sach
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final int position = info.position;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa thi sinh này?");

            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    HoaDon hd = dsKhachHang.get(position);
                    String soXe = hd.getSoXe();
                    hoaDonDB.xoaKhachHang(soXe);
                    dsKhachHang.remove(position);

                    hoaDonAdapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, "Đã xóa thi sinh", Toast.LENGTH_SHORT).show();
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
        } else if (item.getItemId() == R.id.item_sua) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            Intent it = new Intent(MainActivity.this, UpdateActivity.class);

            Bundle bundle = new Bundle();
            HoaDon selectedHoaDon = dsKhachHang.get(position);
            bundle.putString("KEY_SOXE", selectedHoaDon.getSoXe());
            bundle.putDouble("KEY_QUANGDUONG", selectedHoaDon.getQuangDuong());
            bundle.putDouble("KEY_DONGIA", selectedHoaDon.getDonGia());
            bundle.putDouble("KEY_KHUYENMAI", selectedHoaDon.getKhuyenMai());


            it.putExtras(bundle);
            startActivity(it);
            return false;

        }

        return super.onContextItemSelected(item);

    }

        private void khoiTaoDuLieuDB () {
        hoaDonDB.themKhachHang(new HoaDon("29D2-283.34", 14.3, 7000f, 6f));
        hoaDonDB.themKhachHang(new HoaDon("30K1-129.84", 12.5, 8000f, 20f));
        hoaDonDB.themKhachHang(new HoaDon("29M3-857.65", 9.6, 9000f, 10f));
        hoaDonDB.themKhachHang(new HoaDon("29T2-648.87", 6.5, 10000f, 10f));
        hoaDonDB.themKhachHang(new HoaDon("29T4-746.75", 4.7, 15000f, 50f));
    }
}