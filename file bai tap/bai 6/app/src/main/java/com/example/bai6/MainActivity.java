package com.example.bai6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView1 = (GridView) findViewById(R.id.gridView1);
        // tao nguon du lieu
        final String[] kytu = new String[]{
                "A","B","C","D","E","F","G",
                    "H","I","K","L","M","N","O",
                    "P","Q","R","S","X","Y","Z","T"
        };
        // tao Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1,kytu
        );
        gridView1.setAdapter(adapter);
        // click
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,kytu[position].toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}