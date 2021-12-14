package com.quvonchbek.gunohikabiralar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class yozuv_turi extends AppCompatActivity {
    private AlertDialog.Builder d;
    private final Intent i = new Intent();
    //SharedPreferences sp = getSharedPreferences("data", Activity.MODE_PRIVATE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yozuv_turi);
        d = new AlertDialog.Builder(this);
        Button lotin = findViewById(R.id.lotin);
        Button kiril = findViewById(R.id.kiril);
        Objects.requireNonNull(getSupportActionBar()).hide();



        final OnClickListener clicked_l = v -> {
            //Objects.requireNonNull(sp.edit()).putString("til", "lotin").apply();
            //Objects.requireNonNull(sp.edit()).putString("harf", "_l_").apply();
            i.setClass(getApplicationContext(), Mundarija.class);
            startActivity(i);
        };
        final OnClickListener clicked_k = v -> {
            i.setClass(getApplicationContext(), MundarijaKirill.class);
            startActivity(i);
        };
        lotin.setOnClickListener(clicked_l);
        kiril.setOnClickListener(clicked_k);

    }

    @Override
    public void onBackPressed() {
        d.setTitle("Gunohi kabiralar");
        d.setIcon(R.drawable.building);
        d.setMessage("Chiqib ketmoqchimisiz?");
        d.setPositiveButton("Ha", (_dialog, _which) -> finishAffinity());
        d.setNegativeButton("Yo'q", (_dialog, _which) -> {

        });
        d.create().show();

    }
}