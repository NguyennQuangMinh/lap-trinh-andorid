package com.example.sanpham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText edtTenSP, edtGiaTien;
    Switch swGiamGia;
    Button btnSua, btnBack;
    SanPhamDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Init();

        dbHelper = new SanPhamDB(this, "db_sanpham", null, 2); // Khởi tạo ở đây sau khi đã gọi Init()

        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        String tensp = bundle.getString("KEY_TENSP");
        edtTenSP.setText(tensp);

        String giatien = String.valueOf(bundle.getDouble("KEY_GIATIEN"));
        edtGiaTien.setText(giatien);

        String sw = bundle.getString("KEY_SW");
        if (sw.equals("Giảm Giá Còn:")) {
            swGiamGia.setChecked(true);
        } else {
            swGiamGia.setChecked(false);
        }



        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = edtTenSP.getText().toString();

                double giatien = Double.parseDouble(edtGiaTien.getText().toString());

                String sw = swGiamGia.isChecked() ? "Giảm Giá Còn:" : "";

                // Tạo đối tượng ThiSinh với các giá trị được cập nhật
                SanPham updatedSanPham = new SanPham(tensp, giatien, sw);

                // Cập nhật dữ liệu vào cơ sở dữ liệu
                if (dbHelper.suaSanPham(updatedSanPham)) {
                    // Cập nhật thành công
                    Toast.makeText(UpdateActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Cập nhật thất bại
                    Toast.makeText(UpdateActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

                // quay lại main activity
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    public void Init() {
        edtTenSP = findViewById(R.id.edtTenSP);
        edtGiaTien = findViewById(R.id.edtGiaTien);
        btnSua= findViewById(R.id.btnSua);
        btnBack= findViewById(R.id.btnBack);
        swGiamGia=findViewById(R.id.swGiamGia);
    }
}