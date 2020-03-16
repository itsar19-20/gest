package com.rizzoli.fatturoio.serverDatabaseModel;

import org.json.JSONObject;

public class TTTesttt extends JSONObject {

    private String alfa;
    private String bravo;
    private int charlie;

    public TTTesttt(String alfa, String bravo, int charlie) {
        this.alfa = alfa;
        this.bravo = bravo;
        this.charlie = charlie;
    }

    public TTTesttt(JSONObject response) {
    }

    public String getAlfa() {
        return alfa;
    }

    public void setAlfa(String alfa) {
        this.alfa = alfa;
    }

    public String getBravo() {
        return bravo;
    }

    public void setBravo(String bravo) {
        this.bravo = bravo;
    }

    public int getCharlie() {
        return charlie;
    }

    public void setCharlie(int charlie) {
        this.charlie = charlie;
    }

    @Override
    public String toString() {
        return "TTTesttt{" +
                "alfa='" + alfa + '\'' +
                ", bravo='" + bravo + '\'' +
                ", charlie=" + charlie +
                '}';
    }
}
