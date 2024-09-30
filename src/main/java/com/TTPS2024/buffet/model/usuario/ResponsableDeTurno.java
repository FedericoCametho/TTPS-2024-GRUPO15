package com.TTPS2024.buffet.model.usuario;

import com.TTPS2024.buffet.model.permiso.Rol;
import jakarta.persistence.Entity;

@Entity
public class ResponsableDeTurno extends Usuario {
    public ResponsableDeTurno(Integer dni, String email, String nombre, String apellido, Rol rol) {
        super(dni, email, nombre, apellido, rol);
    }

    public ResponsableDeTurno() {

    }
}
