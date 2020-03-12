package com.rizzoli.fatturoio.model;

import com.google.gson.annotations.SerializedName;

public class Admin extends Users {

    public String codiceAdmin;

    public String getCodiceAdmin() {
        return codiceAdmin;
    }

    public void setCodiceAdmin(String codiceAdmin) {
        this.codiceAdmin = codiceAdmin;
    }
}
