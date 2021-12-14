package com.quvonchbek.gunohikabiralar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MundarijaKirill extends AppCompatActivity {
    private double num2 = 1;
    private ListView kabiralar;
    private WebView web;
    private final Intent i = new Intent();
    String sp = "kirill";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mundarija);
        kabiralar = findViewById(R.id.kabiralar);
        web = findViewById(R.id.web);
        Objects.requireNonNull(getSupportActionBar()).show();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Мундарижа");

        try {
            JSONObject obj = new JSONObject((String) Objects.requireNonNull(json_string()));
            JSONArray sahifalar = obj.getJSONArray("kirill");
            List<String> latina = new ArrayList<>();
            latina.add(sahifalar.getJSONObject(0).getString("title"));
            for (int i=1; i<sahifalar.length(); i++){
                JSONObject detail = sahifalar.getJSONObject(i);
                latina.add(detail.getString("id").concat(". ").concat(detail.getString("title")));

            }
            if (kabiralar != null){
                kabiralar.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, latina));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        assert kabiralar != null;
        kabiralar.setOnItemClickListener((_param1, _param2, _param3, _param4) -> {
            JSONObject obj;
            try {
                obj = new JSONObject((String) Objects.requireNonNull(json_string()));
                JSONArray sahifalar = obj.getJSONArray("kirill");
                web.loadUrl("file:///android_asset/Kaboir/".concat(sp).concat("/").concat(String.valueOf((long) (_param3)).concat("_k_").concat("gunohi_kabira.html")));
                kabiralar.setVisibility(View.GONE);
                web.setVisibility(View.VISIBLE);
                JSONObject detail = sahifalar.getJSONObject(Integer.parseInt(String.valueOf((long) (_param3))));
                Objects.requireNonNull(getSupportActionBar()).setTitle(detail.getString("title"));
                num2 = 2;
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }

    private Object json_string() {
        String json;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return  json;
    }

    @Override
    public void onBackPressed(){
        Objects.requireNonNull(getSupportActionBar()).setTitle("Мундарижа");
        if (num2 == 2){
            web.setVisibility(View.GONE);
            kabiralar.setVisibility(View.VISIBLE);
            num2 = 1;
        }
        else {
            i.setClass(getApplicationContext(), yozuv_turi.class);
            startActivity(i);
        }
    }
}