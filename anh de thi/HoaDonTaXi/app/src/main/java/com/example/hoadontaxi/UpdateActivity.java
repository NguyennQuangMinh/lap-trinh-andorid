package com.example.hoadontaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText edtSoXe, edtQuangDuong, edtDonGia, edtKhuyenMai;
    Button buttonSua, buttonBack;
    HoaDonDB dbHelper; // Không cần khởi tạo ở đây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Init();

        dbHelper = new HoaDonDB(this, "db_hoadon", null, 2); // Khởi tạo ở đây sau khi đã gọi Init()

        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        String sx = bundle.getString("KEY_SOXE");
        edtSoXe.setText(sx);

        String quangduong = String.valueOf(bundle.getDouble("KEY_QUANGDUONG"));
        edtQuangDuong.setText(quangduong);

        String dongia = String.valueOf(bundle.getDouble("KEY_DONGIA"));
        edtDonGia.setText(dongia);

        String khuyenmai = String.valueOf(bundle.getDouble("KEY_KHUYENMAI"));
        edtKhuyenMai.setText(khuyenmai);



        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sx = edtSoXe.getText().toString();

                double quangduong = Double.parseDouble(edtQuangDuong.getText().toString());
                double dongia = Double.parseDouble(edtDonGia.getText().toString());
                double khuyenmai = Double.parseDouble(edtKhuyenMai.getText().toString());

                // Tạo đối tượng ThiSinh với các giá trị được cập nhật
                HoaDon updatedHoaDon = new HoaDon(sx, quangduong, dongia, khuyenmai);

                // Cập nhật dữ liệu vào cơ sở dữ liệu
                if (dbHelper.suaKhachHang(updatedHoaDon)) {
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


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    public void Init() {
        edtSoXe = findViewById(R.id.edtSoXe);
        edtQuangDuong = findViewById(R.id.edtQuangDuong);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtKhuyenMai = findViewById(R.id.edtKhuyenMai);
        buttonSua = findViewById(R.id.btnSua);
        buttonBack = findViewById(R.id.btnBack);
    }
}