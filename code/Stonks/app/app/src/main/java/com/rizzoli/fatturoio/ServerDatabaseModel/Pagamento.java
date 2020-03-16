package com.rizzoli.fatturoio.ServerDatabaseModel;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

class Pagamento {

    @SerializedName("id")
    private Integer _id;

    private Fattura fattura;
    private Date dataPagamento;
    private float giaPagato;
    private boolean pagato;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Fattura getFattura() {
        return fattura;
    }

    public void setFattura(Fattura fattura) {
        this.fattura = fattura;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public float getGiaPagato() {
        return giaPagato;
    }

    public void setGiaPagato(float giaPagato) {
        this.giaPagato = giaPagato;
    }

    public boolean isPagato() {
        return pagato;
    }

    public void setPagato(boolean pagato) {
        this.pagato = pagato;
    }
}
