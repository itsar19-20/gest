package com.rizzoli.fatturoio.serverDatabaseModel;

import com.google.gson.annotations.SerializedName;

public class Persona {

    @SerializedName("id")
    private Integer _id;

    private String nome, cognome, pIVA, mail, indirizzo, telefono;
    private Integer autore;
    private boolean eliminabile;

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getAutore() {
        return autore;
    }

    public void setAutore(Integer autore) {
        this.autore = autore;
    }

    public boolean isEliminabile() {
        return eliminabile;
    }

    public void setEliminabile(boolean eliminabile) {
        this.eliminabile = eliminabile;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", pIVA='" + pIVA + '\'' +
                ", mail='" + mail + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", autore=" + autore +
                ", eliminabile=" + eliminabile +
                '}';
    }
}
