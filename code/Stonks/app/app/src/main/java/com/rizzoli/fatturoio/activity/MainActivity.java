package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizzoli.fatturoio.GlobalState;
import com.rizzoli.fatturoio.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonToTestActivity;
    private FloatingActionButton btnProfilo, btnArchivio, btnScadenziario, btnConti;
    private TextView tvCiaoUtente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonToTestActivity = findViewById(R.id.buttonToTestActivity);
        btnProfilo = findViewById(R.id.fab_profilo);
        btnArchivio = findViewById(R.id.fab_archivio);
        btnScadenziario = findViewById(R.id.fab_scadenziario);
        btnConti = findViewById(R.id.fab_conti);
        tvCiaoUtente = findViewById(R.id.textView_ciao_utente);

        tvCiaoUtente.setText("Ciao " + GlobalState.getUserName() + "!");

        buttonToTestActivity.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TestVolleyActivity.class));
            finish();
        });
        btnProfilo.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfiloActivity.class));
            finish();
        });
        btnArchivio.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FatturaActivity.class));
            finish();
        });
        btnScadenziario.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CercaScadenziario.class));
            finish();
        });
        btnConti.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ContiActivity.class));
            finish();
        });

    }
}
