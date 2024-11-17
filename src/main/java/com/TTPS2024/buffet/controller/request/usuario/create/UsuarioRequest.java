package com.TTPS2024.buffet.controller.request.usuario.create;

import com.TTPS2024.buffet.controller.request.usuario.RequestUsuarioGeneral;

public abstract class UsuarioRequest extends RequestUsuarioGeneral {

    protected String contrasena;
    protected String CONTRASENA_DEFAULT = "1234";


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
