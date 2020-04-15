package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.rizzoli.fatturoio.GlobalState;
import com.rizzoli.fatturoio.R;

public class ProfiloActivity extends AppCompatActivity {

    private Button btnEsci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        btnEsci = findViewById(R.id.button_esci);
        btnEsci.setOnClickListener(v -> esci());
    }

    private void esci() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", null);
        editor.putString("id", null);
        editor.apply();
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
