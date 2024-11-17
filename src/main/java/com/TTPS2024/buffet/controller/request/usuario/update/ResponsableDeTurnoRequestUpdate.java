package com.TTPS2024.buffet.controller.request.usuario.update;


import com.TTPS2024.buffet.model.usuario.Turno;

public class ResponsableDeTurnoRequestUpdate extends UsuarioRequestUpdate {
    private Turno turno;

    public ResponsableDeTurnoRequestUpdate() {
        super();
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
