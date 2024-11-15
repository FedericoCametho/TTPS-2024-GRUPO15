package com.TTPS2024.buffet.controller.dto;


import java.util.List;

public class MenuDTO extends ProductoComercializableDTO{

    private List<Long> comidas;

    private List<Long> compras;

    private List<Long> cartasDelDia;
    private boolean isVeggie;


    public List<Long> getComidas() {
        return comidas;
    }

    public void setComidas(List<Long> comidas) {
        this.comidas = comidas;
    }

    public List<Long> getCompras() {
        return compras;
    }

    public void setCompras(List<Long> compras) {
        this.compras = compras;
    }

    public List<Long> getCartasDelDia() {
        return cartasDelDia;
    }

    public void setCartasDelDia(List<Long> cartasDelDia) {
        this.cartasDelDia = cartasDelDia;
    }

    public boolean isVeggie() {
        return isVeggie;
    }

    public void setVeggie(boolean veggie) {
        isVeggie = veggie;
    }
}
