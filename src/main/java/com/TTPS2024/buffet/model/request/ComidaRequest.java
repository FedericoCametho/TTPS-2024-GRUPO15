package com.TTPS2024.buffet.model.request;

import com.TTPS2024.buffet.model.carta.producto.TipoComida;



public class ComidaRequest {
    private String nombre;
    private TipoComida tipoComida;
    private Double precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


}
