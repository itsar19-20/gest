package com.rizzoli.fatturoio.serverDatabaseModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Conto {

    public Conto() {}

    @SerializedName("id")
    private Integer _id;

    private String nome, prefisso;
    // private List<Fattura> fatture;
    // private List<Pagamento> pagamenti;
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

    public String getPrefisso() {
        return prefisso;
    }

    public void setPrefisso(String prefisso) {
        this.prefisso = prefisso;
    }

    @Override
    public String toString() {
        return "Conto{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", prefisso='" + prefisso + '\'' +
                ", saldoDisponibile=" + saldoDisponibile +
                ", saldoUtile=" + saldoUtile +
                ", utente=" + utente +
                '}';
    }
}
