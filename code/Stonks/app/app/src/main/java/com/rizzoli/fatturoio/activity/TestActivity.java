package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.utils.ApiEndPoint;
import com.rizzoli.fatturoio.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    ApiEndPoint apiEndPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button btnGet = findViewById(R.id.buttonTestGet);
        Button btnGetSpecial = findViewById(R.id.buttonTestGetSpecial);
        Button btnPost = findViewById(R.id.buttonTestPost);
        Button btnPostMaxi = findViewById(R.id.buttonTestPostMaxi);
        final TextView tv_1 = findViewById(R.id.textView);
        TextView tv_2 = findViewById(R.id.textView2);
        TextView tv_3 = findViewById(R.id.textView3);
        EditText et_1 = findViewById(R.id.editText);
        EditText et_2 = findViewById(R.id.editText2);
        EditText et_3 = findViewById(R.id.editText3);

        apiEndPoint = RetrofitUtils.getInstance().getApiEndPoint();

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<String> call = apiEndPoint.doTestMethodGet();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (!response.isSuccessful()) {
                            tv_1.setText("Code: " + response.code());
                            return;
                        }
                        tv_1.setText(response.body());
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        tv_1.setText(t.getMessage());
                    }
                });

            }
        });

        btnGetSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPostMaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
