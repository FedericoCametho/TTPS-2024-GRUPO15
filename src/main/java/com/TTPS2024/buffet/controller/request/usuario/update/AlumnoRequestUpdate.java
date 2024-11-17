package com.TTPS2024.buffet.controller.request.usuario.update;


public class AlumnoRequestUpdate extends UsuarioRequestUpdate {
    private byte[] foto;
    private boolean habilitado;

    public AlumnoRequestUpdate() {
        super();
    }


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
