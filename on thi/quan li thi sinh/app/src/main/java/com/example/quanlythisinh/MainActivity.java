package com.example.quanlythisinh;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    ThiSinhDB thiSinhDB;
    List<ThiSinh> dsThiSinh;
    ThiSinhAdapter thiSinhAdapter;
    public static ArrayList<ThiSinh> arrTS=new ArrayList<>();

    FloatingActionButton btnAdd;
    EditText edtTimKiem;
    ListView lvThiSinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thiSinhDB = new ThiSinhDB(this, "db_thisinh", null, 2);
        khoiTaoDuLieuDB();
        anhXa();
        themSuKien();

    }

    private void themSuKien() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 =new Intent(MainActivity.this, AddActivity.class);
                startActivity(it1);
            }
        });

    }

    private void anhXa() {
        btnAdd = findViewById(R.id.btnAdd);
        edtTimKiem = findViewById(R.id.edtTimKiem);

        lvThiSinh = findViewById(R.id.lvThiSinh);

        dsThiSinh = thiSinhDB.danhSachThiSinh();

        //sap xep dsThiSinh
        Collections.sort(dsThiSinh, new Comparator<ThiSinh>() {
            @Override
            public int compare(ThiSinh o1, ThiSinh o2) {

                String[] arrName1 = o1.getHoTen().split(" ");
                String[] arrName2 = o2.getHoTen().split(" ");

                return arrName1[arrName1.length - 1].compareToIgnoreCase(arrName2[arrName2.length - 1]);
            }
        });

        thiSinhAdapter = new ThiSinhAdapter(dsThiSinh, this);

        lvThiSinh.setAdapter(thiSinhAdapter);

        //context menu
        registerForContextMenu(lvThiSinh);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.lvThiSinh) {
            getMenuInflater().inflate(R.menu.listview_context_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

//        if (item.getItemId() == R.id.item_xoa) { // xoa nhieu phan tu nho hon hoac bang tong diem
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//            int position = info.position;
//
//            ThiSinh ts = dsThiSinh.get(position);
//            Double tongDiem = ts.getDiemToan() + ts.getDiemLy() + ts.getDiemHoa();
//
//
//            final List<ThiSinh> dsCanXoa = new ArrayList<>();
//            for (ThiSinh thiSinh : dsThiSinh) {
//                if (thiSinh.getDiemHoa() + thiSinh.getDiemLy() + thiSinh.getDiemToan() < tongDiem) {
//                    dsCanXoa.add(thiSinh);
//                }
//            }
//
//            if (dsCanXoa.size() == 0) {
//                Toast.makeText(this, "Khong co thi sinh co tong diem nho hon " + tongDiem, Toast.LENGTH_LONG).show();
//                return false;
//            }
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Xoa thi sinh");
//            builder.setMessage("Ban co muon xoa " + dsCanXoa.size() + " thi sinh co tong diem < " + tongDiem);
//
//            builder.setPositiveButton("Dong y", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    int thanhCong = 0;
//
//                    for (ThiSinh tscanxoa : dsCanXoa) {
//                        if (thiSinhDB.xoaThiSinh(tscanxoa.getSoBaoDanh())) {
//                            thanhCong++;
//                        }
//                    }
//
//                    dsThiSinh.clear();
//                    dsThiSinh.addAll(thiSinhDB.danhSachThiSinh());
//                    thiSinhAdapter.notifyDataSetChanged();
//
//                    Toast.makeText(MainActivity.this, "Xoa thanh cong " + thanhCong + ", that bai " + (dsCanXoa.size() - thanhCong), Toast.LENGTH_SHORT).show();
//
//                    dialog.dismiss();
//                }
//            });
//
//            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//
//            builder.show();
//        }

            if (item.getItemId() == R.id.item_xoa) { // xoa 1 phan tu trong danh sach
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final int position = info.position;

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa thi sinh này?");

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ThiSinh ts = dsThiSinh.get(position);
                        String soBD = ts.getSoBaoDanh();
                        thiSinhDB.xoaThiSinh(soBD);
                        dsThiSinh.remove(position);

                        thiSinhAdapter.notifyDataSetChanged();

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
            }


        else if (item.getItemId() == R.id.item_sua) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int position = info.position;
                Intent it = new Intent(MainActivity.this, UpdateActivity.class);

                Bundle bundle = new Bundle();
                ThiSinh selectedThiSinh = dsThiSinh.get(position);
                bundle.putString("KEY_SOBD", selectedThiSinh.getSoBaoDanh());
                bundle.putString("KEY_HOTEN", selectedThiSinh.getHoTen());
                bundle.putDouble("KEY_DIEMTOAN", selectedThiSinh.getDiemToan());
                bundle.putDouble("KEY_DIEMLY", selectedThiSinh.getDiemLy());
                bundle.putDouble("KEY_DIEMHOA", selectedThiSinh.getDiemHoa());

                it.putExtras(bundle);
                startActivity(it);
                return false;

            }

        return super.onContextItemSelected(item);

    }

    private void khoiTaoDuLieuDB() {
        thiSinhDB.themThiSinh(new ThiSinh("GHA01", "Nguyen Van A", 5f, 6f, 2f));
        thiSinhDB.themThiSinh(new ThiSinh("GHA05", "Nguyen Van F", 9f, 2f, 6f));
        thiSinhDB.themThiSinh(new ThiSinh("GHA02", "Nguyen Van B", 6f, 5f, 3f));
        thiSinhDB.themThiSinh(new ThiSinh("GHA03", "Nguyen Van C", 7f, 4f, 4f));
        thiSinhDB.themThiSinh(new ThiSinh("GHA06", "Nguyen Van G", 10f, 1f, 7f));
        thiSinhDB.themThiSinh(new ThiSinh("GHA04", "Nguyen Van D", 8f, 3f, 5f));
    }
}

