package com.rizzoli.fatturoio.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.business.FatturaManager;

public class FragmentFatturaLista extends Fragment {

    private FloatingActionButton fab_sync;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fattura_lista, container, false);
        fab_sync = view.findViewById(R.id.fab_sync_invoices);

        FatturaManager.loadInvoices(((FatturaActivity)getActivity()), view);

        fab_sync.setOnClickListener(v -> syncInvoices(view));

        return view;
    }

    private void syncInvoices(View view) {
        FatturaManager.syncInvoices(((FatturaActivity)getActivity()), view);
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
