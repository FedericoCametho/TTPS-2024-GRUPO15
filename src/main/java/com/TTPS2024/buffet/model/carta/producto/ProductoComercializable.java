package com.TTPS2024.buffet.model.carta.producto;


import jakarta.persistence.*;

@MappedSuperclass
public abstract class ProductoComercializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected Double precio;
    @Lob
    protected byte[] foto;

    public ProductoComercializable() {
    }
    public ProductoComercializable(String nombre, Double precio, byte[] foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.foto = foto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}

