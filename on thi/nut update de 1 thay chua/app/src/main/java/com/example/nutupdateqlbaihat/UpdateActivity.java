package com.example.nutupdateqlbaihat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    EditText edtMa, edTenBH, edtTenCS, edtTL;
    Button btnSua, btnXoa;
    DBHelper dbHelper = new DBHelper(this, "", null, 1);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        map();
        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        int ma = bundle.getInt("KEY_MABH");
        edtMa.setText(ma+"");
        String tenBH = bundle.getString("KEY_TENBH");
        edTenBH.setText(tenBH);
        String tenCS = bundle.getString("KEY_TENCS");
        edtTenCS.setText(tenCS);
        float tl = bundle.getFloat("KEY_TL");
        edtTL.setText(tl+"");
        //MainActivity.arrBH.add();
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtMa.getText().toString());
                String tenCS = edtTenCS.getText().toString();
                String tenBH = edTenBH.getText().toString();
                float tl = Float.parseFloat(edtTL.getText().toString());
                BaiHat bh = new BaiHat(id, tenBH, tenCS, tl);
                dbHelper.update(bh);
                Intent it = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị hộp thoại xác nhận trước khi xóa
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xác nhận đồng ý, thực hiện xóa
                        int id = Integer.parseInt(edtMa.getText().toString());
                        dbHelper.deleteById(id);

                        // Chuyển về MainActivity sau khi xóa
                        Intent it = new Intent(UpdateActivity.this, MainActivity.class);
                        startActivity(it);
                    }
                });
                builder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xác nhận không đồng ý, không thực hiện xóa
                        dialog.dismiss(); // Đóng hộp thoại
                    }
                });

                // Hiển thị hộp thoại
                builder.show();
            }
        });
    }
    private void map(){
        edtMa = findViewById(R.id.edtMaBH);
        edtTenCS = findViewById(R.id.edtTenCS);
        edTenBH = findViewById(R.id.edtTenBH);
        edtTL = findViewById(R.id.edtThoiLuong);
        btnSua = findViewById(R.id.btnSua2);
        btnXoa = findViewById(R.id.btnXoa);
    }
}
