package com.TTPS2024.buffet.model.menu;

import com.TTPS2024.buffet.model.carrito.Carrito;
import com.TTPS2024.buffet.model.carrito.Compra;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToMany(mappedBy = "menues", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comida> comidas;
    private Double precio;
    @ManyToMany
    @JoinTable(
            name = "menu_carrito",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "carrito_id")
    )
    private List<Carrito> carritos;
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public Menu(String titulo, List<Comida> comidas, Double precio) {
        this.titulo = titulo;
        this.comidas = comidas;
        this.precio = precio;
    }

    public Menu() {
    }
    public Long getId() {
        return id;
    }
    public Double getPrecio() {
        return precio;
    }
}
