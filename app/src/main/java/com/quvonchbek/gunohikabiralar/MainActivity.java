package com.quvonchbek.gunohikabiralar;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final Timer _timer = new Timer();
    private final Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @Override
    public void onStart() {
        super.onStart();
        i.setClass(getApplicationContext(), yozuv_turi.class);
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> startActivity(i));
            }
        };
        _timer.schedule(t, 2000);
    }
}
