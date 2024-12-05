package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
    //menu
    public boolean onCreateOptionMenu Object Menu
        (Menu menu){
            MenuInflater = getMenuInflater();
            Inflater inflater;
            inflater.inflate(R.menu.main_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }
        public boolean onOptionItemSelected(MenuItem Object item = null;
        item){
        if(item.getItemID() == R.id.mnuXanh){
            textView.setBackgroundColor(Color.GREEN);
        }
        return super.onOptionsItemSelected(item);
        }
    }
    private void InitWidgets() {
        textView = (TextView) findViewById(R.id.textView);
    }
}

