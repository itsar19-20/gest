package com.rizzoli.fatturoio.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizzoli.fatturoio.GlobalState;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.business.FatturaManager;

public class FragmentFatturaLista extends Fragment {

    private FloatingActionButton fab_sync;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fattura_lista, container, false);

        fab_sync = view.findViewById(R.id.fab_sync_invoices);
        listView = view.findViewById(R.id.listView_fatture);
        FatturaManager.loadInvoices(((FatturaActivity)getActivity()), view, listView);
        listView.setOnItemClickListener((parent, view1, position, id) -> goToFragmentFatturaDettaglio(position, view));
        fab_sync.setOnClickListener(v -> syncInvoices(view));

        return view;
    }

    private void syncInvoices(View view) {
        FatturaManager.syncInvoices(((FatturaActivity)getActivity()), view, listView);
    }

    private void goToFragmentFatturaDettaglio(int position, View view) {
        GlobalState.setFatturaDettaglioId(FatturaManager.takeId(position));
        FragmentFatturaDettaglio.loadFattura();
        FragmentFatturaDettaglio.refreshListaArticoli(position);
        ((FatturaActivity)getActivity()).setViewPager(1);
    }

}
