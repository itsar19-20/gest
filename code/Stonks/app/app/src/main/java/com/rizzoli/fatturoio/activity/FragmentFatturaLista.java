package com.rizzoli.fatturoio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rizzoli.fatturoio.R;

public class FragmentFatturaLista extends Fragment {

    private TextView textView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fattura_lista, container, false);
        textView = view.findViewById(R.id.textView_f1);
        button = view.findViewById(R.id.btn_f1);

        button.setOnClickListener(v -> goToFragmentFatturaDettaglio());

        return view;
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
