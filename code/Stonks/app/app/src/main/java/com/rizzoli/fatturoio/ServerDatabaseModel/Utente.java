package com.rizzoli.fatturoio.ServerDatabaseModel;

public class Utente extends Users {

    private String metodoDiRegistrazione;

    public String getMetodoDiRegistrazione() {
        return metodoDiRegistrazione;
    }

    public void setMetodoDiRegistrazione(String metodoDiRegistrazione) {
        this.metodoDiRegistrazione = metodoDiRegistrazione;
    }
}
