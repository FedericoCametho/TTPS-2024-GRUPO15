package com.TTPS2024.buffet.service.usuario.login;

public class Credential {
    private String token;
    private int expirationInSec;
    private String email;
    private String rol;

    public Credential(String token, int expirationInSec, String username, String rol) {
        this.token = token;
        this.expirationInSec = expirationInSec;
        this.email = username;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpirationInSec() {
        return expirationInSec;
    }

    public void setExpirationInSec(int expirationInSec) {
        this.expirationInSec = expirationInSec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
