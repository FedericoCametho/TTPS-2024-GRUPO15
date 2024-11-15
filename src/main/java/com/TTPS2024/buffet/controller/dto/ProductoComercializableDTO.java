package com.TTPS2024.buffet.controller.dto;

import jakarta.persistence.Lob;

public abstract class ProductoComercializableDTO {
    protected Long id;
    protected String nombre;
    protected Double precio;
    @Lob
    protected byte[] foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
