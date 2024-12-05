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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ThemActivity extends AppCompatActivity {
    EditText edtGaDi, edtGaDen, edtDonGia;
    RadioButton rdoMotChieu, rdoKhuHoi;
    RadioGroup rdog;
    Button buttonThem, buttonBack;
    VeTauDB dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        Init();

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gadi = edtGaDi.getText().toString();
                String gaden = edtGaDen.getText().toString();
                double dongia;
                String chieuDi;
                if (rdoMotChieu.isChecked())
                {
                    chieuDi="Một Chiều";
                }
                else
                {
                    chieuDi="Khứ Hồi";
                }

                try {
                    dongia = Double.parseDouble(edtDonGia.getText().toString());
                    VeTau insertVeTau = new VeTau( gadi, gaden, dongia, chieuDi);

                    dbHelper = new VeTauDB(ThemActivity.this, "db_vetau", null, 2);
                    dbHelper.themVeTau(insertVeTau);

                    Intent it1 = new Intent(ThemActivity.this, MainActivity.class);
                    startActivity(it1);

                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(ThemActivity.this, "Vui lòng nhập giá vé hợp lệ.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(ThemActivity.this, MainActivity.class);
                startActivity(it1);
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
        buttonThem = findViewById(R.id.buttonThem);
        buttonBack = findViewById(R.id.buttonBack);
    }
}
