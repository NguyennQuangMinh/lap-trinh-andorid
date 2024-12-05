package com.example.quanlythisinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText edtSoBD, edtHoTen, edtDiemToan, edtDiemLy, edtDiemHoa;
    Button buttonThem, buttonBack;
    ThiSinhDB dbHelper; // Không cần khởi tạo ở đây
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Init();
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sobd = edtSoBD.getText().toString();
                String hoten = edtHoTen.getText().toString();
                double diemtoan, diemly, diemhoa;

                try {
                    diemtoan = Double.parseDouble(edtDiemToan.getText().toString());
                    diemly = Double.parseDouble(edtDiemLy.getText().toString());
                    diemhoa = Double.parseDouble(edtDiemHoa.getText().toString());

                    ThiSinh insertThiSinh = new ThiSinh(sobd, hoten, diemtoan, diemly, diemhoa);

                    // Add the new student to the database
                    dbHelper = new ThiSinhDB(AddActivity.this, "db_thisinh", null, 2);
                    dbHelper.themThiSinh(insertThiSinh);

                    // Navigate back to the MainActivity
                    Intent it1 = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(it1);
                    finish();
                } catch (NumberFormatException e) {
                    // Handle the case where parsing doubles fails (e.g., invalid input)
                    Toast.makeText(AddActivity.this, "Vui lòng nhập điểm hợp lệ.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1=new Intent(AddActivity.this, MainActivity.class);
                startActivity(it1);
                finish();
            }
        }));
    }
    public void Init() {
        edtSoBD = findViewById(R.id.edtSoBD);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiemToan = findViewById(R.id.edtDiemToan);
        edtDiemLy = findViewById(R.id.edtDiemLy);
        edtDiemHoa = findViewById(R.id.edtDiemHoa);
        buttonThem = findViewById(R.id.buttonThem);
        buttonBack = findViewById(R.id.buttonBack);
    }
}
