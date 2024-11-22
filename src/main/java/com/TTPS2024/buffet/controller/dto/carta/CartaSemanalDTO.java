package com.TTPS2024.buffet.controller.dto.carta;

import com.TTPS2024.buffet.model.carta.CartaDelDia;

import java.util.List;

public class CartaSemanalDTO {

    private String nombre;
    private List<CartaDelDiaDTO> cartasDelDia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CartaDelDiaDTO> getCartasDelDia() {
        return cartasDelDia;
    }

    public void setCartasDelDia(List<CartaDelDiaDTO> cartasDelDia) {
        this.cartasDelDia = cartasDelDia;
    }
}
