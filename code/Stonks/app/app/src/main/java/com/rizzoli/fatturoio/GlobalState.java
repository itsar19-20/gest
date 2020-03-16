package com.rizzoli.fatturoio;

import android.app.Application;

import com.rizzoli.fatturoio.serverDatabaseModel.Users;

public class GlobalState extends Application {

    // Qui salviamo le variabili che saranno accessibili da tutte le altre classi
    private String test;
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

}
