package com.TTPS2024.buffet.controller.dto.carta;

import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;

import java.util.List;

public class CartaDelDiaDTO {
    private List<Long> menus;
    private DiaSemana diaSemana;
    private Long cartaSemanal;
    private boolean isActiva;

    public List<Long> getMenus() {
        return menus;
    }

    public void setMenus(List<Long> menus) {
        this.menus = menus;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Long getCartaSemanal() {
        return cartaSemanal;
    }

    public void setCartaSemanal(Long cartaSemanal) {
        this.cartaSemanal = cartaSemanal;
    }

    public boolean isActiva() {
        return isActiva;
    }

    public void setActiva(boolean activa) {
        isActiva = activa;
    }
}
