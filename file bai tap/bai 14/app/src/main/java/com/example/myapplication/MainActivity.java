package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextHoten, editTextDiemToan, editTextDiemLy, editTextDiemHoa;
    Button buttonSend;
    TextView textViewKQ;
    Spinner spinner;
    double tong;
    String kv;
    double dc;
    private ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode()==33){
                                Intent intent = result.getData();
                                tong = intent.getDoubleExtra("tong",-1);
                                if(tong>25){
                                    textViewKQ.setText(tong + " - Dat");
                                }
                                else {
                                    textViewKQ.setText(tong + " - Khong dat");
                                }
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        ArrayList<String> arrayListKv = new ArrayList<String>();
        arrayListKv.add("Khu vuc 1");
        arrayListKv.add("Khu vuc 2");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, arrayListKv);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kv = arrayListKv.get(i);
                if(i==0){
                    dc = 1.5;
                }
                else {
                    dc = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyIntent();
            }
            private void xulyIntent(){
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("Ten", editTextHoten.getText().toString());
                intent.putExtra("Toan", Double.parseDouble(editTextDiemToan.getText().toString()));
                intent.putExtra("Ly", Double.parseDouble(editTextDiemLy.getText().toString()));
                intent.putExtra("Hoa", Double.parseDouble(editTextDiemHoa.getText().toString()));
                intent.putExtra("dc", dc);
                //startActivityForResult(intent, 99);
                activityResultLauncher.launch(intent);
            }
        });
    }


    // @Override
    // protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //     super.onActivityResult(requestCode, resultCode, data);
    //     if (requestCode==99 && resultCode==33){
    //         int t = data.getIntExtra("tong",1);//        textViewKq.setText(t);
    //}
    //}

    private void InitWidgets() {
        editTextHoten = (EditText) findViewById(R.id.editTextHoten);
        editTextDiemToan = (EditText) findViewById(R.id.editTextDiemToan);
        editTextDiemLy = (EditText) findViewById(R.id.editTextDiemLy);
        editTextDiemHoa = (EditText) findViewById(R.id.editTextDiemHoa);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        textViewKQ = (TextView) findViewById(R.id.textViewKQ);
        spinner = (Spinner) findViewById(R.id.spinner);
    }
}