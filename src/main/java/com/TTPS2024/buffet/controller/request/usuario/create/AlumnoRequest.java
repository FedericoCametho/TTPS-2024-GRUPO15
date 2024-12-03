package com.TTPS2024.buffet.controller.request.usuario.create;

public class AlumnoRequest extends UsuarioRequest {
    private String foto;
    private boolean habilitado;

    public AlumnoRequest() {
        super();
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
