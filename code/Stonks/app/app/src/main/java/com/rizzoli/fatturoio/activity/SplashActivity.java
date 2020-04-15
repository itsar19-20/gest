package com.rizzoli.fatturoio.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rizzoli.fatturoio.GlobalState;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String user = sp.getString("username", null);
        if (user == null)
            startActivity(new Intent(this, LoginActivity.class));
        else {
            GlobalState.setUserId(Integer.valueOf(sp.getString("id", null)));
            GlobalState.setUserName(user);
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
}
