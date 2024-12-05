package com.example.quanlythisinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText edtSoBD, edtHoTen, edtDiemToan, edtDiemLy, edtDiemHoa;
    Button buttonSua, buttonBack;
    ThiSinhDB dbHelper; // Không cần khởi tạo ở đây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Init();

        dbHelper = new ThiSinhDB(this, "db_thisinh", null, 2); // Khởi tạo ở đây sau khi đã gọi Init()

        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        String sbd = bundle.getString("KEY_SOBD");
        edtSoBD.setText(sbd);

        String hoten = bundle.getString("KEY_HOTEN");
        edtHoTen.setText(hoten);

        String diemtoan = String.valueOf(bundle.getDouble("KEY_DIEMTOAN"));
        edtDiemToan.setText(diemtoan);

        String diemly = String.valueOf(bundle.getDouble("KEY_DIEMLY"));
        edtDiemLy.setText(diemly);

        String diemhoa = String.valueOf(bundle.getDouble("KEY_DIEMHOA"));
        edtDiemHoa.setText(diemhoa);

        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sobd = edtSoBD.getText().toString();
                String hoten = edtHoTen.getText().toString();
                double diemtoan = Double.parseDouble(edtDiemToan.getText().toString());
                double diemly = Double.parseDouble(edtDiemLy.getText().toString());
                double diemhoa = Double.parseDouble(edtDiemHoa.getText().toString());

                // Tạo đối tượng ThiSinh với các giá trị được cập nhật
                ThiSinh updatedThiSinh = new ThiSinh(sobd, hoten, diemtoan, diemly, diemhoa);

                // Cập nhật dữ liệu vào cơ sở dữ liệu
                if (dbHelper.suaThiSinh(updatedThiSinh)) {
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
        edtSoBD = findViewById(R.id.edtSoBD);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiemToan = findViewById(R.id.edtDiemToan);
        edtDiemLy = findViewById(R.id.edtDiemLy);
        edtDiemHoa = findViewById(R.id.edtDiemHoa);
        buttonSua = findViewById(R.id.buttonSua);
        buttonBack = findViewById(R.id.buttonBack);
    }
}
