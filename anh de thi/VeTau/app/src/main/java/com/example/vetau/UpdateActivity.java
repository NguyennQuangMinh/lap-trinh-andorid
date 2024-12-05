package com.example.vetau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText edtGaDi, edtGaDen, edtDonGia;
    RadioButton rdoMotChieu, rdoKhuHoi;
    RadioGroup rdog;
    Button buttonUpdate, buttonBack;
    VeTauDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Init();

        dbHelper = new VeTauDB(this, "db_vetau", null, 2); // Khởi tạo ở đây sau khi đã gọi Init()

        Intent it = getIntent();
        Bundle bundle = it.getExtras();

        String gadi = bundle.getString("KEY_GADI");
        edtGaDi.setText(gadi);

        String gaden = bundle.getString("KEY_GADEN");
        edtGaDen.setText(gaden);

        String dongia = String.valueOf(bundle.getDouble("KEY_DONGIA"));
        edtDonGia.setText(dongia);

        String chieudi = bundle.getString("KEY_CHIEUDI");
        if (chieudi.equals("Một Chiều")) {
            rdoMotChieu.setChecked(true);
        } else {
            rdoKhuHoi.setChecked(true);
        }



        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gadi = edtGaDi.getText().toString();

                String gaden = edtGaDen.getText().toString();
                double dongia = Double.parseDouble(edtDonGia.getText().toString());
                String chieudi;
                if (rdoMotChieu.isChecked())
                {
                    chieudi="Một Chiều";
                }
                else
                {
                    chieudi="Khứ Hồi";
                }

                // Tạo đối tượng ThiSinh với các giá trị được cập nhật
                VeTau updatedVeTau = new VeTau(gadi, gaden, dongia, chieudi);

                // Cập nhật dữ liệu vào cơ sở dữ liệu
                if (dbHelper.suaVeTau(updatedVeTau)) {
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
        edtGaDi = findViewById(R.id.edtGaDi);
        edtGaDen = findViewById(R.id.edtGaDen);
        edtDonGia = findViewById(R.id.edtDonGia);
        rdog= findViewById(R.id.rdog);
        rdoMotChieu = findViewById(R.id.rdoMotChieu);
        rdoKhuHoi = findViewById(R.id.rdoKhuHoi);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonBack = findViewById(R.id.buttonBack);
    }
}