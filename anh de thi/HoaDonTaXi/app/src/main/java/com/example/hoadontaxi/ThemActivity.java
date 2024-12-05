package com.example.hoadontaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThemActivity extends AppCompatActivity {
    EditText edtSoXe, edtQuangDuong, edtDonGia, edtKhuyenMai;
    Button buttonThem, buttonBack;
    HoaDonDB dbHelper; // Không cần khởi tạo ở đây
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        Init();
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soxe = edtSoXe.getText().toString();
                double quangduong, dongia ,khuyenmai;

                try {
                    quangduong= Double.parseDouble(edtQuangDuong.getText().toString());
                    dongia = Double.parseDouble(edtDonGia.getText().toString());
                    khuyenmai= Double.parseDouble(edtKhuyenMai.getText().toString());

                    HoaDon insertHoaDon = new HoaDon(soxe, quangduong, dongia, khuyenmai);

                    // Add the new student to the database
                    dbHelper = new HoaDonDB(ThemActivity.this, "db_hoadon", null, 2);
                    dbHelper.themKhachHang(insertHoaDon);

                    // Navigate back to the MainActivity
                    Intent it1 = new Intent(ThemActivity.this, MainActivity.class);
                    startActivity(it1);

                    finish();
                } catch (NumberFormatException e) {
                    // Handle the case where parsing doubles fails (e.g., invalid input)
                    Toast.makeText(ThemActivity.this, "Vui lòng nhập điểm hợp lệ.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1=new Intent(ThemActivity.this, MainActivity.class);
                startActivity(it1);
                finish();
            }
        }));
    }
    public void Init() {
        edtSoXe = findViewById(R.id.edtSoXe);
        edtQuangDuong = findViewById(R.id.edtQuangDuong);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtKhuyenMai = findViewById(R.id.edtKhuyenMai);
        buttonThem = findViewById(R.id.btnThem);
        buttonBack = findViewById(R.id.btnBack);
    }
}