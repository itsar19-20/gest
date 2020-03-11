package com.rizzoli.fatturoio.model;

import com.google.gson.annotations.SerializedName;

class Articolo {

    @SerializedName("id")
    private Integer _id;

    private String descrizione;
    private float
        quantita,
        prezzo,
        parziale,
        iva;
    private Fattura fattura;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getQuantita() {
        return quantita;
    }

    public void setQuantita(float quantita) {
        this.quantita = quantita;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public float getParziale() {
        return parziale;
    }

    public void setParziale(float parziale) {
        this.parziale = parziale;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public Fattura getFattura() {
        return fattura;
    }

    public void setFattura(Fattura fattura) {
        this.fattura = fattura;
    }
}
