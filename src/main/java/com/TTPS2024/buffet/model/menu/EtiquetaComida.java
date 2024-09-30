package com.TTPS2024.buffet.model.menu;

import jakarta.persistence.*;

@Entity
public class EtiquetaComida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Comida comida;

    public EtiquetaComida() {
    }

    public EtiquetaComida(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
