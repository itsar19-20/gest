package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rizzoli.fatturoio.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonToTestActivity, btnScadenziario, btnToFatturaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonToTestActivity = findViewById(R.id.buttonToTestActivity);
        btnScadenziario=findViewById(R.id.btnScadenziario);
        btnToFatturaActivity = findViewById(R.id.buttonToFatturaActivity);

        btnToFatturaActivity.setOnClickListener(v -> startFatturaActivity());

        buttonToTestActivity.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TestVolleyActivity.class));
            finish();
        });

        btnScadenziario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CercaScadenziario.class));
                finish();
            }
        });

    }

    private void startFatturaActivity() {
        startActivity(new Intent(MainActivity.this, FatturaActivity.class));
        finish();
    }
}
