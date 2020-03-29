package com.rizzoli.fatturoio.serverDatabaseModel;

public class Utente extends User {

    private String metodoDiRegistrazione;

    public String getMetodoDiRegistrazione() {
        return metodoDiRegistrazione;
    }

    public void setMetodoDiRegistrazione(String metodoDiRegistrazione) {
        this.metodoDiRegistrazione = metodoDiRegistrazione;
    }
}
