package com.rizzoli.fatturoio.activity;

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

public class FragmentFatturaDettaglio extends Fragment {

    private TextView textView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fattura_dettaglio, container, false);
        textView = view.findViewById(R.id.textView_f2);
        button = view.findViewById(R.id.btn_f2);

        button.setOnClickListener(v -> goToFragmentFatturaLista());

        return view;
    }

    private void goToFragmentFatturaLista() {
        ((FatturaActivity)getActivity()).setViewPager(0);
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
