package com.rizzoli.fatturoio.serverDatabaseModel;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Fattura {

    @SerializedName("id")
    private Integer _id;

    private Date data;
    private Integer scadenza;

    @SerializedName("e_una_fattura_cliente")
    private boolean eUnaFatturaCliente;

    private Persona persona;
    private String nota;
    private List<Articolo> articoli;
    private String numeroFattura;
    private float iva;
    private float lordo;
    private boolean pagata;
    private Conto conto;



    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
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

    public Conto getConto() {
        return conto;
    }

    public void setConto(Conto conto) {
        this.conto = conto;
    }

}
