package com.example.de1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonThem,buttonTimKiem,buttonDelete;
    ListView listviewDSBaiHat;
    EditText editTextTimKiem;
    MyDB mydb;
    BaiHatAdapter adapter;
    ArrayList<DSBaiHat> arrBaiHat;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();

        mydb = new MyDB(this);
        buttonThem.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, MainActivityThem.class);
            startActivityForResult(it, 200); // set request Code với màn hình create
        });
        listviewDSBaiHat.setOnItemClickListener((parent, view, position, id) -> {
            i=position;

        });
        buttonDelete.setOnClickListener(v->{
            Integer id= arrBaiHat.get(i).Id;
            long kq=mydb.delete(id);
            if(kq==-1)
            {
                Toast.makeText(MainActivity.this, "Can not delete", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();

            }
            LoadData();
        });
        buttonTimKiem.setOnClickListener(v -> {
            String name = editTextTimKiem.getText().toString();
            ArrayList<DSBaiHat> bh = new ArrayList<DSBaiHat>();
            for (int i = 0; i < arrBaiHat.toArray().length; i++) {
                if (arrBaiHat.get(i).BaiHat.contains(name) == true) {
                    bh.add(arrBaiHat.get(i));
                }
            }
            adapter = new BaiHatAdapter(MainActivity.this, R.layout.item_bai_hat, bh);
            listviewDSBaiHat.setAdapter(adapter);
        });
    }


        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==200&&resultCode==201) // check đúng resultCode, requestCode để thực hiện tương ứng chức năng
            {
                // data sẽ là intent mang dữ liệu
                String baihat=data.getStringExtra("BAIHAT");
                String thoigian=data.getStringExtra("THOIGIAN");
                String tacgia=data.getStringExtra("TACGIA");
                Toast.makeText(MainActivity.this, tacgia.toString(), Toast.LENGTH_LONG).show();
                long ketqua=mydb.insert(baihat, thoigian, tacgia);
                if(ketqua==-1)
                {
                    Toast.makeText(MainActivity.this, "Can not create", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_SHORT).show();
                    LoadData();
                }
            }
    }
    void LoadData()
    {
        arrBaiHat=new ArrayList<DSBaiHat>();
        mydb.openDb();
        Cursor cursor=mydb.get();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            Integer id=cursor.getInt(cursor.getColumnIndex(mydb.getID()));
            String baihat=cursor.getString(cursor.getColumnIndex(mydb.getBAIHAT()));
            String thoigian=cursor.getString(cursor.getColumnIndex(mydb.getTHOIGIAN()));
            String tacgia=cursor.getString(cursor.getColumnIndex(mydb.getTACGIA()));

            arrBaiHat.add(new DSBaiHat( baihat, thoigian,tacgia , id));
            adapter=new BaiHatAdapter(MainActivity.this, R.layout.item_bai_hat, arrBaiHat);
            listviewDSBaiHat.setAdapter(adapter);
        }
    }
    void Init(){
        buttonThem=(Button) findViewById(R.id.buttonThem);
        buttonTimKiem=(Button) findViewById(R.id.buttonTimKiem);
        buttonDelete=(Button) findViewById(R.id.buttonDelete);
        listviewDSBaiHat=(ListView) findViewById(R.id.listviewDSBaiHat);
        editTextTimKiem=(EditText) findViewById(R.id.editTextTimKiem);
    }
}