package com.rizzoli.fatturoio.business;

import android.content.Context;

import com.rizzoli.fatturoio.localDatabaseAdapter.PeronaDatabaseAdapter;
import com.rizzoli.fatturoio.serverDatabaseModel.Persona;

public class PersonaManager {

    private static PeronaDatabaseAdapter databaseAdapter;

    public static void syncPersona(Persona p, Context context) {
        databaseAdapter = new PeronaDatabaseAdapter(context);
        databaseAdapter.open();
        if (databaseAdapter.exist(p.get_id()))
            databaseAdapter.update(p.get_id(), p.getNome(), p.getCognome(), p.getpIVA(), p.getMail(), p.getIndirizzo(), p.getTelefono(), p.getAutore(), p.isEliminabile() ? 1 : 0);
        else
            databaseAdapter.create(p.get_id(), p.getNome(), p.getCognome(), p.getpIVA(), p.getMail(), p.getIndirizzo(), p.getTelefono(), p.getAutore(), p.isEliminabile() ? 1 : 0);
        databaseAdapter.close();
    }

}
