package com.rizzoli.fatturoio.business;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.rizzoli.fatturoio.GlobalState;
import com.rizzoli.fatturoio.localDatabaseAdapter.FatturaCursorAdapter;
import com.rizzoli.fatturoio.localDatabaseAdapter.FatturaDatabaseAdapter;
import com.rizzoli.fatturoio.localDatabaseAdapter.PeronaDatabaseAdapter;
import com.rizzoli.fatturoio.serverDatabaseModel.Articolo;
import com.rizzoli.fatturoio.serverDatabaseModel.Fattura;
import com.rizzoli.fatturoio.utils.VolleyUtils;

public class FatturaManager {

    private static FatturaDatabaseAdapter databaseAdapter;
    private static FatturaCursorAdapter cursorAdapter;
    private static Cursor cursor;
    private static Fattura[] invoices;
    private static Context context;
    private static View view;
    private static ListView listViewFatture;

    public static void setContext(Context context) { FatturaManager.context = context; }
    public static void setView(View view) { FatturaManager.view = view; }
    public static void setListViewFatture(ListView listView) { FatturaManager.listViewFatture = listView; }

    public static void syncInvoices(Context context, View view, ListView listView) {
        try {
            setContext(context);
            setView(view);
            setListViewFatture(listView);
            makeTheRequestToTheServer();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void loadInvoices(Context context, View view, ListView listView) {
        try {
            setContext(context);
            setView(view);
            setListViewFatture(listView);
            refreshListView();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static void makeTheRequestToTheServer() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                VolleyUtils.url("archivio/getAllMineInvoices?user=8"),
                response -> {
                    String str = "\n";
                    try {
                        invoices = VolleyUtils.getGsonInstance().fromJson(response, Fattura[].class);
                        for (Fattura f : invoices) { str += String.valueOf(f.get_id()) + "\n\n"; }
                        putValuesIntoLocalDatabase();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "impossibile risolvere la risposta del sever", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(context, "impossibile contattare il sever", Toast.LENGTH_LONG).show();
                }
        );
        VolleyUtils.getRequestQueueInstance(context).add(request);
    }

    private static void putValuesIntoLocalDatabase() {
        try {
            databaseAdapter = new FatturaDatabaseAdapter(context);
            databaseAdapter.open();
            int num = 0;
            for (Fattura f : invoices) {
                Integer numArticoli = 0;
                Articolo[] articoli = f.getArticoli();
                for (Articolo a : articoli) { numArticoli++; }
                if (!databaseAdapter.exist(f.get_id())) {
                    databaseAdapter.create(
                            f.get_id(),
                            f.getData(),
                            f.getAnno(),
                            f.getScadenza(),
                            f.iseUnaFatturaCliente() ? 1 : 0,
                            f.getPersona().get_id(),
                            f.getNota(),
                            f.getNumeroFattura(),
                            f.getIva(),
                            f.getLordo(),
                            f.isPagata() ? 1 : 0,
                            f.isNotaDiCredito() ? 1 : 0,
                            f.getConto().get_id(),
                            numArticoli
                    );
                    ArticoloManager.create(f.getArticoli(), f.get_id(), context);
                    num++;
                } else {
                    databaseAdapter.update(
                            f.get_id(),
                            f.getData(),
                            f.getAnno(),
                            f.getScadenza(),
                            f.iseUnaFatturaCliente() ? 1 : 0,
                            f.getPersona().get_id(),
                            f.getNota(),
                            f.getNumeroFattura(),
                            f.getIva(),
                            f.getLordo(),
                            f.isPagata() ? 1 : 0,
                            f.isNotaDiCredito() ? 1 : 0,
                            f.getConto().get_id(),
                            numArticoli
                    );
                }
                ContoManager.syncConto(f.getConto(), context);
                PersonaManager.syncPersona(f.getPersona(), context);
            }
            if (num > 0) Toast.makeText(context, num + " nuove fatture", Toast.LENGTH_LONG).show();
            else Toast.makeText(context, "non ci sono nuove fatture", Toast.LENGTH_LONG).show();
            databaseAdapter.close();
            refreshListView();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static void refreshListView() {
        try {
            databaseAdapter = new FatturaDatabaseAdapter(context);
            databaseAdapter.open();
            cursor = databaseAdapter.fetchAll();
            cursorAdapter = new FatturaCursorAdapter(context, cursor);
            listViewFatture.setAdapter(cursorAdapter);
            databaseAdapter.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static Integer takeId(int position) {
        try {
            cursor.moveToPosition(position);
            return cursor.getInt(cursor.getColumnIndex(databaseAdapter.getKeyId()));
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

}
