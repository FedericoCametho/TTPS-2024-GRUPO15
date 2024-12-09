package com.TTPS2024.buffet.controller.request.usuario.create;

import com.TTPS2024.buffet.controller.request.usuario.RequestUsuarioGeneral;

public abstract class UsuarioRequest extends RequestUsuarioGeneral {
    protected Integer dni;
    protected String email;

    protected String contrasena;

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
