package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextHoTen, editTextNamSinh, editTextID;
    TextView textViewData;
    Button buttonThem, buttonSua, buttonXoa, buttonLoadAll;
    MyDBHelper dbHelper = new MyDBHelper(this);

    protected void onStart(){
        super.onStart();
    }

    protected void onStop(){
        super.onStop();
        dbHelper.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long resultInsert = dbHelper.Insert(
                        editTextHoTen.getText().toString(),
                        Integer.parseInt(editTextNamSinh.getText().toString())

                );
                if(resultInsert == -1){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long resultUpdate = dbHelper.Update(
                        Integer.parseInt(editTextID.getText().toString()),
                        editTextHoTen.getText().toString(),
                        Integer.parseInt(editTextNamSinh.getText().toString())
                );
            }
        });
        buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long resultDelete = dbHelper.Delete(
                        Integer.parseInt(editTextID.getText().toString())
                );
                if (resultDelete == 0) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }

            }

        });
        buttonLoadAll.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                Cursor cursor = dbHelper.DisplayAll();
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                    buffer.append(cursor.getString((cursor.getColumnIndex(MyDBHelper.getID()))));

                    buffer.append(" - ");
                    buffer.append((cursor.getString(cursor.getColumnIndex((MyDBHelper.getNAME())))));
                    buffer.append(" - ");
                    buffer.append((cursor.getString(cursor.getColumnIndex((MyDBHelper.getYEARDB())))));
                    buffer.append("\n");
                    textViewData.setText(buffer);
                }
            }
        });
    }


    private void InitWidget(){
        editTextHoTen = (EditText) findViewById(R.id.editTextHoten);
        editTextNamSinh = (EditText) findViewById(R.id.editTextNamsinh);
        editTextID = (EditText) findViewById(R.id.editTextID);
        buttonThem = (Button) findViewById(R.id.buttonThem);
        buttonSua = (Button) findViewById(R.id.buttonSua);
        buttonXoa = (Button) findViewById(R.id.buttonXoa);
        buttonLoadAll = (Button) findViewById(R.id.buttonLoadall);
        textViewData = (TextView) findViewById(R.id.textViewData);
    }
}
