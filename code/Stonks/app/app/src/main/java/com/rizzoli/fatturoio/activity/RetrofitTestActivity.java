package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.serverDatabaseModel.TTTesttt;
import com.rizzoli.fatturoio.utils.ApiEndPoint;
import com.rizzoli.fatturoio.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitTestActivity extends AppCompatActivity {

    ApiEndPoint apiEndPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button btnCls = findViewById(R.id.buttonTestClear);
        Button btnGet = findViewById(R.id.buttonTestGet);
        Button btnGetQuery = findViewById(R.id.buttonTestGetQuery);
        Button btnGetPath = findViewById(R.id.buttonTestGetPath);
        Button btnPost = findViewById(R.id.buttonTestPost);
        Button btnPostMaxi = findViewById(R.id.buttonTestPostMaxi);
        Button btnPostBody = findViewById(R.id.buttonTestPostBody);
        Button btnPostMultipart = findViewById(R.id.buttonTestPostMultipart);
        TextView tv_1 = findViewById(R.id.textView);
        TextView tv_2 = findViewById(R.id.textView2);
        TextView tv_3 = findViewById(R.id.textView3);
        EditText et_1 = findViewById(R.id.editText);
        EditText et_2 = findViewById(R.id.editText2);
        EditText et_3 = findViewById(R.id.editText3);

        apiEndPoint = RetrofitUtils.getInstance().getApiEndPoint();

        btnCls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_1.setText("Clean");
                tv_2.setText("Clean");
                tv_3.setText("Clean");
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TTTesttt> call = apiEndPoint.doTestMethodGet();
                call.enqueue(new Callback<TTTesttt>() {
                    @Override
                    public void onResponse(Call<TTTesttt> call, Response<TTTesttt> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        TTTesttt t = response.body();
                        tv_1.setText(t.getAlfa());
                    }
                    @Override
                    public void onFailure(Call<TTTesttt> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });
            }
        });

        btnGetQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TTTesttt> call = apiEndPoint.doTestMethodGetQuery(et_1.getText().toString());
                call.enqueue(new Callback<TTTesttt>() {
                    @Override
                    public void onResponse(Call<TTTesttt> call, Response<TTTesttt> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        TTTesttt t = response.body();
                        tv_1.setText(t.getAlfa());
                        tv_2.setText(t.getBravo());
                        tv_3.setText(String.valueOf(t.getCharlie()));
                    }
                    @Override
                    public void onFailure(Call<TTTesttt> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });
            }
        });

        btnGetPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TTTesttt> call = apiEndPoint.doTestMethodGetPath(et_1.getText().toString());
                call.enqueue(new Callback<TTTesttt>() {
                    @Override
                    public void onResponse(Call<TTTesttt> call, Response<TTTesttt> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        TTTesttt t = response.body();
                        tv_1.setText(t.getAlfa());
                        tv_2.setText(t.getBravo());
                        tv_3.setText(String.valueOf(t.getCharlie()));
                    }
                    @Override
                    public void onFailure(Call<TTTesttt> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TTTesttt> call = apiEndPoint.doTestMethodPost(et_1.getText().toString());
                call.enqueue(new Callback<TTTesttt>() {
                    @Override
                    public void onResponse(Call<TTTesttt> call, Response<TTTesttt> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        TTTesttt t = response.body();
                        tv_1.setText(t.getAlfa());
                    }
                    @Override
                    public void onFailure(Call<TTTesttt> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });
            }
        });

        btnPostMaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTTesttt t = new TTTesttt("c", "r", 7);
                Call<TTTesttt> call = apiEndPoint.doTestMethodPostBody(t);
                call.enqueue(new Callback<TTTesttt>() {
                    @Override
                    public void onResponse(Call<TTTesttt> call, Response<TTTesttt> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        TTTesttt t = response.body();
                        tv_1.setText(t.getAlfa());
                        tv_2.setText(t.getBravo());
                        tv_3.setText(String.valueOf(t.getCharlie()));
                    }
                    @Override
                    public void onFailure(Call<TTTesttt> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });
            }
        });

        btnPostBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TTTesttt> call = apiEndPoint.doTestMethodPostMaxi(et_1.getText().toString(), et_2.getText().toString(), Integer.valueOf(et_3.getText().toString()));
                call.enqueue(new Callback<TTTesttt>() {
                    @Override
                    public void onResponse(Call<TTTesttt> call, Response<TTTesttt> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        TTTesttt t = response.body();
                        tv_1.setText(t.getAlfa());
                        tv_2.setText(t.getBravo());
                        tv_3.setText(String.valueOf(t.getCharlie()));
                    }
                    @Override
                    public void onFailure(Call<TTTesttt> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });
            }
        });

        btnPostMultipart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TTTesttt> call = apiEndPoint.doTestMethodPostMultipart(et_1.getText().toString(), et_2.getText().toString());
                call.enqueue(new Callback<TTTesttt>() {
                    @Override
                    public void onResponse(Call<TTTesttt> call, Response<TTTesttt> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        tv_2.setText("Richiesta multipart riuscita");
                    }
                    @Override
                    public void onFailure(Call<TTTesttt> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RetrofitTestActivity.this, MainActivity.class));
        finish();
    }
}
