package com.TTPS2024.buffet.controller.request.carta;

import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import com.TTPS2024.buffet.model.carta.producto.Menu;

import java.util.List;


public class CartaDelDiaRequest {
    private List<Menu> menues;
    private DiaSemana diaSemana;
    private CartaSemanal cartaSemanal;
    private boolean isActiva;

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public CartaSemanal getCartaSemanal() {
        return cartaSemanal;
    }

    public void setCartaSemanal(CartaSemanal cartaSemanal) {
        this.cartaSemanal = cartaSemanal;
    }

    public Menu getMenuVegetariano() {
        return menues.stream().filter(Menu::isVeggie).findFirst().orElse(null);
    }

    public Menu getMenuComun() {
        return menues.stream().filter(menu -> !menu.isVeggie()).findFirst().orElse(null);
    }

    public List<Menu> getMenues() {
        return menues;
    }
    public void setMenues(List<Menu> menues) {
        this.menues = menues;
    }

    public boolean isActiva() {
        return isActiva;
    }

    public void setActiva(boolean activa) {
        isActiva = activa;
    }
}
