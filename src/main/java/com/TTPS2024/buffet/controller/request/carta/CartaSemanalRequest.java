package com.TTPS2024.buffet.controller.request.carta;

import com.TTPS2024.buffet.model.carta.CartaDelDia;

import java.util.List;

public class CartaSemanalRequest {

    private String nombre;
    private List<Long> cartasDelDia;


    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public List<Long> getCartasDelDia() {
        return cartasDelDia;
    }

    public void setCartasDelDia(List<Long> cartasDelDia) {
        this.cartasDelDia = cartasDelDia;
    }


}
