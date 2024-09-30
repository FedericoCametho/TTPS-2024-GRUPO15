package com.TTPS2024.buffet.model.usuario;

import com.TTPS2024.buffet.model.permiso.Rol;
import jakarta.persistence.Entity;

@Entity
public class ResponsableDeTurno extends Usuario {
    public Turno turno;
    public ResponsableDeTurno(Integer dni, String email, String nombre, String apellido, Rol rol, Turno turno) {
        super(dni, email, nombre, apellido, rol);
        this.turno = turno;
    }

    public ResponsableDeTurno() {

    }
}
