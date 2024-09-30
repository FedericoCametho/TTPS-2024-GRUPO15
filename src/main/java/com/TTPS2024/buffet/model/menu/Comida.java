package com.TTPS2024.buffet.model.menu;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private TipoComida tipoComida;
    private Double precio;
    @OneToMany
    private List<EtiquetaComida> etiquetas;
    @ManyToMany
    @JoinTable(
            name = "comida_menu",
            joinColumns = @JoinColumn(name = "comida_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menues;

    public Comida(String nombre, TipoComida tipoComida, Double precio, List<EtiquetaComida> etiquetas) {
        this.nombre = nombre;
        this.tipoComida = tipoComida;
        this.precio = precio;
        this.etiquetas = etiquetas;
    }

    public Comida() {

    }
}
