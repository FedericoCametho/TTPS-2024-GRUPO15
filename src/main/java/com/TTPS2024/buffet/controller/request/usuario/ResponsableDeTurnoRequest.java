package com.TTPS2024.buffet.controller.request.usuario;


import com.TTPS2024.buffet.model.usuario.Turno;

public class ResponsableDeTurnoRequest extends UsuarioRequest{
    private Turno turno;

    public ResponsableDeTurnoRequest() {
        super();
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
