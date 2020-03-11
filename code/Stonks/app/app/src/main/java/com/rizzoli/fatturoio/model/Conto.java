package com.rizzoli.fatturoio.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Conto {

    @SerializedName("id")
    private Integer _id;

    private String nome;
    private List<Fattura> fatture;
    private List<Pagamento> pagamenti;
    private float saldoDisponibile, saldoUtile;
    private Integer utente;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Fattura> getFatture() {
        return fatture;
    }

    public void setFatture(List<Fattura> fatture) {
        this.fatture = fatture;
    }

    public List<Pagamento> getPagamenti() {
        return pagamenti;
    }

    public void setPagamenti(List<Pagamento> pagamenti) {
        this.pagamenti = pagamenti;
    }

    public float getSaldoDisponibile() {
        return saldoDisponibile;
    }

    public void setSaldoDisponibile(float saldoDisponibile) {
        this.saldoDisponibile = saldoDisponibile;
    }

    public float getSaldoUtile() {
        return saldoUtile;
    }

    public void setSaldoUtile(float saldoUtile) {
        this.saldoUtile = saldoUtile;
    }

    public Integer getUtente() {
        return utente;
    }

    public void setUtente(Integer utente) {
        this.utente = utente;
    }
}
