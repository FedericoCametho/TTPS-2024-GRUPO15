package com.TTPS2024.buffet.model.carta.producto;

import com.TTPS2024.buffet.model.carrito.Compra;
import jakarta.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class ProductoComercializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected Double precio;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public ProductoComercializable() {}

    public ProductoComercializable(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
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
