package com.rizzoli.fatturoio;

import android.app.Application;

import com.rizzoli.fatturoio.serverDatabaseModel.User;

public class GlobalState extends Application {

    // Qui salviamo le variabili che saranno accessibili da tutte le altre classi
    private String test;
    private User user;
    private static Integer fatturaDettaglioId;

    public static Integer getFatturaDettaglioId() { return fatturaDettaglioId; }

    public static void setFatturaDettaglioId(Integer fatturaDettaglioId) { GlobalState.fatturaDettaglioId = fatturaDettaglioId; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}
