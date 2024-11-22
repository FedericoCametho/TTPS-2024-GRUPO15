package com.TTPS2024.buffet.controller.dto.usuario;

import com.TTPS2024.buffet.model.usuario.Turno;

public class ResponsableDeTurnoDTO extends UsuarioDTO{
    private Turno turno;

    public ResponsableDeTurnoDTO() {
        super();
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
