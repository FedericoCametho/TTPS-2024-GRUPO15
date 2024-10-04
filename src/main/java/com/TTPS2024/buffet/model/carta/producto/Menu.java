package com.TTPS2024.buffet.model.carta.producto;

import com.TTPS2024.buffet.model.carrito.Carrito;
import com.TTPS2024.buffet.model.carrito.Compra;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Menu implements ProductoComercializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected Double precio;
    @ManyToMany(mappedBy = "menues", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comida> comidas;

    @ManyToMany
    @JoinTable(
            name = "menu_carrito",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "carrito_id")
    )
    private List<Carrito> carritos;
    @ManyToMany(mappedBy = "menues", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> compras;

    public Menu(String titulo, List<Comida> comidas, Double precio) {
        this.nombre = titulo;
        this.precio = precio;
        this.comidas = comidas;
        this.setComidasEnMenu(comidas);
    }

    public Menu() {
        super();
    }

    private void setComidasEnMenu(List<Comida> comidas) {
        comidas.forEach(comida -> comida.setEnMenu(Boolean.TRUE));
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getId() {
        return this.id;
    }
}
