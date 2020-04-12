package com.rizzoli.fatturoio.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizzoli.fatturoio.GlobalState;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.business.Converter;
import com.rizzoli.fatturoio.business.FatturaManager;
import com.rizzoli.fatturoio.localDatabaseAdapter.ContoDatabaseAdapter;
import com.rizzoli.fatturoio.localDatabaseAdapter.FatturaDatabaseAdapter;
import com.rizzoli.fatturoio.localDatabaseAdapter.PeronaDatabaseAdapter;

import static com.rizzoli.fatturoio.business.ArticoloManager.loadArticoli;

public class FragmentFatturaDettaglio extends Fragment {

    private static FloatingActionButton fabNDC;
    private static TextView tvConto, tvNumero, tvTipo, tvPersona, tvData, tvScadenza, tvNota, tvLordo, tvIva;
    private static ListView listView;
    private static Context context;
    private static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fattura_dettaglio, container, false);

        fabNDC = view.findViewById(R.id.fab_emetti_nota_di_credito);
        tvConto = view.findViewById(R.id.d_tv_conto);
        tvNumero = view.findViewById(R.id.d_tv_numero_fattura);
        tvTipo = view.findViewById(R.id.d_tv_tipo_fattura);
        tvPersona = view.findViewById(R.id.d_tv_persona);
        tvData = view.findViewById(R.id.d_tv_data);
        tvScadenza = view.findViewById(R.id.d_tv_scadenza);
        tvNota = view.findViewById(R.id.d_tv_nota);
        tvLordo = view.findViewById(R.id.d_tv_lordo);
        tvIva = view.findViewById(R.id.d_tv_di_cui_iva);
        listView = view.findViewById(R.id.d_lv_lista_articoli);
        context = ((FatturaActivity)getActivity());
        this.view = view;

        fabNDC.setOnClickListener(v -> FatturaManager.emettiNotaDiCredito());

        return view;
    }

    protected static void refreshListaArticoli(int position) {
        loadArticoli(context, view, listView);
    }

    protected static void loadFattura() {
        try {
            FatturaDatabaseAdapter databaseAdapter = new FatturaDatabaseAdapter(context);
            databaseAdapter.open();
            Cursor cursor = databaseAdapter.fetchById(GlobalState.getFatturaDettaglioId());
            if (cursor.moveToFirst()) {
                // nota di credito
                if (cursor.getInt(cursor.getColumnIndex(databaseAdapter.getKeyNotaDiCreito())) == 0) falseNDC();
                else trueNDC();
                // conto
                ContoDatabaseAdapter contoDatabaseAdapter = new ContoDatabaseAdapter(context);
                contoDatabaseAdapter.open();
                tvConto.setText(contoDatabaseAdapter.getNome(cursor.getInt(cursor.getColumnIndex(FatturaDatabaseAdapter.getKeyConto()))));
                contoDatabaseAdapter.close();
                // numero
                tvNumero.setText(cursor.getString(cursor.getColumnIndex(databaseAdapter.getKeyNumeroFattura())));
                // tipo
                if ((cursor.getString(cursor.getColumnIndex(databaseAdapter.getKeyEUnaFatturaCliente()))).contentEquals("1")) tvTipo.setText("cliente");
                else tvTipo.setText("fornitore");
                //persona
                PeronaDatabaseAdapter peronaDatabaseAdapter = new PeronaDatabaseAdapter(context);
                peronaDatabaseAdapter.open();
                tvPersona.setText(peronaDatabaseAdapter.getNomeCognome(cursor.getInt(cursor.getColumnIndex(FatturaDatabaseAdapter.getKeyPersona()))));
                peronaDatabaseAdapter.close();
                // data
                tvData.setText(Converter.data(cursor.getString(cursor.getColumnIndex(databaseAdapter.getKeyData()))));
                // scadenza
                tvScadenza.setText(cursor.getString(cursor.getColumnIndex(databaseAdapter.getKeyScadenza())));
                // nota
                tvNota.setText(cursor.getString(cursor.getColumnIndex(databaseAdapter.getKeyNota())));
                // lordo
                float lordo = cursor.getFloat(cursor.getColumnIndex(databaseAdapter.getKeyLordo()));
                tvLordo.setText(String.valueOf(lordo));
                // di cui IVA
                float iva = cursor.getFloat(cursor.getColumnIndex(databaseAdapter.getKeyIva()));
                iva *= lordo;
                tvIva.setText(String.valueOf(iva));
            }
            databaseAdapter.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void goToFragmentFatturaLista() {
        ((FatturaActivity)getActivity()).setViewPager(0);
    }

    @SuppressLint("RestrictedApi")
    public static void trueNDC(){
        fabNDC.setEnabled(false);
        fabNDC.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("RestrictedApi")
    private static void falseNDC(){
        fabNDC.setEnabled(true);
        fabNDC.setVisibility(View.VISIBLE);
    }

}
