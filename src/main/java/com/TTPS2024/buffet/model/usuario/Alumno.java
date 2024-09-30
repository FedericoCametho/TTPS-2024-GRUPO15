package com.TTPS2024.buffet.model.usuario;

import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.permiso.Rol;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Alumno extends Usuario {
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> compras;

    private Boolean habilitado;
    public Alumno(Integer dni, String email, String nombre, String apellido, Rol rol) {
        super(dni, email, nombre, apellido, rol);
        this.compras = new ArrayList<>();
        this.habilitado = true;
    }

    public Alumno() {

    }

    public List<Compra> getCompras() {
        return compras;
    }
}
