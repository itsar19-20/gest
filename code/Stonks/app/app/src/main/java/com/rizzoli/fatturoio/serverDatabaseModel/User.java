package com.rizzoli.fatturoio.serverDatabaseModel;

import java.util.Date;

public class User extends Persona {

    private String username, password;
    private Date dataOraUltimoLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDataOraUltimoLogin() {
        return dataOraUltimoLogin;
    }

    public void setDataOraUltimoLogin(Date dataOraUltimoLogin) {
        this.dataOraUltimoLogin = dataOraUltimoLogin;
    }
}