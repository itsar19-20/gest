package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.serverDatabaseModel.TTTesttt;
import com.rizzoli.fatturoio.utils.VolleyUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyTestActivity extends AppCompatActivity {

    private static Button btn_cls, btn_get, btn_get_query, btn_post;
    private static TextView tv_0, tv_1, tv_2, tv_3, tv_url;
    private static EditText et_1, et_2, et_3;
    private static String url = VolleyUtils.url("test");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_test);

        btn_cls = findViewById(R.id.btn_cls);
        btn_get = findViewById(R.id.btn_get);
        btn_get_query = findViewById(R.id.btn_get_query);
        btn_post = findViewById(R.id.btn_post);
        tv_0 = findViewById(R.id.tv_0);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);
        tv_url = findViewById(R.id.tv_url);
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);

        tv_url.setText(VolleyUtils.url("test"));

        btn_cls.setOnClickListener(v -> cls());

        btn_get.setOnClickListener(v -> get());

        btn_get_query.setOnClickListener(v -> getQuery());

        btn_post.setOnClickListener(v -> {
            try {
                post();
            } catch (JSONException e) {
                e.printStackTrace();
                tv_0.setText(e.toString());
            }
        });
    }

    private void cls() {
        tv_0.setText("");
        tv_1.setText("");
        tv_2.setText("");
        tv_3.setText("");
    }

    private void get() {
        Toast.makeText(this, "GET", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new JSONObject(),
                response -> {
                    try {
                        // Converto la risposta in un oggetto di tipo "TTTesttt" grazie alla libreria Gson
                        TTTesttt testtt = VolleyUtils.getGsonInstance().fromJson(response.toString(), TTTesttt.class);
                        tv_0.setText(response.toString());
                        tv_1.setText(testtt.getAlfa());
                    } catch (Exception e) {
                        e.printStackTrace();
                        tv_0.setText(e.toString());
                    }
                    Log.e("SERVLET_RESPONSE", response.toString());
                },
                error -> Log.d("ERROR_REQUEST", error.toString())
        );
        VolleyUtils.getRequestQueueInstance(this).add(request);
    }

    private void getQuery() {
        Toast.makeText(this, "GET QUERY", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                // Imposto i paramenti "alfa", "bravo", "charlie" con i valori delle EditText
                VolleyUtils.url("test?alfa=" + et_1.getText().toString() + "&bravo=" + et_2.getText().toString() + "&charlie=" + et_3.getText().toString()),
                new JSONObject(),
                response -> {
                    try {
                        // Converto la risposta in un oggetto di tipo "TTTesttt" grazie alla libreria Gson
                        TTTesttt testtt = VolleyUtils.getGsonInstance().fromJson(response.toString(), TTTesttt.class);
                        tv_0.setText(response.toString());
                        tv_1.setText(testtt.getAlfa());
                        tv_2.setText(testtt.getBravo());
                        tv_3.setText("#Valore immesso * 3# : " + String.valueOf(testtt.getCharlie()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        tv_0.setText(e.toString());
                    }
                    Log.e("SERVLET_RESPONSE", response.toString());
                },
                error -> Log.d("ERROR_REQUEST", error.toString())
        );
        VolleyUtils.getRequestQueueInstance(this).add(request);
    }

    private void post() throws JSONException {
        Toast.makeText(this, "POST", Toast.LENGTH_SHORT).show();

        TTTesttt oggettoDaInviare = new TTTesttt(et_1.getText().toString(), et_2.getText().toString(), Integer.valueOf(et_3.getText().toString()));
        /*
        HashMap oggettoDaInviare = new HashMap();
        oggettoDaInviare.put("alfa", et_1.getText().toString());

         */

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                new JSONObject(VolleyUtils.getGsonInstance().toJson(oggettoDaInviare)),
                response -> {
                    try {
                        TTTesttt testtt = VolleyUtils.getGsonInstance().fromJson(response.toString(), TTTesttt.class);
                        tv_0.setText("La servlet ha risposto con l'oggetto che hai inviato: " + response.toString());
                        tv_1.setText(testtt.getAlfa());
                        tv_2.setText(testtt.getBravo());
                        tv_3.setText(String.valueOf(testtt.getCharlie()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        tv_0.setText(e.toString());
                    }
                    Log.e("SERVLET_RESPONSE", response.toString());
                },
                error -> Log.d("ERROR_REQUEST", error.toString())
        );
        VolleyUtils.getRequestQueueInstance(this).add(request);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VolleyTestActivity.this, MainActivity.class));
        finish();
    }
}
