package com.rizzoli.fatturoio.model;

import com.google.gson.annotations.SerializedName;

public class Admin extends Users {

    public SerializedName codiceAdmin;

    public SerializedName getCodiceAdmin() {
        return codiceAdmin;
    }

    public void setCodiceAdmin(SerializedName codiceAdmin) {
        this.codiceAdmin = codiceAdmin;
    }
}
