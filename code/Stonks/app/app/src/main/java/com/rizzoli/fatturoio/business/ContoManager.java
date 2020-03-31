package com.rizzoli.fatturoio.business;

import android.content.Context;

import com.rizzoli.fatturoio.localDatabaseAdapter.ContoDatabaseAdapter;
import com.rizzoli.fatturoio.serverDatabaseModel.Conto;

public class ContoManager {

    private static ContoDatabaseAdapter databaseAdapter;

    public static void syncConto(Conto conto, Context context) {
        databaseAdapter = new ContoDatabaseAdapter(context);
        databaseAdapter.open();
        if (databaseAdapter.exist(conto.get_id()))
            databaseAdapter.update(conto.get_id(), conto.getNome(), conto.getPrefisso(), conto.getSaldoDisponibile(), conto.getSaldoDisponibile(), conto.getUtente());
        else
            databaseAdapter.create(conto.get_id(), conto.getNome(), conto.getPrefisso(), conto.getSaldoDisponibile(), conto.getSaldoDisponibile(), conto.getUtente());
        databaseAdapter.close();
    }

}
