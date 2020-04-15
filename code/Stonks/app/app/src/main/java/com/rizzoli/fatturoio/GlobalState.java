package com.rizzoli.fatturoio;

import android.app.Application;

import com.rizzoli.fatturoio.serverDatabaseModel.User;

public class GlobalState extends Application {

    // Qui salviamo le variabili che saranno accessibili da tutte le altre classi
    private static String test, userName;
    private static Integer userId, fatturaDettaglioId;

    public static Integer getFatturaDettaglioId() { return fatturaDettaglioId; }

    public static void setFatturaDettaglioId(Integer fatturaDettaglioId) { GlobalState.fatturaDettaglioId = fatturaDettaglioId; }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        GlobalState.userName = userName;
    }

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer userId) {
        GlobalState.userId = userId;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}
