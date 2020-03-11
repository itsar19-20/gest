package com.rizzoli.fatturoio.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MetodoDiPagamento {

    @SerializedName("id")
    private Integer _id;

    @SerializedName("fattura")
    private List<Fattura> fatture;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public List<Fattura> getFatture() {
        return fatture;
    }

    public void setFatture(List<Fattura> fatture) {
        this.fatture = fatture;
    }
}
