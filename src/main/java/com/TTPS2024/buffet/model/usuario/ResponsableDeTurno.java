package com.TTPS2024.buffet.model.usuario;

import com.TTPS2024.buffet.model.permiso.Rol;
import jakarta.persistence.Entity;

@Entity
public class ResponsableDeTurno extends Usuario {
    public Turno turno;
    public ResponsableDeTurno(Integer dni, String email, String nombre, String apellido, Turno turno, String contrasena) {
        super(dni, email, nombre, apellido, Rol.RESPONSABLE_DE_TURNO, contrasena);
        this.turno = turno;
    }

    public ResponsableDeTurno() {

    }
    public Turno getTurno() {
        return turno;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
