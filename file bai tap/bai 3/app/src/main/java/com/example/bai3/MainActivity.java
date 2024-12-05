package com.example.bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    EditText editTextCannang, editTextChieucao;
    Button buttonok;
    TextView textViewBMI, textViewTinhtrang;
    ImageView imageViewHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                imageViewHinh.setVisibility(0);
                //imageViewHinh.setImageResource(R.drawable.binhthuong);
                buttonok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String s1 = editTextCannang.getText().toString();
                        double cn = Double.parseDouble(s1);
                        String s2 = editTextChieucao.getText().toString();
                        double cc = Double.parseDouble(s2);
                        double BMI = cn/(cc*cc);
                        DecimalFormat df = new DecimalFormat("#.#");
                        double BMI1 = Double.parseDouble(df.format(BMI));
                        textViewBMI.setText(String.valueOf(BMI1));
                        String Tinhtrang;
                        if (BMI < 15){
                            Tinhtrang = "Suy dinh duong";
                            imageViewHinh.setImageResource(R.drawable.gay);
                        }
                        else if (BMI < 16){
                            Tinhtrang = "Sieu gay";
                            imageViewHinh.setImageResource(R.drawable.gay);
                        }
                        else if (BMI < 18.5){
                            Tinhtrang = "Gay";
                            imageViewHinh.setImageResource(R.drawable.gay);
                        }
                        else if (BMI < 25){
                            Tinhtrang = "Binh thuong";
                            imageViewHinh.setImageResource(R.drawable.bt);
                        }
                        else if (BMI < 30){
                            Tinhtrang = "Thua can";
                            imageViewHinh.setImageResource(R.drawable.beo);
                        }
                        else if (BMI < 35){
                            Tinhtrang = "Beo phi do 1";
                            imageViewHinh.setImageResource(R.drawable.beo);
                        }
                        else if (BMI < 40){
                            Tinhtrang = "Beo phi do 2";
                            imageViewHinh.setImageResource(R.drawable.beo);
                        }
                        else {
                            Tinhtrang = "Beo phi do 3";
                            imageViewHinh.setImageResource(R.drawable.beo);
                        }
                        textViewTinhtrang.setText(Tinhtrang);
                    }
                });
            }
        });
    }
    private void InitWidgets() {
        editTextCannang = (EditText) findViewById(R.id.editTextCannang);
        editTextChieucao = (EditText) findViewById(R.id.editTextChieucao);
        buttonok = (Button) findViewById(R.id.button);
        textViewBMI = (TextView) findViewById(R.id.textViewBMI);

        imageViewHinh = (ImageView) findViewById(R.id.imageView);
        textViewTinhtrang = (TextView) findViewById(R.id.textViewTinhtrang);
    }
}