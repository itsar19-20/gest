package com.rizzoli.fatturoio.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.reflect.TypeToken;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.serverDatabaseModel.Fattura;
import com.rizzoli.fatturoio.utils.VolleyUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class FragmentFatturaLista extends Fragment {

    private TextView textView;
    private Button button;
    private FloatingActionButton fab_sync;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fattura_lista, container, false);
        textView = view.findViewById(R.id.textView_f1);
        button = view.findViewById(R.id.btn_f1);
        fab_sync = view.findViewById(R.id.fab_sync_invoices);

        button.setOnClickListener(v -> goToFragmentFatturaDettaglio());
        fab_sync.setOnClickListener(v -> syncInvoices());

        return view;
    }

    private void syncInvoices() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                VolleyUtils.url("archivio/getAllMineInvoices?user=8"),
                response -> {
                    String str = "";
                    try {
                        Fattura[] fatture = VolleyUtils.getGsonInstance().fromJson(response, Fattura[].class);
                        for (Fattura f : fatture) { str += String.valueOf(f.get_id()) + "\n\n"; }
                        textView.setText(str);
                        Log.e("SERVLET_RESPONSE", "try");
                        Log.e("SERVLET_RESPONSE", response);
                    } catch (Exception e) {
                        textView.setText("//////////////////////////////////////////////");
                        Log.e("SERVLET_RESPONSE", "catch");
                        Log.e("SERVLET_RESPONSE", response);
                        Log.e("SERVLET_RESPONSE", e.toString());
                    }
                },
                error -> {
                    Log.e("ERROR_REQUEST", error.toString());
                    textView.setText("ERROR_REQUEST" + error.toString());
                }
        );
        VolleyUtils.getRequestQueueInstance(((FatturaActivity)getActivity())).add(request);
    }

    private void goToFragmentFatturaDettaglio() {
        ((FatturaActivity)getActivity()).setViewPager(1);
    }

    /*
    // Torna alla home
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getContext(), MainActivity.class));
        finish();
    }
    */

}
