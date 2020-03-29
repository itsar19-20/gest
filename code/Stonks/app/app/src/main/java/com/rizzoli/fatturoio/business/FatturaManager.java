package com.rizzoli.fatturoio.business;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.rizzoli.fatturoio.R;
import com.rizzoli.fatturoio.activity.FatturaActivity;
import com.rizzoli.fatturoio.activity.FragmentFatturaLista;
import com.rizzoli.fatturoio.localDatabaseAdapter.FatturaCursorAdapter;
import com.rizzoli.fatturoio.localDatabaseAdapter.FatturaDatabaseAdapter;
import com.rizzoli.fatturoio.serverDatabaseModel.Fattura;
import com.rizzoli.fatturoio.serverDatabaseModel.Persona;
import com.rizzoli.fatturoio.serverDatabaseModel.User;
import com.rizzoli.fatturoio.utils.VolleyUtils;

public class FatturaManager {

    private static FatturaDatabaseAdapter databaseAdapter;
    private static boolean puoiProcedere = false;

    // x richedo le fatture al server
    // x converto la stringa json in oggetti
    // x controllo di non aver già quelle fatture salvate nel telefono
    // x modifico ogni oggetto
    // x lo salvo nel database locale
    // aggiorno la list view prendendo gli elementi dal database locale

    private static Fattura[] fatture = null;

    public static Fattura[] getFatture() { return fatture; }

    public static void syncFatture(Context context, View view) {
        makeTheRequestToTheServer(context);
        int num = 0;
        while (!puoiProcedere) {
            Log.e("AAA", String.valueOf(num++));
            if (puoiProcedere) break;
        }
        databaseAdapter = new FatturaDatabaseAdapter(context);
        putValuesIntoLocalDatabase();
        refreshListView(view, context);
    }

    private static void refreshListView(View view, Context context) {
        ListView listView = view.findViewById(R.id.listView_fatture);
        listView.setAdapter(new FatturaCursorAdapter(context, databaseAdapter.fetchAll()));
    }

    private static void putValuesIntoLocalDatabase() {
        databaseAdapter.open();
        Integer max = databaseAdapter.getMaxId();
        for (Fattura f : fatture) {
            if (f.get_id() > max) {
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
                        f.getConto().get_id()
                );
            }
        }
        databaseAdapter.close();
    }

    private static void makeTheRequestToTheServer(Context context) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                VolleyUtils.url("archivio/getAllMineInvoices?user=8"),
                response -> {
                    String str = "\n";
                    try {
                        fatture = VolleyUtils.getGsonInstance().fromJson(response, Fattura[].class);
                        for (Fattura f : fatture) { str += String.valueOf(f.get_id()) + "\n\n"; }
                        // textView.setText(str);
                        Log.e("SERVLET_RESPONSE", "try");
                        Log.e("SERVLET_RESPONSE", response);
                        Log.e("SERVLET_RESPONSE", str);
                        puoiProcedere = true;
                    } catch (Exception e) {
                        // textView.setText("//////////////////////////////////////////////");
                        Log.e("SERVLET_RESPONSE", "catch");
                        Log.e("SERVLET_RESPONSE", response);
                        Log.e("SERVLET_RESPONSE", e.toString());
                    }
                },
                error -> {
                    Log.e("ERROR_REQUEST", error.toString());
                    // textView.setText("ERROR_REQUEST" + error.toString());
                }
        );
        // VolleyUtils.getRequestQueueInstance(((FatturaActivity)getActivity())).add(request);
        VolleyUtils.getRequestQueueInstance(context).add(request);
    }

}
