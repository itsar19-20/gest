package com.rizzoli.fatturoio.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.rizzoli.fatturoio.GlobalState;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.serverDatabaseModel.Fattura;
import com.rizzoli.fatturoio.serverDatabaseModel.User;
import com.rizzoli.fatturoio.utils.VolleyUtils;

public class LoginActivity extends AppCompatActivity {

    private TextView tvError;
    private EditText etUsername, etPassword;
    private Switch sRestaConnesso;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvError = findViewById(R.id.textView_error_login);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        sRestaConnesso = findViewById(R.id.switch1);
        btn = findViewById(R.id.btn_login);

        btn.setOnClickListener(v -> login());
    }

    private void login() {
        String user = etUsername.getText().toString(), pwd = etPassword.getText().toString();
        if (user.isEmpty() || pwd.isEmpty())
            tvError.setText("Inserire tutti i campi");
        else {
            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    VolleyUtils.url("login?username=" + user + "&password=" + pwd),
                    response -> {
                        try {
                            User u = VolleyUtils.getGsonInstance().fromJson(response, User.class);
                            loggedIn(u);
                        } catch (Exception e) {
                            e.printStackTrace();
                            tvError.setText("Username e/o password errati");
                            Toast.makeText(this, "impossibile risolvere la risposta del sever", Toast.LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        Toast.makeText(this, "impossibile contattare il sever", Toast.LENGTH_LONG).show();
                    }
            );
            VolleyUtils.getRequestQueueInstance(this).add(request);
        }
    }

    private void loggedIn(User u) {
        if (sRestaConnesso.isChecked()) {
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("id", String.valueOf(u.get_id()));
            editor.putString("username", u.getUsername());
            editor.commit();
        }
        GlobalState.setUserId(u.get_id());
        GlobalState.setUserName(u.getUsername());
        startActivity(new Intent(this, MainActivity.class));
    }

}
