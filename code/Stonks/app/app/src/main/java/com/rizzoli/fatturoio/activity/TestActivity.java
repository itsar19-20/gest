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
        TextView tv_1 = findViewById(R.id.textView);
        TextView tv_2 = findViewById(R.id.textView2);
        TextView tv_3 = findViewById(R.id.textView3);
        EditText et_1 = findViewById(R.id.editText);
        EditText et_2 = findViewById(R.id.editText2);
        EditText et_3 = findViewById(R.id.editText3);

        apiEndPoint = RetrofitUtils.getInstance().getApiEndPoint();

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call<String> call =
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
