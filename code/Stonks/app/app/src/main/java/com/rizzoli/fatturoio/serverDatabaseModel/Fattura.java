package com.rizzoli.fatturoio.serverDatabaseModel;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Fattura {

    @SerializedName("id")
    private Integer _id;

    private String data, nota, numeroFattura;
    private Integer anno, scadenza;
    private boolean eUnaFatturaCliente, pagata, notaDiCredito;
    private Persona persona;
    private Articolo[] articoli;
    private float iva, lordo;
    private Conto conto;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getScadenza() {
        return scadenza;
    }

    public void setScadenza(Integer scadenza) {
        this.scadenza = scadenza;
    }

    public boolean iseUnaFatturaCliente() {
        return eUnaFatturaCliente;
    }

    public void seteUnaFatturaCliente(boolean eUnaFatturaCliente) {
        this.eUnaFatturaCliente = eUnaFatturaCliente;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(String numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getLordo() {
        return lordo;
    }

    public void setLordo(float lordo) {
        this.lordo = lordo;
    }

    public boolean isPagata() {
        return pagata;
    }

    public void setPagata(boolean pagata) {
        this.pagata = pagata;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public boolean isNotaDiCredito() {
        return notaDiCredito;
    }

    public void setNotaDiCredito(boolean notaDiCredito) {
        this.notaDiCredito = notaDiCredito;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Articolo[] getArticoli() {
        return articoli;
    }

    public void setArticoli(Articolo[] articoli) {
        this.articoli = articoli;
    }

    public Conto getConto() {
        return conto;
    }

    public void setConto(Conto conto) {
        this.conto = conto;
    }

    @Override
    public String toString() {
        return "Fattura{" +
                "_id=" + _id +
                ", data='" + data + '\'' +
                ", anno=" + anno +
                ", scadenza=" + scadenza +
                ", eUnaFatturaCliente=" + eUnaFatturaCliente +
                ", persona=" + persona +
                ", nota='" + nota + '\'' +
                ", articoli=" + articoli +
                ", numeroFattura='" + numeroFattura + '\'' +
                ", iva=" + iva +
                ", lordo=" + lordo +
                ", pagata=" + pagata +
                ", notaDiCredito=" + notaDiCredito +
                ", conto=" + conto +
                '}';
    }
}
