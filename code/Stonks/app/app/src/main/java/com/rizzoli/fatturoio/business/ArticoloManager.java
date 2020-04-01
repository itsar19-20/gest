package com.rizzoli.fatturoio.business;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import com.rizzoli.fatturoio.GlobalState;
import com.rizzoli.fatturoio.localDatabaseAdapter.ArticoloCursorAdapter;
import com.rizzoli.fatturoio.localDatabaseAdapter.ArticoloDatabaseAdapter;
import com.rizzoli.fatturoio.serverDatabaseModel.Articolo;

public class ArticoloManager {

    private static ArticoloDatabaseAdapter databaseAdapter;
    private static Cursor cursor;
    private static ArticoloCursorAdapter cursorAdapter;

    public static void loadArticoli(Context context, View view, ListView listView) {
        try {
            databaseAdapter = new ArticoloDatabaseAdapter(context);
            databaseAdapter.open();
            cursor = databaseAdapter.fetchByFatturaId(GlobalState.getFatturaDettaglioId());
            cursorAdapter = new ArticoloCursorAdapter(context, cursor);
            listView.setAdapter(cursorAdapter);
            databaseAdapter.close();
        } catch (Exception e ) { e.printStackTrace(); }
    }

    public static void create(Articolo[] articoli, Integer fatturaID, Context context) {
        try {
            databaseAdapter = new ArticoloDatabaseAdapter(context);
            databaseAdapter.open();
            for (Articolo a : articoli) {
                databaseAdapter.crate(
                        a.get_id(),
                        a.getDescrizione(),
                        a.getQuantita(),
                        a.getPrezzo(),
                        a.getParziale(),
                        fatturaID
                );
            }
            databaseAdapter.close();
        } catch (Exception e ) { e.printStackTrace(); }
    }
}
