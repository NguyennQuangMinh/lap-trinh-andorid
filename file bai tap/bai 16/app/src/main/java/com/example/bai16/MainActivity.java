package com.example.bai16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    Button buttonPlay, buttonStop, buttonStartService, buttonStopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player = MediaPlayer.create(MainActivity.this, Settings.System.DEFAULT_RINGTONE_URI);
                player.setLooping(true);
                player.start();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.stop();
            }
        });
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });
        buttonStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MyService.class));
            }
        });
    }

    private void initWidget() {
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonStartService = (Button) findViewById(R.id.buttonStartService);
        buttonStopService = (Button) findViewById(R.id.buttonStopService);
    }
}